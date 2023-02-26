/*
 * TCSS 305 - Tetris Project
 *
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * This class creates a panel to displays the next tetris piece to the user
 * on the top right of the game.
 *
 * @author Viktoria Dolojan
 * @version 1.3
 */
public class NextPiece extends JPanel {

    /** Width and height of the panel. */
    private static final int PANEL_SIZE = 200;

    /** Constructor for NextPiece panel. */
    public NextPiece() {
        super();
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        setBorder(new TitledBorder("Next Tetris Piece"));
    }
}