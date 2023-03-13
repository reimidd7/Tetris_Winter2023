
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import java.util.List;
import javax.swing.JPanel;
import model.Block;
import model.Board;
import model.MovableTetrisPiece;
import model.Rotation;
import model.TetrisPiece;

/**
 * group1-tetris game board.
 *
 * @author rick_adams.
 * @author Reilly Middlebrooks, Calvin Beardemphl, Viktoria Dolojan
 * @version Winter 2023.
 */
public class BoardPanel extends JPanel implements PropertyChangeListener {
    /** Serial generated for version UID. */
    @Serial
    private static final long serialVersionUID = 5122343764710334165L;

    /**
     * Grid size for calculating grid dimensions.
     */
    private static final int PIECE_SIZE = 20;

    /**
     * Panel width constant.
     */
    private static final int PANEL_WIDTH = 200;
    /** X coordinate for the rotation. */
    private static final int PI_X = 100;
    /** Y coordinate for the rotation. */
    private static final int PI_Y = 100;
    /**
     * Panel height constant.
     */
    private static final int PANEL_HEIGHT = 400;
    /** Offset for piece placement. */
    private static final int OFFSET = -1;

    private static final int ROW = -1;

    private static final int COL = -1;
    /** Board dimensions in with dimension class.*/
    private static final Dimension BOARD_SIZE = new Dimension(PANEL_WIDTH,
            PANEL_HEIGHT);

    /**
     * UW Purple.
     */
    private static final Color UW_GOLD = new Color(145, 123, 76);
    /**
     * UW Purple.
     */
    private static final Color UW_PURPLE = new Color(51, 0, 111);
    /**
     * The current tetris piece in play.
     */
    private MovableTetrisPiece myCurrentPiece;
    /** DrawPieces object. */
    private final DrawPieces myDraw;
    /**
     * A list of all the frozen blocks currently on the board.
     */
    private List<Block[]> myFrozenBlocks;



