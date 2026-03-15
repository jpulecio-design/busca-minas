import accion.ControladorJuego;
import util.LectorEntrada;

public class App {
    public static void main(String[] args) throws Exception {
        LectorEntrada lector = new LectorEntrada();
        ControladorJuego juego = new ControladorJuego(lector);
        juego.iniciar();
        lector.cerrar();
    }
}
