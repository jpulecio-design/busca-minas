package util;

import java.util.Scanner;

/**
 * Lee y valida la entrada del usuario desde consola
 */
public class LectorEntrada {

    private final Scanner scanner;

    public LectorEntrada() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Lee un entero en el rango [min, max]. Repite hasta recibir un valor valido
     */
    public int leerEntero(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor >= min && valor <= max)
                    return valor;
                System.out.println("  Ingresa un numero entre " + min + " y " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("  Entrada invalida, ingresa un numero entero");
            }
        }
    }

    // Lee D (destapar) o M (marcar)
    public char leerAccion() {
        while (true) {
            System.out.print("Accion [D=Destapar / M=Marcar]: ");
            String entrada = scanner.nextLine().trim().toUpperCase();
            if (entrada.equals("D") || entrada.equals("M"))
                return entrada.charAt(0);
            System.out.println("  Ingresa D o M.");
        }
    }

    /** Lee S o N. */
    public boolean leerSiNo(String prompt) {
        while (true) {
            System.out.print(prompt + " [S/N]: ");
            String entrada = scanner.nextLine().trim().toUpperCase();
            if (entrada.equals("S"))
                return true;
            if (entrada.equals("N"))
                return false;
            System.out.println("  Ingresa S o N.");
        }
    }

    public void cerrar() {
        scanner.close();
    }
}
