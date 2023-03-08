/*
 * TCSS 305 - Tetris Project
 *
 */

package view;

import model.Score;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
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

    /**
     * The Score object.
     */
    private static Score myScore = new Score();

    /** Constructor for OtherInfo panel. */
    public OtherInfo() {
        super();
        setBackground(Color.GREEN);
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        setBorder(new TitledBorder("Other Information"));
        final JTextArea controlsText = new JTextArea(createTextBox());
        controlsText.setEditable(false);
        controlsText.setBackground(Color.GREEN);
        add(controlsText);
    }

    /**
     * Creates the Text to be displayed on
     * the OtherInfo panel.
     *
     * @return a String Text Block
     */
    private static String createTextBox() {
        // NOT DONE - JUST MOCK UP
        return """
         Controls:
         A/a/← moves piece left
         D/d/→ moves piece right
         S/s/↓ moves piece down
         W/w/↑ rotates piece
         ----------------------
         Score:\s""" + myScore.getScore() + """
         \nLevel:\s""" + myScore.getLevel() + """
         \nLines Cleared:\s""" + myScore.getLinesCleared();
        // this ^^^ is a text block that IntelliJ
        // recommended I do instead of a String or String builder
        // it looks nice to me!
        //
    }

}