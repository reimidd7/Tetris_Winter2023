/*
 * TCSS 305A - Tetris Project
 *
 */

package view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

import model.*;

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

    /** Grid size for calculating grid dimensions. */
    private static final int GRID_SIDE = 20;

    /** Panel width constant. */
    private static final int PANEL_WIDTH = 200;

    /** Panel height constant. */
    private static final int PANEL_HEIGHT = 400;

    /** Board dimensions in with dimension class.*/
    private static final Dimension BOARD_SIZE = new Dimension(PANEL_WIDTH,
            PANEL_HEIGHT);

    /** UW Purple. */
    private static final Color UW_GOLD = new Color(145, 123, 76);

    /** UW Purple.*/
    private static final Color UW_PURPLE = new Color(51, 0, 111);

    /** Current Tetris Piece in motion. */
    private MovableTetrisPiece myCurrentPiece;

    private List<Block[]> myFrozenBlocks;

    /** Current Rotation. */
    private Rotation myRot;


    /**
     * Public constructor. Creates the tetris game board panel.
     */
    public BoardPanel() {
        super();
        setBackground(UW_PURPLE);
        setPreferredSize(BOARD_SIZE);
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
     *
     * @param theGraphics the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        DrawPieces draw = new DrawPieces();

        // Paint Grid.
        g2d.setPaint(UW_GOLD);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        for (int rows = 0; rows < getGridDimension().height; rows++) {
            for (int cols = 0; cols < getGridDimension().width ; cols++) {
                g2d.drawRect(cols * GRID_SIDE, rows * GRID_SIDE,
                        GRID_SIDE, GRID_SIDE);
            }
        }

        // Draw Pieces.
        if (myCurrentPiece != null) {
            int pX = myCurrentPiece.getPosition().x() * 20;
            int pY = myCurrentPiece.getPosition().y() * 20;

            g2d.rotate(Math.PI, 100, 200);
            g2d.translate(pX, pY);

            drawRotatedPiece(g2d, draw);
        }
    }

    /**
     * Draws the pieces with proper rotation.
     *
     * @param theG2d The Graphics2d for the paint compenent.
     * @param theDraw A reference to the draw pieces class to get the proper drawing.
     */
    private void drawRotatedPiece(final Graphics2D theG2d, final DrawPieces theDraw) {

        if (myCurrentPiece.getTetrisPiece() == TetrisPiece.O) {
            theDraw.drawO(theG2d);

        } else if (myCurrentPiece.getRotation() == Rotation.NONE) {
            System.out.println("none");
            switch (myCurrentPiece.getTetrisPiece()) {
                case I -> theDraw.drawI(theG2d);
                case J -> theDraw.drawJ(theG2d);
                case L -> theDraw.drawL(theG2d);
                case S -> theDraw.drawS(theG2d);
                case T -> theDraw.drawT(theG2d);
                case Z -> theDraw.drawZ(theG2d);
            }
        } else if (myCurrentPiece.getRotation() == Rotation.QUARTER) {
            System.out.println("1/4");
            switch (myCurrentPiece.getTetrisPiece()) {
                case I -> theDraw.drawRot1I(theG2d);
                case J -> theDraw.drawRot1J(theG2d);
                case L -> theDraw.drawRot1L(theG2d);
                case S -> theDraw.drawRot1S(theG2d);
                case T -> theDraw.drawRot1T(theG2d);
                case Z -> theDraw.drawRot1Z(theG2d);
            }
        } else if (myCurrentPiece.getRotation() == Rotation.HALF) {
            System.out.println("1/2");
            switch (myCurrentPiece.getTetrisPiece()) {
                case I -> theDraw.drawRot2I(theG2d);
                case J -> theDraw.drawRot2J(theG2d);
                case L -> theDraw.drawRot2L(theG2d);
                case S -> theDraw.drawRot2S(theG2d);
                case T -> theDraw.drawRot2T(theG2d);
                case Z -> theDraw.drawRot2Z(theG2d);
            }
        } else if (myCurrentPiece.getRotation() == Rotation.THREEQUARTER) {
            System.out.println("3/4");
            switch (myCurrentPiece.getTetrisPiece()) {
                case I -> theDraw.drawRot3I(theG2d);
                case J -> theDraw.drawRot3J(theG2d);
                case L -> theDraw.drawRot3L(theG2d);
                case S -> theDraw.drawRot3S(theG2d);
                case T -> theDraw.drawRot3T(theG2d);
                case Z -> theDraw.drawRot3Z(theG2d);
            }
        } else {
            System.out.println("4/4");
            switch (myCurrentPiece.getTetrisPiece()) {
                case I -> theDraw.drawI(theG2d);
                case J -> theDraw.drawJ(theG2d);
                case L -> theDraw.drawL(theG2d);
                case S -> theDraw.drawS(theG2d);
                case T -> theDraw.drawT(theG2d);
                case Z -> theDraw.drawZ(theG2d);
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
    public void propertyChange(final PropertyChangeEvent theEvent) {

        if (theEvent.getPropertyName().equals(Board.PROPERTY_PIECE_LOCATION)) {
            myCurrentPiece = (MovableTetrisPiece) theEvent.getNewValue();
            repaint();

        } else if (theEvent.getPropertyName().equals(Board.PROPERTY_CURRENT_PIECE)) {
            myCurrentPiece = (MovableTetrisPiece) theEvent.getNewValue();
            repaint();
        } else if (theEvent.getPropertyName().equals(Board.PROPERTY_ROTATIONAL)) {
            myRot = (Rotation) theEvent.getNewValue();
            repaint();
        } else if (theEvent.getPropertyName().equals(Board.PROPERTY_FROZEN_BLOCKS)) {
            List<Block[]> b = (List<Block[]>) theEvent.getNewValue();
            myFrozenBlocks = b;
            repaint();
        }
    }

}