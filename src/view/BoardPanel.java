/*
 * TCSS 305A - Tetris Project
 *
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import javax.swing.JPanel;
import model.Board;
import model.BoardInterface;
import model.MovableTetrisPiece;

/**
 * group1-tetris game board.
 *
 * @author rick_adams.
 * @version Winter 2023.
 */
public class BoardPanel extends JPanel implements PropertyChangeListener {
    /** Serial generated for version UID. */
    @Serial
    private static final long serialVersionUID = 5122343764710334165L;
    /**
     * Grid size for calculating grid dimensions.
     */
    private static final int GRID_SIDE = 20;
    /**
     * Panel width constant.
     */
    private static final int PANEL_WIDTH = 200;
    /**
     * Panel height constant.
     */
    private static final int PANEL_HEIGHT = 400;
    /** Size of pieces. */
    private static final int PIECE_SIZE = 20;
    /**
     * Board dimensions in with dimension class.
     */
    private static final Dimension BOARD_SIZE = new Dimension(PANEL_WIDTH,
                                                              PANEL_HEIGHT);

    /** UW Purple. */
    private static final Color UW_GOLD = new Color(145, 123, 76);
    /** UW Purple. */
    private static final Color UW_PURPLE = new Color(51, 0, 111);
    /** Board object for instantiation. */
    private BoardInterface myBoard;
    /** on/off switch for the grid. */
    private boolean myGridSwitch;
    /** GAme status. */
    private boolean myGameStatus;
    /** Counter for check. */
    private int myCount;
    /** Movable TetrisPiece object. */
    private MovableTetrisPiece myCurrentPiece;
    /**
     * Public constructor. Creates the tetris game board panel.
     */
    public BoardPanel() {
        super(true);
        setBackground(UW_PURPLE);
        myGridSwitch = true;
        myGameStatus = true;
        if (myCount > 0) {
            throw new IllegalArgumentException("There can be only one! (BoardPanel)");
        }
        myCount++;
    }

    /**
     * Private constructor for instantiation.
     *
     * @param theBoard An instantiated Board object.
     */
    private BoardPanel(final BoardInterface theBoard) {
        this();
        myBoard = theBoard;
    }

    /**
     * Sets the grid on or off.
     *
     * @param theSwitch a boolean switch.
     */
    private void setMyGridSwitch(final boolean theSwitch) {
        myGridSwitch = theSwitch;
    }

    private void setMyGameStatus(final boolean theStatus) {
        myGameStatus = theStatus;
    }
    /**
     * "Accessor" method for the painted grid's dimensions.
     *
     * @return returns dimensions of the painted grid.
     */
    public static Dimension getGridDimension() {
        return BOARD_SIZE;
    }

    /**
     * Overrides swings paintComponent to draw a simple grid on a JPanel.
     * @param theGraphics the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);

        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setPaint(UW_GOLD);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (myGridSwitch && myGameStatus) {
            for (int rows = 0; rows < getGridDimension().height; rows++) {
                for (int cols = 0; cols < getGridDimension().width; cols++) {
                    g2d.drawRect(cols * GRID_SIDE,
                            rows * GRID_SIDE,
                            GRID_SIDE, GRID_SIDE);
                }
                repaint();
            }

        }
    }
    /**
     * This method gets called when a bound property is changed.
     *
     * @param theEvent A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {


        if (myBoard != null && myGameStatus) {
            if (Board.PROPERTY_CURRENT_PIECE.equals(theEvent.getPropertyName())) {
//                final Point locale = (Point) theEvent.getNewValue();
                myCurrentPiece = (MovableTetrisPiece) theEvent.getNewValue();
                // tbd
//                myCurrentPiece.set(locale.x * PIECE_SIZE, locale.getY() * PIECE_SIZE,
//                        PIECE_SIZE, PIECE_SIZE);
                myBoard = (BoardInterface) theEvent.getNewValue();
            } else if (Board.PROPERTY_BOARD.equals(theEvent.getPropertyName())) {

                myBoard = (BoardInterface) theEvent.getNewValue();
            }
            repaint();
        }
    }
}