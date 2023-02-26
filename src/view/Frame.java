package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 * This class creates the Frame and GUI for the tetris game.
 *  It includes a menu bar at the top of the frame
 *
 * @author Reilly Middlebrooks
 * @author Tsion Fufa
 * @author Calvin Beardemphl, Viktoria Dolojan, Rick Adams
 * @version Winter 2023
 */
public class Frame {

    /** Width of frame. */
    private static final int WIDTH = 400;

    /** Height of frame. */
    private static final int HEIGHT = 450;

    /** Frame dimension. */
    private static final Dimension FRAME_DIMENSION = new Dimension(400, 450);

    /** Panel dimension. */
    private static final Dimension PANEL_DIMENSION = new Dimension(200, 400);

    ///Possible constructor needed later.///
    public Frame() {
        createAndShowGUI();
    }

    //Used to create the rough frame for our tetris project.
    public static JFrame createTetrisFrame(final int theWidth, final int theHeight) {
        final JFrame tFrame = new JFrame();
        tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tFrame.setSize(theWidth, theHeight); //set height and width
        tFrame.setVisible(true);

        tFrame.setJMenuBar(createFileMenu()); //add menu bar to the frame

        return tFrame;
    }

    //Create a file menu with event handlers.
    public static JMenuBar createFileMenu() {
        final JMenuBar menuBar = new JMenuBar();

        final JMenu menu = new JMenu("File");
        final JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(
                e -> {
                    System.exit(0);
                });
        menu.add(exit);
        menuBar.add(menu);

        final JMenu help = new JMenu("Help");
        final JMenuItem rules = new JMenuItem("Rules");
        rules.addActionListener(
                e -> {
                    JOptionPane.showMessageDialog(null, "Best of Luck");
                });
        help.add(rules);
        menuBar.add(help);

        final JMenu about = new JMenu("About");
        final JMenuItem abt = new JMenuItem("About Game");
        abt.addActionListener(
                e -> {
                    JOptionPane.showMessageDialog(null, "This is a tetris game");
                });
        about.add(abt);
        menuBar.add(about);

        return menuBar;
    }

    private static void createAndShowGUI() {
        final JFrame tetrisFrame = createTetrisFrame(WIDTH, HEIGHT);
        final NextPiece nextPiece = new NextPiece();
        final OtherInfo otherInfo = new OtherInfo();
        final BoardPanel boardPanel = new BoardPanel();

        // sets the min and max size of frame
        tetrisFrame.setLayout(new GridLayout(1, 2));
        tetrisFrame.setMinimumSize(FRAME_DIMENSION);
        //tetrisFrame.setMaximumSize(FRAME_DIMENSION);

        // creates panel for next piece panel on top of other info panel
        final JPanel eastInfo = new JPanel(new GridLayout(2, 1));
        eastInfo.add(nextPiece);
        eastInfo.add(otherInfo);

        // sets size of board panel and places it on the left of the frame
        boardPanel.setPreferredSize(PANEL_DIMENSION);
        tetrisFrame.add(boardPanel);

        // sets size of info panel and places it on the right of the frame
        eastInfo.setPreferredSize(PANEL_DIMENSION);
        tetrisFrame.add(eastInfo);

        tetrisFrame.setVisible(true);
    }
}