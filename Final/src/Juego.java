class Juego {
    private PalabraSecreta palabraSecreta;
    //tipo: private solo accesible dentro de juego
    private Jugador jugador;

    public Juego() {
        palabraSecreta = new PalabraSecreta();
        jugador = new Jugador();
    }

    public void iniciarJuego() {
        System.out.println("¡Bienvenido al Juego del Ahorcado!");
        palabraSecreta.seleccionarPalabraAleatoria();

        while (!jugador.haPerdido() && !palabraSecreta.palabraAdivinada()) {
            System.out.println("Palabra: " + palabraSecreta.obtenerPalabraOculta());
            System.out.println("Letras adivinadas: " + jugador.obtenerLetrasAdivinadas());
            System.out.println("Oportunidades restantes: " + jugador.obtenerOportunidadesRestantes());

            char letra = jugador.adivinarLetra();
            if (!palabraSecreta.letraEnPalabra(letra)) {
                jugador.disminuirOportunidades();
            }
            jugador.registrarLetraAdivinada(letra);
        }

        if (palabraSecreta.palabraAdivinada()) {
            System.out.println("¡Felicidades, has ganado! La palabra era: " + palabraSecreta.obtenerPalabra());
        } else {
            System.out.println("¡Has perdido! La palabra era: " + palabraSecreta.obtenerPalabra());
        }
    }
}
