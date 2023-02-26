package view;

import java.io.Serial;
import javax.swing.JFrame;

/**
 * Application class to kick off the Tetris game.
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
     *
     */
    public Application() {
        super();
    }

    /**
     * Main driver method.
     * Calls the parent container, Frame().
     *
     * @param theArgs command-line arguments.
     */
    public static void main(final String[] theArgs) {
        new Frame();
    }
}
