package view;

import model.Point;
import model.TetrisPiece;

import java.awt.*;

public class DrawPieces {
   /** For the scale of our grid. */
    private static final int GRID_UNIT = 20;

    public void drawI(final Graphics2D theGraphics) {
        for (Point bI: TetrisPiece.I.getPoints()) {
            theGraphics.setPaint(Color.CYAN);
            theGraphics.fillRect(bI.x() * GRID_UNIT, bI.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
            theGraphics.setPaint(Color.BLACK);
            theGraphics.drawRect(bI.x() * GRID_UNIT, bI.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
        }
    }

    public void drawO(final Graphics2D theGraphics) {
        for (Point bO: TetrisPiece.O.getPoints()) {
            theGraphics.setPaint(Color.YELLOW);
            theGraphics.fillRect(bO.x() * GRID_UNIT, bO.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
            theGraphics.setPaint(Color.BLACK);
            theGraphics.drawRect(bO.x() * GRID_UNIT, bO.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
        }
    }

    public void drawJ(final Graphics2D theGraphics) {
        for (Point bJ: TetrisPiece.J.getPoints()) {
            theGraphics.setPaint(Color.BLUE);
            theGraphics.fillRect(bJ.x() * GRID_UNIT, bJ.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
            theGraphics.setPaint(Color.BLACK);
            theGraphics.drawRect(bJ.x() * GRID_UNIT, bJ.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
        }
    }

    public void drawL(final Graphics2D theGraphics) {
        for (Point bL: TetrisPiece.L.getPoints()) {
            theGraphics.setPaint(Color.ORANGE);
            theGraphics.fillRect(bL.x() * GRID_UNIT, bL.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
            theGraphics.setPaint(Color.BLACK);
            theGraphics.drawRect(bL.x() * GRID_UNIT, bL.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
        }
    }

    public void drawS(final Graphics2D theGraphics) {
        for (Point bS: TetrisPiece.S.getPoints()) {
            theGraphics.setPaint(Color.GREEN);
            theGraphics.fillRect(bS.x() * GRID_UNIT, bS.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
            theGraphics.setPaint(Color.BLACK);
            theGraphics.drawRect(bS.x() * GRID_UNIT, bS.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
        }
    }

    public void drawT(final Graphics2D theGraphics) {
        for (Point bT: TetrisPiece.T.getPoints()) {
            theGraphics.setPaint(Color.MAGENTA);
            theGraphics.fillRect(bT.x() * GRID_UNIT, bT.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
            theGraphics.setPaint(Color.BLACK);
            theGraphics.drawRect(bT.x() * GRID_UNIT, bT.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
        }
    }

    public void drawZ(final Graphics2D theGraphics) {
        for (Point bZ: TetrisPiece.Z.getPoints()) {
            theGraphics.setPaint(Color.RED);
            theGraphics.fillRect(bZ.x() * GRID_UNIT, bZ.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
            theGraphics.setPaint(Color.BLACK);
            theGraphics.drawRect(bZ.x() * GRID_UNIT, bZ.y() * GRID_UNIT, GRID_UNIT, GRID_UNIT);
        }
    }
}