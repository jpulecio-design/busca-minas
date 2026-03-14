package modelo;

/**
 * Representa una celda individual del tablero
 * Encapsula su estado: si tiene mina, si fue revelada, si esta marcada
 * y cuantas minas vecinas tiene
 */
public class Celda {

    private boolean tieneMina;
    private boolean revelada;
    private boolean marcada;
    private int minasVecinas;

    public Celda() {
        this.tieneMina = false;
        this.revelada = false;
        this.marcada = false;
        this.minasVecinas = 0;
    }

    //getteras y setters

    public boolean tieneMina() {
        return tieneMina;
    }

    public boolean estaRevelada() {
        return revelada;
    }

    public boolean estaMarcada() {
        return marcada;
    }

    public int getMinasVecinas() {
        return minasVecinas;
    }

    public void colocarMina() {
        this.tieneMina = true;
    }

    public void revelar() {
        this.revelada = true;
    }

    public void setMinasVecinas(int contador) {
        this.minasVecinas = contador;
    }

    public void cambiarMarca() {
        if (!revelada)
            this.marcada = !this.marcada;
    }

    //Consultar estado
        public boolean esSegura() {
        return !tieneMina && revelada;
    }

    public boolean estaOculta() {
        return !revelada && !marcada;
    }
}
