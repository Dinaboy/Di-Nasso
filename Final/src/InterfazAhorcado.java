import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazAhorcado extends JFrame {

    private PalabraSecreta palabraSecreta;
    private Jugador jugador;

    private JLabel palabraOcultaLabel;
    private JLabel letrasAdivinadasLabel;
    private JLabel oportunidadesRestantesLabel;
    private JTextField letraTextField;
    private JButton adivinarButton;

    public InterfazAhorcado() {
        super("Juego del Ahorcado");
        palabraSecreta = new PalabraSecreta();
        jugador = new Jugador();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        palabraOcultaLabel = new JLabel("Palabra: " + palabraSecreta.obtenerPalabraOculta());
        letrasAdivinadasLabel = new JLabel("Letras Adivinadas: ");
        oportunidadesRestantesLabel = new JLabel("Oportunidades restantes: " + jugador.obtenerOportunidadesRestantes());
        letraTextField = new JTextField(1);
        adivinarButton = new JButton("Adivinar");

        setLayout(new GridLayout(5, 1));

        add(palabraOcultaLabel);
        add(letrasAdivinadasLabel);
        add(oportunidadesRestantesLabel);
        add(letraTextField);
        add(adivinarButton);

        adivinarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarAdivinanza();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void procesarAdivinanza() {
        char letra = letraTextField.getText().toLowerCase().charAt(0);

        if (!jugador.haPerdido() && !palabraSecreta.palabraAdivinada()) {
            boolean acierto = palabraSecreta.letraEnPalabra(letra);

            if (!acierto) {
                jugador.disminuirOportunidades();
            }

            actualizarInterfaz();

            if (jugador.haPerdido() || palabraSecreta.palabraAdivinada()) {
                mostrarResultado();
            }
        }
    }

    private void actualizarInterfaz() {
        palabraOcultaLabel.setText("Palabra: " + palabraSecreta.obtenerPalabraOculta());
        letrasAdivinadasLabel.setText("Letras Adivinadas: " + jugador.obtenerLetrasAdivinadas());
        oportunidadesRestantesLabel.setText("Oportunidades restantes: " + jugador.obtenerOportunidadesRestantes());
    }

    private void mostrarResultado() {
        if (palabraSecreta.palabraAdivinada()) {
            JOptionPane.showMessageDialog(this, "¡Felicidades! Has adivinado la palabra.");
        } else {
            JOptionPane.showMessageDialog(this, "¡Oh no! Has perdido. La palabra era: " + palabraSecreta.obtenerPalabra());
        }
        reiniciarJuego();
    }

    private void reiniciarJuego() {
        palabraSecreta.seleccionarPalabraAleatoria();
        jugador = new Jugador();
        actualizarInterfaz();
    }

    public PalabraSecreta getPalabraSecreta() {
        return palabraSecreta;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InterfazAhorcado interfazAhorcado = new InterfazAhorcado();
                interfazAhorcado.getPalabraSecreta().seleccionarPalabraAleatoria();  // Asegurar la inicialización
            }
        });
    }
}
