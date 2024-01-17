import java.util.Random;
class PalabraSecreta {
    private String[] palabras = { "java", "programacion", "desarrollo", "computadora", "ahorcado" };
    private String palabraSeleccionada;
    private String palabraAdivinada;

    public void seleccionarPalabraAleatoria() {
        Random random = new Random();
        int indice = random.nextInt(palabras.length);
        palabraSeleccionada = palabras[indice];
        palabraAdivinada = "_".repeat(palabraSeleccionada.length());
    }

    public boolean letraEnPalabra(char letra) {
        boolean encontrada = false;
        for (int i = 0; i < palabraSeleccionada.length(); i++) {
            if (palabraSeleccionada.charAt(i) == letra) {
                palabraAdivinada = palabraAdivinada.substring(0, i) + letra + palabraAdivinada.substring(i + 1);
                encontrada = true;
            }
        }
        return encontrada;
    }

    public boolean palabraAdivinada() {
        return palabraAdivinada.equals(palabraSeleccionada);
    }

    public String obtenerPalabraOculta() {
        return palabraAdivinada;
    }

    public String obtenerPalabra() {
        return palabraSeleccionada;
    }
}