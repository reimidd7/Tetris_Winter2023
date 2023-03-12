/*
 * TCSS 305 - Tetris Project
 *
 */

package view;

import model.Board;
import model.Score;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * This is the class that creates a panel to display
 * information to the user.
 * It is on the right side of the Tetris board.
 *
 * @author Calvin Beardemphl
 * @author Victoria Dolojan
 * @version Winter 2023
 */
public class OtherInfo extends JPanel implements PropertyChangeListener {

    /** Width and height of the panel.*/
    private static final int PANEL_SIZE = 200;

    /**
     * The Score object.
     */
    private final Score myScore = new Score();

    private final JPanel myStats;

    private JTextField myScoreText;
    private JTextField myLevelText;
    private JTextField myLinesText;




    /** Constructor for OtherInfo panel. */
    public OtherInfo() {
        super();
        setBackground(Color.GREEN);
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        setLayout(new GridLayout(2, 0));
        createControls();
        myStats = new JPanel();
        createStats();
    }

    private void createControls() {
        final JTextArea controlsText = new JTextArea(createControlsText());
        controlsText.setEditable(false);
        controlsText.setBackground(Color.GREEN);
        add(controlsText);
    }

    private void createStats() {
        myStats.setBackground(Color.BLUE);
        myStats.setLayout(new GridLayout(3, 2));
//        final JTextArea statsText = new JTextArea(createStatsText());
//        statsText.setEditable(false);
//        myStats.add(statsText);

        final JLabel score = new JLabel("Score: ");
        final JLabel level = new JLabel("Level: ");
        final JLabel lines = new JLabel("Lines Cleared: ");
        repaint();
//        myScoreText = new JTextField("0");
//        myLevelText = new JTextField("1");
//        myLinesText = new JTextField("0");
//
//        final Border border = BorderFactory.createLineBorder(Color.BLACK);
//        myScoreText.setBorder(border);
//        myLevelText.setBorder(border);
//        myLinesText.setBorder(border);
//
//        myScoreText.setEditable(false);
//        myLevelText.setEditable(false);
//        myLinesText.setEditable(false);
//
        myStats.add(score);
////        myStats.add(myScoreText);
        myStats.add(level);
////        myStats.add(myLevelText);
        myStats.add(lines);
//        myStats.add(myLinesText);

        add(myStats);
//        repaint();
    }


    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setColor(Color.PINK);
        myScoreText = new JTextField(myScore.getScore());
        myLevelText = new JTextField(myScore.getLevel());
        myLinesText = new JTextField(myScore.getLinesCleared());
        myStats.add(myScoreText);
        myStats.add(myLevelText);
        myStats.add(myLinesText);
//        g2d.drawString(myScoreText.toString(), 0, 0);
//        g2d.drawString(myLevelText.toString(), 0, 0);
//        g2d.drawString(myLevelText.toString(), 0, 0);

    }

    /**
     * Creates the Text for the controls
     * to be displayed on the OtherInfo panel.
     *
     * @return a String Text Block
     */
    private static String createControlsText() {
        return """
           Controls:
            A/a/← moves piece left
            D/d/→ moves piece right
            S/s/↓ moves piece down
            W/w/↑ rotates piece""";
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals(Board.PROPERTY_SCORE)) {
            repaint();
        }
    }
}