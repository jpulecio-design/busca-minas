package modelo;
/**
 * Configuración intocable de una partida, dimensiones y cantidad de minas
 * Centraliza las constantes del juego 
 */
public class Configuracion {
    public enum Dificultad {
        FACIL(8, 8, 10),
        MEDIO(16, 16, 40),
        DIFICIL(16, 30, 99),
        CUSTOM(-1, -1, -1);

        public final int filas;
        public final int columnas;
        public final int minas;

        Dificultad(int filas, int columnas, int minas) {
            this.filas = filas;
            this.columnas = columnas;
            this.minas = minas;
        }
    }
    /*  poseen diferentes atributos con mismo nombre porque enum trabaja con ellos directamente
    y confuguracion necesita q no se acceda a ellos*/

    private final int filas;
    private final int columnas;
    private final int totalMinas;
    //El usuario eligue dificultad predefinida 

    public Configuracion(Dificultad dificultad) {
        this.filas = dificultad.filas;
        this.columnas = dificultad.columnas;
        this.totalMinas = dificultad.minas;
    }
    // Ekl usuario elige dificultad personalizada

    public Configuracion(int filas, int columnas, int totalMinas) {
        validar(filas, columnas, totalMinas);
        this.filas = filas;
        this.columnas = columnas;
        this.totalMinas = totalMinas;
    }

    private void validar(int filas, int columnas, int minas) {
        if (filas < 2 || columnas < 2)
            throw new IllegalArgumentException("El tablero debe ser de al menos 2x2");
        if (minas < 1 || minas >= filas * columnas)
            throw new IllegalArgumentException("Numero de minas invalido");
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getTotalMinas() {
        return totalMinas;
    }
}
