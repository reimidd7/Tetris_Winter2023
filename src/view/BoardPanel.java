package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serial;
import javax.swing.JPanel;

/**
 * group1-tetris game board.
 *
 * @author rick_adams.
 * @version Winter 2023.
 */
public class BoardPanel extends JPanel {
    /** Serial generated for version UID. */
    @Serial
    private static final long serialVersionUID = 5122343764710334165L;
    /** Constant row variable for the grid dimensions. */
    private static final int ROWS = 40;
    /** Constant column variable for the grid dimensions. */
    private static final int COLUMNS = 20;
    /**
     * Constant variable for grid calculation.
     * Represents the x-coordinate.
     */
    private static final int X_ORIGIN = 0;
    /**
     * Constant variable for grid calculation.
     * Represents the y-coordinate.
     */
    private static final int Y_ORIGIN = 0;
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

    /**
     * Public constructor.
     */
    public BoardPanel() {
        super();
        setBackground(Color.RED);
        setSize(BOARD_SIZE);
        setMaximumSize(BOARD_SIZE);
    }
    /**
     * Overrides swings paintComponent to draw a simple grid on a JPanel.
     * @param theGraphics the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);

        for (int i = 0; i < ROWS + 1; i++) {
            theGraphics.drawLine(X_ORIGIN, Y_ORIGIN + i * GRID_SIDE,
                    X_ORIGIN + COLUMNS * GRID_SIDE, Y_ORIGIN + i * GRID_SIDE);

            for (int j = 0; j < COLUMNS + 1; j++) {
                theGraphics.drawLine(X_ORIGIN + j * GRID_SIDE, Y_ORIGIN ,
                        X_ORIGIN + j * GRID_SIDE, Y_ORIGIN + ROWS * GRID_SIDE);
            }
        }
    }
}
