import modelo.Celda;
import modelo.Tablero;

/**
 * Procesa cada jugada del usuario aplicando las reglas del Buscaminas
 */
public class ProcesadorJugada {

    public enum Resultado {
        CONTINUA, VICTORIA, DERROTA
    }

    private final Tablero tablero;
    private final GeneradorTablero generador;
    private boolean primeraJugada;

    public ProcesadorJugada(Tablero tablero) {
        this.tablero = tablero;
        this.generador = new GeneradorTablero(tablero);
        this.primeraJugada = true;
    }

    // Acciones publicas

    /**
     * Destapar una celda. Si es la primera jugada, genera el tablero
     * garantizando que esa posicion sea segura
     */
    public Resultado destapar(int fila, int col) {
        if (!tablero.coordenadaValida(fila, col))
            return Resultado.CONTINUA;

        Celda celda = tablero.getCelda(fila, col);
        if (celda.estaRevelada() || celda.estaMarcada())
            return Resultado.CONTINUA;

        if (primeraJugada) {
            generador.generarMinas(fila, col);
            primeraJugada = false;
        }

        if (celda.tieneMina()) {
            celda.revelar();
            tablero.revelarTodasLasMinas();
            return Resultado.DERROTA;
        }

        revelarCascada(fila, col);
        return tablero.victoriaAlcanzada() ? Resultado.VICTORIA : Resultado.CONTINUA;
    }

    /**
     * Alternar bandera de Marca en una celda oculta
     */
    public void marcar(int fila, int col) {
        if (tablero.coordenadaValida(fila, col))
            tablero.getCelda(fila, col).cambiarMarca();
    }

    //  Logica interna 

    /**
     * Revela en cascada o descubir muchas de las celdas vacías contiguas
     * Una celda vacia es aquella con minasVecinas == 0
     */
    private void revelarCascada(int fila, int col) {
        if (!tablero.coordenadaValida(fila, col))
            return;

        Celda celda = tablero.getCelda(fila, col);
        if (celda.estaRevelada() || celda.estaMarcada() || celda.tieneMina())
            return;

        celda.revelar();

        if (celda.getMinasVecinas() == 0)
            expandirVecinas(fila, col);
    }

    private void expandirVecinas(int fila, int col) {
        for (int deltafila = -1; deltafila <= 1; deltafila++)
            for (int deltacolumna = -1; deltacolumna <= 1; deltacolumna++)
                if (deltafila != 0 || deltacolumna != 0)
                    revelarCascada(fila + deltafila, col + deltacolumna);
    }
}