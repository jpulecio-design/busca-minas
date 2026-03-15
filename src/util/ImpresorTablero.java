package util;

import modelo.Celda;
import modelo.Tablero;

/**
 * Renderiza el tablero en consola usando solo caracteres simples
 */
public class ImpresorTablero {

    // Simbolos
    private static final char OCULTA = '?';
    private static final char MINA = '*';
    private static final char MARCA = 'M';
    private static final char VACIA = ' ';

    private final Tablero tablero;

    public ImpresorTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void imprimir() {
        imprimirEncabezado();
        imprimirFilas();
        imprimirSeparador();
    }

    public void imprimirEstadisticas() {
        int restantes = tablero.getTotalMinas() - tablero.contarMarcas();
        System.out.println("Minas: " + Math.max(restantes, 0)
                + " / " + tablero.getTotalMinas());
    }

    private void imprimirEncabezado() {
        System.out.print("\n   ");
        for (int c = 0; c < tablero.getColumnas(); c++)
            System.out.printf("%2d", c + 1);
        System.out.println();
        imprimirSeparador();
    }

    private void imprimirFilas() {
        for (int f = 0; f < tablero.getFilas(); f++) {
            System.out.printf("%2d|", f + 1);
            for (int c = 0; c < tablero.getColumnas(); c++)
                System.out.print(" " + simboloCelda(tablero.getCelda(f, c)));
            System.out.println();
        }
    }

    private void imprimirSeparador() {
        System.out.println("  +" + "--".repeat(tablero.getColumnas()) + "-+");
    }

    private String simboloCelda(Celda celda) {
        if (!celda.estaRevelada())
            return celda.estaMarcada() ? String.valueOf(MARCA) : String.valueOf(OCULTA);

        if (celda.tieneMina())
            return String.valueOf(MINA);
        if (celda.getMinasVecinas() == 0)
            return String.valueOf(VACIA);

        return String.valueOf(celda.getMinasVecinas());
    }
}
