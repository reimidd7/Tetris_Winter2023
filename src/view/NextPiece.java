/*
 * TCSS 305 - Tetris Project
 *
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import model.Block;
import model.TetrisPiece;

/**
 * This class creates a panel to displays the next tetris piece to the user
 * on the top right of the game.
 *
 * @author Viktoria Dolojan
 * @version Winter 2023
 */
public class NextPiece extends JPanel implements PropertyChangeListener {

    /** Width and height of the panel. */
    private static final int PANEL_SIZE = 200;

    /** Next Tetris Piece to be played. */
    private TetrisPiece myNextPiece;

    /** Constructor for NextPiece panel. */
    public NextPiece() {
        super();
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        setBorder(new TitledBorder("Next Tetris Piece"));
    }

    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

//        g2d.setPaint(Color.PINK);
//        g2d.drawRect(25 , 25 , 25, 25);

        // TODO: draw tetris piece
        // attempt at drawing next piece
        if (myNextPiece != null) {
            for (int rows = 0; rows < myNextPiece.getHeight(); rows++) {
                for (int columns = 0; columns < myNextPiece.getWidth(); columns++) {
                    if (myNextPiece.getBlock() != Block.EMPTY) { // if block type is not empty, paint block
                        g2d.drawRect(25 * rows, 25 * columns, 25, 25);
                    }
                }
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {
        if ("PROPERTY_NEXT_PIECE".equals(theEvent.getPropertyName())) {
            myNextPiece = (TetrisPiece) theEvent.getNewValue();
            repaint(); // draws next tetris piece in panel
        }
    }
}