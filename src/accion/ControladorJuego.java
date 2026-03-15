package accion;

import modelo.Configuracion;
import modelo.Tablero;
import util.ImpresorTablero;
import util.LectorEntrada;

/**
 * Controla el flujo completo de una partida
 */
public class ControladorJuego {

    private final LectorEntrada lector;

    public ControladorJuego(LectorEntrada lector) {
        this.lector = lector;
    }

    public void iniciar() {
        System.out.println("--- BUSCAMINAS ---");
        boolean jugarOtraVez = true;
        while (jugarOtraVez) {
            jugarPartida();
            jugarOtraVez = lector.leerSiNo("¿Otra partida?");
        }
        System.out.println("Hasta luego");
    }

    private void jugarPartida() {
        Configuracion config = elegirDificultad();
        Tablero tablero = new Tablero(config);
        ImpresorTablero imp = new ImpresorTablero(tablero);
        ProcesadorJugada proc = new ProcesadorJugada(tablero);

        ProcesadorJugada.Resultado resultado = ProcesadorJugada.Resultado.CONTINUA;

        while (resultado == ProcesadorJugada.Resultado.CONTINUA) {
            imp.imprimir();
            imp.imprimirEstadisticas();
            resultado = procesarTurno(tablero, proc);
        }

        imp.imprimir();
        mostrarResultadoFinal(resultado);
    }

    private ProcesadorJugada.Resultado procesarTurno(Tablero tablero, ProcesadorJugada proc) {
        char accion = lector.leerAccion();
        int fila = lector.leerEntero("Fila    (1-" + tablero.getFilas() + "): ", 1, tablero.getFilas()) - 1;
        int col = lector.leerEntero("Columna (1-" + tablero.getColumnas() + "): ", 1, tablero.getColumnas()) - 1;

        if (accion == 'D')
            return proc.destapar(fila, col);
        proc.marcar(fila, col);
        return ProcesadorJugada.Resultado.CONTINUA;
    }

    private Configuracion elegirDificultad() {
        System.out.println("\nDificultad:");
        System.out.println("  1. Facil    (8x8,  10 minas)");
        System.out.println("  2. Medio    (16x16, 40 minas)");
        System.out.println("  3. Dificil  (16x30, 99 minas)");
        System.out.println("  4. Personalizado");

        int opcion = lector.leerEntero("Opcion: ", 1, 4);
        return switch (opcion) {
            case 1 -> new Configuracion(Configuracion.Dificultad.FACIL);
            case 2 -> new Configuracion(Configuracion.Dificultad.MEDIO);
            case 3 -> new Configuracion(Configuracion.Dificultad.DIFICIL);
            default -> pedirConfiguracionPersonalizada();
        };
    }

    private Configuracion pedirConfiguracionPersonalizada() {
        int filas = lector.leerEntero("Filas (2-30): ", 2, 30);
        int cols = lector.leerEntero("Columnas (2-50): ", 2, 50);
        int maxMinas = filas * cols - 1;
        int minas = lector.leerEntero("Minas (1-" + maxMinas + "): ", 1, maxMinas);
        return new Configuracion(filas, cols, minas);
    }

    private void mostrarResultadoFinal(ProcesadorJugada.Resultado resultado) {
        if (resultado == ProcesadorJugada.Resultado.VICTORIA)
            System.out.println("\n¡Ganaste! Todas las minas esquivadas.");
        else
            System.out.println("\n¡Boom! Pisaste una mina.");
    }
}
