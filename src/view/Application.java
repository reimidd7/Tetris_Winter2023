package view;

import java.awt.*;
import java.io.Serial;
import javax.swing.*;

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
        setSize(FRAME_SIZE);
       
        final JPanel panel = new JPanel(new BorderLayout());
        final NextPiece nextPiece = new NextPiece();
        final OtherInfo otherInfo = new OtherInfo();
        final BoardPanel boardPanel = new BoardPanel();
        panel.add(nextPiece, BorderLayout.LINE_END);
        panel.add(otherInfo, BorderLayout.LINE_START);
        panel.add(boardPanel, BorderLayout.CENTER);
        add(panel);

    }

    /**
     * Main method.
     * Strictly for testing.
     *
     * @param theArgs command-line arguments.
     */
    public static void main(final String[] theArgs) {

        new Application().setVisible(true);
    }


}
