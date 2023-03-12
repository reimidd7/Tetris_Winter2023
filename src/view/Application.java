/**
 * TCSS 305A - Tetris.
 *
 */

package view;

import model.Board;
import model.BoardInterface;

import javax.swing.*;

/**
 * Application class to kick off the Tetris game.
 *
 * @author Rick Adams
 * @author Calvin Beardemphl, Viktoria Dolojan, Tsion Fufa, Reilly Middlebrooks.
 * @version Winter 2023.
 */
public final class Application  {

    /**
     * Public constructor.
     */
    private Application() {
    }

    /**
     * Main driver method.
     * Calls the parent container, Frame().
     *
     * @param theArgs command-line arguments.
     */
    public static void main(final String[] theArgs) {
        SwingUtilities.invokeLater(Frame::createAndShowGUI);
    }
}