    /**
     * Public constructor. Creates the tetris game board panel.
     */
    public BoardPanel() {
        super();
        setBackground(UW_PURPLE);
        myDraw = new DrawPieces();
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
     * Helper to draw the grid.
     *
     * @param theGrid the grid in Graphics2D.
     */
    private void drawGrid(final Graphics2D theGrid) {
        for (int rows = 0; rows < getGridDimension().height; rows++) {
            for (int cols = 0; cols < getGridDimension().width; cols++) {
                theGrid.drawRect(cols * PIECE_SIZE, rows * PIECE_SIZE,
                        PIECE_SIZE, PIECE_SIZE);
            }
        }
    }
    /**
     * Helper to draw the pieces.
     *
     * @param thePieces the pieces in Graphics2D.
     */
    private void drawPieces(final Graphics2D thePieces) {
        // attempt at drawing frozen blocks
        thePieces.rotate(Math.PI, PI_X, PI_Y);

        int ROW = OFFSET;
        for (final Block[] blockArr : myFrozenBlocks) {
            ROW++;
            if (blockArr != null) {
                int COL = OFFSET; // use -4 to center blocks
                for (final Block block : blockArr) {
                    COL++;
                    if (block != null) {
                        thePieces.setPaint(Color.GRAY);
                        thePieces.fillRect(COL * PIECE_SIZE,
                                ROW * PIECE_SIZE, PIECE_SIZE, PIECE_SIZE);
                        thePieces.setPaint(Color.BLACK);
                        thePieces.drawRect(COL * PIECE_SIZE,
                                ROW * PIECE_SIZE, PIECE_SIZE, PIECE_SIZE);
                    }
                }
            }
        }
    }





    /**
     * Overrides swings paintComponent to draw a simple grid on a JPanel.
     *
     * @param theGraphics the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // Paint Grid.
        g2d.setPaint(UW_GOLD);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawGrid(g2d);

        // attempt at drawing frozen blocks
        if (myFrozenBlocks != null) {

            drawPieces(g2d);
//            // attempt at drawing frozen blocks
        }
        //Draw Moving Pieces.
        final int piecesX = myCurrentPiece.getPosition().x() * PIECE_SIZE;
        final int piecesY = myCurrentPiece.getPosition().y() * PIECE_SIZE;
        g2d.translate(piecesX, piecesY);
        drawRotatedPiece(g2d, myDraw);
    }


    /**
     * Draws the pieces with proper rotation.
     *
     * @param theG2d The Graphics2D for the paint component.
     * @param theDraw A reference to the draw pieces class to get the proper drawing.
     */
    private void drawRotatedPiece(final Graphics2D theG2d, final DrawPieces theDraw) {

        // Draw Pieces.
        if (myCurrentPiece != null) {
            if (myCurrentPiece.getTetrisPiece() == TetrisPiece.O) {
                theDraw.drawO(theG2d);

            }
            if (myCurrentPiece.getRotation() == Rotation.NONE) {
                switch (myCurrentPiece.getTetrisPiece()) {
                    case I -> theDraw.drawI(theG2d);
                    case J -> theDraw.drawJ(theG2d);
                    case L -> theDraw.drawL(theG2d);
                    case S -> theDraw.drawS(theG2d);
                    case T -> theDraw.drawT(theG2d);
                    case Z -> theDraw.drawZ(theG2d);
                    default -> throw new IllegalStateException("Unexpected value: "
                            + myCurrentPiece.getTetrisPiece());
                }

            }
            if (myCurrentPiece.getRotation() == Rotation.QUARTER) {
                switch (myCurrentPiece.getTetrisPiece()) {
                    case I -> theDraw.drawRot1I(theG2d);
                    case J -> theDraw.drawRot1J(theG2d);
                    case L -> theDraw.drawRot1L(theG2d);
                    case S -> theDraw.drawRot1S(theG2d);
                    case T -> theDraw.drawRot1T(theG2d);
                    case Z -> theDraw.drawRot1Z(theG2d);
                    default -> throw new IllegalStateException("Unexpected value: "
                            + myCurrentPiece.getTetrisPiece());
                }
            }
            if (myCurrentPiece.getRotation() == Rotation.HALF) {
                switch (myCurrentPiece.getTetrisPiece()) {
                    case I -> theDraw.drawRot2I(theG2d);
                    case J -> theDraw.drawRot2J(theG2d);
                    case L -> theDraw.drawRot2L(theG2d);
                    case S -> theDraw.drawRot2S(theG2d);
                    case T -> theDraw.drawRot2T(theG2d);
                    case Z -> theDraw.drawRot2Z(theG2d);
                    default -> throw new IllegalStateException("Unexpected value: "
                            + myCurrentPiece.getTetrisPiece());

                }

            }
            if (myCurrentPiece.getRotation() == Rotation.THREEQUARTER) {
                switch (myCurrentPiece.getTetrisPiece()) {
                    case I -> theDraw.drawRot3I(theG2d);
                    case J -> theDraw.drawRot3J(theG2d);
                    case L -> theDraw.drawRot3L(theG2d);
                    case S -> theDraw.drawRot3S(theG2d);
                    case T -> theDraw.drawRot3T(theG2d);
                    case Z -> theDraw.drawRot3Z(theG2d);
                    default -> throw new IllegalStateException("Unexpected value: "
                            + myCurrentPiece.getTetrisPiece());

                }

            } else {
                switch (myCurrentPiece.getTetrisPiece()) {
                    case I -> theDraw.drawI(theG2d);
                    case J -> theDraw.drawJ(theG2d);
                    case L -> theDraw.drawL(theG2d);
                    case S -> theDraw.drawS(theG2d);
                    case T -> theDraw.drawT(theG2d);
                    case Z -> theDraw.drawZ(theG2d);
                    default -> throw new IllegalStateException("Unexpected value: "
                            + myCurrentPiece.getTetrisPiece());
                }
            }
        }

    }
        /**
         * This method gets called when a bound property is changed.
         *
         * @param theEvent A PropertyChangeEvent object describing the event source
         *                 and the property that has changed.
         */
        @Override
        public void propertyChange ( final PropertyChangeEvent theEvent){
            if (theEvent.getPropertyName().equals(Board.PROPERTY_CURRENT_PIECE)) {
                myCurrentPiece = (MovableTetrisPiece) theEvent.getNewValue();
                repaint();
            }
            if (theEvent.getPropertyName().equals(Board.PROPERTY_FROZEN_BLOCKS)) {
                myFrozenBlocks = (List<Block[]>) theEvent.getNewValue();
                repaint();
            }
        }
    }

