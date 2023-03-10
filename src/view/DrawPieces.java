package view;

import model.Point;
import model.TetrisPiece;

import java.awt.*;

public class DrawPieces {
   /** For the scale of our grid. */
    private static final int GRID_UNIT = 20;



    public Graphics2D drawI(final Graphics2D theGraphics) {
        final Graphics2D block = (Graphics2D) theGraphics;

        for (Point bI: TetrisPiece.I.getPoints()) {
            block.setPaint(Color.PINK);
            block.fillRect(bI.x() * GRID_UNIT, bI.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
            block.setPaint(Color.BLACK);
            block.drawRect(bI.x() * GRID_UNIT, bI.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
        }
        return block;
    }

    public Graphics2D drawO(final Graphics2D theGraphics) {
        final Graphics2D block = (Graphics2D) theGraphics;

        for (Point bO: TetrisPiece.O.getPoints()) {
            block.setPaint(Color.PINK);
            block.fillRect(bO.x() * GRID_UNIT, bO.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
            block.setPaint(Color.BLACK);
            block.drawRect(bO.x() * GRID_UNIT, bO.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
        }
        return block;
    }

    public Graphics2D drawJ(final Graphics2D theGraphics) {
        final Graphics2D block = (Graphics2D) theGraphics;

        for (Point bJ: TetrisPiece.J.getPoints()) {
            block.setPaint(Color.PINK);
            block.fillRect(bJ.x() * GRID_UNIT, bJ.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
            block.setPaint(Color.BLACK);
            block.drawRect(bJ.x() * GRID_UNIT, bJ.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
        }
        return block;
    }

    public Graphics2D drawL(final Graphics2D theGraphics) {
        final Graphics2D block = (Graphics2D) theGraphics;

        for (Point bL: TetrisPiece.L.getPoints()) {
            block.setPaint(Color.PINK);
            block.fillRect(bL.x() * GRID_UNIT, bL.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
            block.setPaint(Color.BLACK);
            block.drawRect(bL.x() * GRID_UNIT, bL.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
        }
        return block;

    }

    public Graphics2D drawS(final Graphics2D theGraphics) {
        final Graphics2D block = (Graphics2D) theGraphics;

        for (Point bS: TetrisPiece.S.getPoints()) {
            block.setPaint(Color.PINK);
            block.fillRect(bS.x() * GRID_UNIT, bS.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
            block.setPaint(Color.BLACK);
            block.drawRect(bS.x() * GRID_UNIT, bS.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
        }
        return block;

    }

    public Graphics2D drawT(final Graphics2D theGraphics) {
        final Graphics2D block = (Graphics2D) theGraphics;

        for (Point bT: TetrisPiece.T.getPoints()) {
            block.setPaint(Color.PINK);
            block.fillRect(bT.x() * GRID_UNIT, bT.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
            block.setPaint(Color.BLACK);
            block.drawRect(bT.x() * GRID_UNIT, bT.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
        }
        return block;

    }

    public Graphics2D drawZ(final Graphics2D theGraphics) {
        final Graphics2D block = (Graphics2D) theGraphics;

        for (Point bZ: TetrisPiece.Z.getPoints()) {
            block.setPaint(Color.PINK);
            block.fillRect(bZ.x() * GRID_UNIT, bZ.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
            block.setPaint(Color.BLACK);
            block.drawRect(bZ.x() * GRID_UNIT, bZ.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
        }
        return block;

    }
}