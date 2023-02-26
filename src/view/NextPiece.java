package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class creates a panel to displays the next tetris piece to the user
 * on the top right of the game.
 *
 * @author Viktoria Dolojan
 * @version 1.2
 */
public class NextPiece extends JPanel {

    /** Width and height of panel. */
    private static final int SIZE = 250;

    /** Panel that displays the next tetris piece. */
    private static JPanel myNextPiecePanel;

    public NextPiece () {
        super();
        createPiecePanel();
    }

    /** Creates the panel that displays the next tetris piece. */
    private static void createPiecePanel() {
        myNextPiecePanel = new JPanel();
        myNextPiecePanel.setBackground(Color.BLUE);
//        myNextPiecePanel.setBounds(SIZE, 0, SIZE, SIZE);
        myNextPiecePanel.setPreferredSize(new Dimension(SIZE, SIZE));

        myNextPiecePanel.setVisible(true);
        // TODO: display next tetris piece inside panel
        // Note: model.Board class has a private field which holds the next tetris piece
    }

    // TODO: add panel to frame

    // main for testing
//    public static void main(final String[] theArgs) {
//        final JFrame frame = new JFrame();
//        final int width = 500;
//        final int height = 1000;
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(null);
//        frame.setSize(new Dimension(width, height));
//        frame.setVisible(true);
//        createPiecePanel();
//        frame.add(myNextPiecePanel);
//    }
}