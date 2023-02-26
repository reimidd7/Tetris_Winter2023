package view;

import java.io.Serial;
import javax.swing.JFrame;

/**
 * Application for the board panel testing.
 *
 * @author rick_adams.
 * @version 2023 Winter.
 */
public class Application extends JFrame {

    /** Serial generated for version UID. */
    @Serial
    private static final long serialVersionUID = 1942683769738516682L;

    /**
     * Public constructor.
     */
    public Application() {
        super();
    }

    /**
     * Main method.
     * Strictly for testing.
     *
     * @param theArgs command-line arguments.
     */
    public static void main(final String[] theArgs) {
        new Frame();
    }
}