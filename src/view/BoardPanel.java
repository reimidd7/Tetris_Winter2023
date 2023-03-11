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
import model.MovableTetrisPiece;
import model.TetrisPiece;

/**
 * group1-tetris game board.
 *
 * @author rick_adams.
 * @version Winter 2023.
 */
public class BoardPanel extends JPanel implements PropertyChangeListener {
    /**
     * Serial generated for version UID.
     */
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
    private MovableTetrisPiece myCurrentPiece;
    /**
     * Board dimensions in with dimension class.
     */
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
     * Public constructor. Creates the tetris game board panel.
     */
    public BoardPanel() {
        super();
        setBackground(UW_PURPLE);
        setPreferredSize(BOARD_SIZE);
        //myBoard.newGame();
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

        g2d.setPaint(UW_GOLD);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        for (int rows = 0; rows < getGridDimension().height; rows++) {
            for (int cols = 0; cols < getGridDimension().width ; cols++) {
                g2d.drawRect(cols * GRID_SIDE, rows * GRID_SIDE,
                        GRID_SIDE, GRID_SIDE);
            }
        }




        if (myCurrentPiece != null) {
            int pX = myCurrentPiece.getPosition().x() * 20;

            int pY = myCurrentPiece.getPosition().y() * 20;

            DrawPieces draw = new DrawPieces();

            g2d.rotate(Math.PI, 100,200);


            switch (myCurrentPiece.getTetrisPiece()) {
                case I -> {
                    g2d.translate(pX,pY);
                    //g2d.translate(myCurrentPiece.getPosition().x() * 20 - 20, myCurrentPiece.getPosition().y() * 20);
                    draw.drawI(g2d);
                }
                case J -> {
                    g2d.translate(pX,pY);
                    //g2d.translate(myCurrentPiece.getPosition().x() * 20- 20, myCurrentPiece.getPosition().y() * 20);
                    draw.drawJ(g2d);
                }
                case L -> {
                    g2d.translate(pX,pY);
                    //g2d.translate(myCurrentPiece.getPosition().x() * 20- 20, myCurrentPiece.getPosition().y() * 20);
                    draw.drawL(g2d);
                }
                case O -> {
                    g2d.translate(pX,pY);
                    //g2d.translate(myCurrentPiece.getPosition().x() * 20- 20, myCurrentPiece.getPosition().y() * 20);
                    draw.drawO(g2d);
                }
                case S -> {
                    g2d.translate(pX,pY);
                    //g2d.translate(myCurrentPiece.getPosition().x() * 20- 20, myCurrentPiece.getPosition().y() * 20);
                    draw.drawS(g2d);
                }
                case T -> {
                    g2d.translate(pX,pY);
                    //g2d.translate(myCurrentPiece.getPosition().x() * 20- 20, myCurrentPiece.getPosition().y() * 20);
                    draw.drawT(g2d);
                }
                case Z -> {
                    g2d.translate(pX,pY);
                    //g2d.translate(myCurrentPiece.getPosition().x() * 20- 20, myCurrentPiece.getPosition().y() * 20);
                    draw.drawZ(g2d);
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
    public void propertyChange(final PropertyChangeEvent theEvent) {
        //if (myBoard != null) {
        if (theEvent.getPropertyName().equals(Board.PROPERTY_PIECE_LOCATION)) {


            myCurrentPiece = (MovableTetrisPiece) theEvent.getNewValue();
            repaint();


    }  //else if (theEvent.getPropertyName().equals(Board.PROPERTY_CURRENT_PIECE)) {
//            myCurrentPiece = (MovableTetrisPiece) theEvent.getNewValue();
//            repaint();
//        }
    }
}