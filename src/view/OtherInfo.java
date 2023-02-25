/*
 * TCSS 305 - Tetris Project
 *
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This is the class that creates a panel to display
 * information to the user.
 * It is on the right side of the Tetris board.
 *
 * @author Calvin Beardemphl
 * @version 1.0
 */
public class OtherInfo {



    /** The preferred height of the panel.*/
    private static final int PREFERRED_HEIGHT = 250;
    /** The preferred width of the panel.*/
    private static final int PREFERRED_WIDTH = 250;

    /** The Panel myInfo that will hold the information for the User.*/
    private static JPanel myInfo;

    /** Constructor so Checkstyle doesn't yell at me. */
    public OtherInfo() { }


    /**
     * This method creates the information panel.
     * It sets the color of the panel, and the preferred size
     * of the panel.
     */
    private static void createOtherInfoPanel() {
        myInfo = new JPanel();
        myInfo.setBackground(Color.GREEN);
        myInfo.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
    }

    // TODO: add to frame

    // main just for testing!
    public static void main(final String[] theArgs) {
        final JFrame frame = new JFrame();
        // using the rough frame size
        frame.setSize(new Dimension(1000, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        createOtherInfoPanel();
        // adding the panel on the right side to test
        frame.add(myInfo, BorderLayout.EAST);
        frame.setVisible(true);
    }


}
