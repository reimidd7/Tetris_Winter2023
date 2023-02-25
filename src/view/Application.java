package view;

import java.awt.Dimension;
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
     /** Frame width constant.*/
    private static final int FRAME_WIDTH = 200;
    /** Frame height constant.*/
    private static final int FRAME_HEIGHT = 400;
    /**
     * Frames dimensions with dimension class.
     */
    private static final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH,
                                                              FRAME_HEIGHT);
    /**
     * Public constructor.
     */
    public Application() {
        super();

        final JFrame mainFrame = new JFrame();
        mainFrame.setSize(FRAME_SIZE);
        mainFrame.setMaximumSize(FRAME_SIZE);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        final BoardPanel boardPanel = new BoardPanel();

        mainFrame.add(boardPanel);
        mainFrame.setVisible(true);
    }

    /**
     * Main method.
     * Strictly for testing.
     *
     * @param theArgs command-line arguments.
     */
    public static void main(final String[] theArgs) {
        new Application();
    }
}
