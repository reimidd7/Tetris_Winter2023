package viewer;

import java.awt.*;
import javax.swing.*;

public class NextPiece extends JPanel {

    /** Width and height of next piece panel. */
    private static final int SIZE = 150;

    /*
     * Creates the panel that displays the next tetris piece
     * @param theX x coordinate of panel
     * @param theY y coordinate of panel
     * @param theSize size of square panel
     */
    public static void createPiecePanel(final int theX, final int theY) {
        // creates a panel with blue background
        JPanel npFrame = new JPanel();
        npFrame.setBackground(Color.blue);
        npFrame.setBounds(theX, theY, SIZE, SIZE);

        // figure out how to display next tetris piece inside panel
    }
}