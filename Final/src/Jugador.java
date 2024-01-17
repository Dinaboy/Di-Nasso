import java.util.Scanner;

class Jugador {
    private String letrasAdivinadas = "";
    private int oportunidades = 6;
    private Scanner scanner = new Scanner(System.in);

    public char adivinarLetra() {
        System.out.print("Adivina una letra: ");
        String entrada = scanner.nextLine();
        if (entrada.length() > 0) {
            return entrada.charAt(0);
        } else {
            return adivinarLetra();
        }
    }

    public void disminuirOportunidades() {
        oportunidades--;
    }

    public void registrarLetraAdivinada(char letra) {
        letrasAdivinadas += letra;
    }

    public String obtenerLetrasAdivinadas() {
        return letrasAdivinadas;
    }

    public int obtenerOportunidadesRestantes() {
        return oportunidades;
    }

    public boolean haPerdido() {
        return oportunidades <= 0;
    }
}