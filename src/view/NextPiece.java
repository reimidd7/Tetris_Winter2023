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
import javax.swing.*;
import javax.swing.border.TitledBorder;

import model.Block;
import model.Board;
import model.MovableTetrisPiece;
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
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        //add(new JLabel("Next Tetris Piece"));
    }

    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        if (myNextPiece != null) {
            final DrawPieces draw = new DrawPieces();
            final int w = (getWidth() - myNextPiece.getWidth() * 20) / 2;
            final int h =  (getHeight() - myNextPiece.getHeight() * 20 - 60) / 2;

            g2d.rotate(Math.PI, 100,100);
            g2d.translate(w, h);

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
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals(Board.PROPERTY_NEXT_PIECE)) {
            myNextPiece = (TetrisPiece) theEvent.getNewValue();
            repaint(); // draws next tetris piece in panel
        }
    }
}