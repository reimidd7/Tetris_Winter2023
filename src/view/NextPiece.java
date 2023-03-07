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
import model.TetrisPiece;
import view.DrawPieces;

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
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        setBorder(new TitledBorder("Next Tetris Piece"));
    }

    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // TODO: draw tetris piece
        // IDEA: call draw pieces depending on myNextPiece
        // attempt at drawing next piece
        if (myNextPiece != null) {
            DrawPieces draw = new DrawPieces();
            switch (myNextPiece) {
                case I -> draw.drawI(g2d);
                case J -> draw.drawJ(g2d);
                case L -> draw.drawL(g2d);
                case O -> draw.drawO(g2d);
                case S -> draw.drawS(g2d);
                case T -> draw.drawT(g2d);
                case Z -> draw.drawZ(g2d);
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