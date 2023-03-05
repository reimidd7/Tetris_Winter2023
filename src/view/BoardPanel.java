/*
 * TCSS 305A - Tetris Project
 *
 */

package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import javax.swing.JPanel;
import model.Board;
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
    private Board myBoard;

    /**
     * Public constructor. Creates the tetris game board panel.
     */
    public BoardPanel() {
        super(true);
        setBackground(UW_PURPLE);
        setPreferredSize(BOARD_SIZE);

    }

    /**
     * Public constructor for instantiation.
     *
     * @param theBoard An instantiated Board object.
     */
    public BoardPanel(final Board theBoard) {
        this();
        myBoard = theBoard;
    }

    /**
     * "Accesser" method for the painted grid's dimensions.
     *
     * @return returns dimensions of the painted grid.
     */
    public static Dimension getGridDimension() {

        return new Dimension(BOARD_SIZE.height * GRID_SIDE + 1,
                BOARD_SIZE.width * GRID_SIDE + 1);
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

        for (int rows = 0; rows < getGridDimension().height; rows++) {
            for (int cols = 0; cols < getGridDimension().width; cols++) {
                g2d.drawRect(cols * GRID_SIDE,
                        rows * GRID_SIDE,
                        GRID_SIDE, GRID_SIDE);
            }
        }
//        repaint();
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param theEvent A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {

        if (myBoard != null) {
            if (theEvent.getPropertyName().equals(Board.PROPERTY_BOARD)) {
                myBoard = (Board) theEvent.getNewValue();
            }
            repaint();
        }
    }

}
