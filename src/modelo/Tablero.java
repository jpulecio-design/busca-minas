package modelo;

/**
 * Modelo del tablero: contiene la matriz de celdas y expone
 * solo operaciones de dominio 
 */
public class Tablero {
    private final Celda[][] matriz;
    private final Configuracion config;

    public Tablero(Configuracion config) {
        this.config = config;
        this.matriz = new Celda[config.getFilas()][config.getColumnas()];
        inicializarCeldas();
    }

    // Inicializacion

    private void inicializarCeldas() {
        for (int f = 0; f < config.getFilas(); f++)
            for (int c = 0; c < config.getColumnas(); c++)
                matriz[f][c] = new Celda();
    }

    // Acceso

    public Celda getCelda(int fila, int col) {
        return matriz[fila][col];
    }

    public boolean coordenadaValida(int fila, int col) {
        return fila >= 0 && fila < config.getFilas()
                && col >= 0 && col < config.getColumnas();
    }

    public int getFilas() {
        return config.getFilas();
    }

    public int getColumnas() {
        return config.getColumnas();
    }

    public int getTotalMinas() {
        return config.getTotalMinas();
    }

    // Consultas de estado global 

    public int contarCeldasSinRevelar() {
        int contador = 0;
        for (int f = 0; f < config.getFilas(); f++)
            for (int c = 0; c < config.getColumnas(); c++)
                if (!matriz[f][c].estaRevelada())
                    contador++;
        return contador;
    }

    public int contarMarcas() {
        int contador = 0;
        for (int f = 0; f < config.getFilas(); f++)
            for (int c = 0; c < config.getColumnas(); c++)
                if (matriz[f][c].estaMarcada())
                    contador++;
        return contador;
    }

    /** Hay victoria cuando todas las celdas sin mina están reveladas */
    public boolean victoriaAlcanzada() {
        return contarCeldasSinRevelar() == config.getTotalMinas();
    }

    /** Revela todas las minas ,para mostrar al perder */
    public void revelarTodasLasMinas() {
        for (int f = 0; f < config.getFilas(); f++)
            for (int c = 0; c < config.getColumnas(); c++)
                if (matriz[f][c].tieneMina())
                    matriz[f][c].revelar();
    }
}
