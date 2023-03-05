package view;

import model.Board;
import model.BoardInterface;
import model.MovableTetrisPiece;
import model.Point;
import model.TetrisPiece;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

/**
 * This class creates the Frame and GUI for the tetris game.
 *  It includes a menu bar at the top of the frame
 *
 * @author Reilly Middlebrooks
 * @author Tsion Fufa
 * @author Calvin Beardemphl, Viktoria Dolojan, Rick Adams
 * @version Winter 2023
 */
public class Frame extends JFrame implements PropertyChangeListener {

    /** Width of frame. */
    private static final int WIDTH = 400;

    /** Height of frame. */
    private static final int HEIGHT = 450;

    /** Frame dimension. */
    private static final Dimension FRAME_DIMENSION = new Dimension(400, 450);

    /** Panel dimension. */
    private static final Dimension PANEL_DIMENSION = new Dimension(200, 400);

    /** Time Constant. */ // TODO: Large only for testing purposes. Make smaller.
    private static final int TIME_CONST = 60000;

    /** Board Object. */
    private final BoardInterface myBoard;

    /** Movable Tetris Piece object. */
    private MovableTetrisPiece myCurrentPiece; //TODO: Need to find a way to instantiate. For use in property change method


    ///Possible constructor needed later.///
    public Frame(final BoardInterface theBoard) {
        super();

        //Create a board/model object from interface
        myBoard = theBoard;

        //myCurrentPiece = ;//TODO: How to instantiate?


        // Create the frame for the tetris game (aka the top most "panel")
        createTetrisFrame(WIDTH, HEIGHT);

        //add KeyListeners to the frame
        addKeyListener(new BoardKeyListener());

        setFocusable(true);
        requestFocus();
    }

    //Used to create the rough frame for our tetris project.
    public void createTetrisFrame(final int theWidth, final int theHeight) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(theWidth, theHeight); //set height and width
        setVisible(true);

        setJMenuBar(createFileMenu()); //add menu bar to the frame

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

    public static void createAndShowGUI() {
        final Board board = new Board();

        final Frame tetrisFrame = new Frame(board);
        board.addPropertyChangeListener(tetrisFrame);

        final NextPiece nextPiece = new NextPiece();
        final OtherInfo otherInfo = new OtherInfo();
        final BoardPanel boardPanel = new BoardPanel();

        board.addPropertyChangeListener(boardPanel);
        board.addPropertyChangeListener(nextPiece);

        // instantiate the timer and set the delay to 500ms
        final Timer timer = new Timer(TIME_CONST,
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        // call the appropriate method from the
                        //      Interface defined in Model Update.
                        new Board().step(); // TODO: Is step() the correct method to do... could be down()
                    }
                });

        // start the timer
        timer.start();

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

        tetrisFrame.pack();
        tetrisFrame.setVisible(true);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Board.PROPERTY_CURRENT_PIECE)) {
            final model.Point temp = (model.Point) evt.getNewValue();
            this.myCurrentPiece.getPosition().transform(temp); //TODO: This is the line that uses myCurrentPiece
            repaint();
        }
    }

    /**
     * Inner class for key listeners in order to move the piece.
     *
     * @author Reilly Middlebrooks
     */
    class BoardKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            System.out.println("key pressed");
            if (theEvent.getKeyCode() == KeyEvent.VK_A
                    || theEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                myBoard.left();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_D
                    || theEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                myBoard.right();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_W
                    || theEvent.getKeyCode() == KeyEvent.VK_UP) {
                //Do I need to make this different for each piece some CCW and some CW
                myBoard.rotateCW();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_S
                    || theEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                myBoard.down();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                myBoard.drop();
            }
        }
    }

} // end of frame class