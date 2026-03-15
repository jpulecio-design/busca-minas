package accion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modelo.Tablero;

/**
 * Responsable exclusivamente de generar y distribuir las minas :v
 * 
 * de forma aleatoria sobre el tablero
 *
 * La primera celda destapada siempre queda libre de mina
 */
public class GeneradorTablero {

    private final Tablero tablero;

    public GeneradorTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    /**
     * Genera minas aleatoriamente evitando la celda segura inicial
     *
     * @param filaSegura    fila de la primera jugada del usuario
     * @param columnaSegura columna de la primera jugada del usuario
     */
    public void generarMinas(int filaSegura, int columnaSegura) {
        List<int[]> candidatos = construirListaCandidatos(filaSegura, columnaSegura);
        Collections.shuffle(candidatos);
        colocarMinas(candidatos);
        calcularVecinas();
    }

    // Metodos privados

    private List<int[]> construirListaCandidatos(int filaSegura, int colSegura) {
        List<int[]> candidatos = new ArrayList<>();
        for (int f = 0; f < tablero.getFilas(); f++)
            for (int c = 0; c < tablero.getColumnas(); c++)
                if (f != filaSegura || c != colSegura)
                    candidatos.add(new int[] { f, c });
        return candidatos;
    }

    private void colocarMinas(List<int[]> candidatos) {
        for (int i = 0; i < tablero.getTotalMinas(); i++)
            tablero.getCelda(candidatos.get(i)[0], candidatos.get(i)[1]).colocarMina();
    }

    /** Para cada celda cuenta cuantas de sus 8 vecinas tienen mina */
    private void calcularVecinas() {
        for (int f = 0; f < tablero.getFilas(); f++)
            for (int c = 0; c < tablero.getColumnas(); c++)
                tablero.getCelda(f, c).setMinasVecinas(contarMinasVecinas(f, c));
    }

    private int contarMinasVecinas(int fila, int col) {
        int contador = 0;
        for (int df = -1; df <= 1; df++)
            for (int dc = -1; dc <= 1; dc++) {
                int nf = fila + df, nc = col + dc;
                if ((df != 0 || dc != 0) && tablero.coordenadaValida(nf, nc)
                        && tablero.getCelda(nf, nc).tieneMina())
                    contador++;
            }
        return contador;
    }
}