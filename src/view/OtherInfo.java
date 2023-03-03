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
 * This is the class that creates a panel to display
 * information to the user.
 * It is on the right side of the Tetris board.
 *
 * @author Calvin Beardemphl
 * @author Victoria Dolojan
 * @version Winter 2023
 */
public class OtherInfo extends JPanel {

    /** Width and height of the panel.*/
    private static final int PANEL_SIZE = 200;

    /** Constructor for OtherInfo panel. */
    public OtherInfo() {
        super();
        setBackground(Color.GREEN);
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        setBorder(new TitledBorder("Other Information"));
    }
}