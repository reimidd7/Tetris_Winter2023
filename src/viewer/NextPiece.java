package viewer;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class creates a panel to displays the next tetris piece to the user
 * on the top right of the game.
 *
 * @author Viktoria Dolojan
 * @version 1.1
 */
public class NextPiece extends JPanel {

    /** Width and height of panel. */
    private static final int SIZE = 250;

    /** Panel that displays the next tetris piece. */
    private static JPanel myNextPiece;

    /*
     * Creates the panel that displays the next tetris piece
     * @param theX x coordinate of panel
     * @param theY y coordinate of panel
     * @param theSize size of square panel
     */
    private static void createPiecePanel() {
        myNextPiece = new JPanel();
        myNextPiece.setBackground(Color.BLUE);
        myNextPiece.setBounds(SIZE, 0, SIZE, SIZE);
        // TODO: display next tetris piece inside panel
    }

    // TODO: add panel to frame

    // main for testing
    public static void main(final String[] theArgs) {
        final JFrame frame = new JFrame();
        final int width = 500;
        final int height = 1000;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(new Dimension(width, height));
        frame.setVisible(true);
        createPiecePanel();
        frame.add(myNextPiece);
    }
}