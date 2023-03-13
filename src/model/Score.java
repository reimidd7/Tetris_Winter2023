package model;

import view.Frame;
import view.OtherInfo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This class calculates the current score of
 * the user, as well as stores the score, level,
 * and number of lines cleared.
 *
 * @author Calvin Beardemphl
 * @version Winter 2023
 */
public class Score implements PropertyChangeListener {

    /**
     * Points awarded for clearing one lines.
     */
    private static final int ONE_LINE = 40;

    /**
     * Points awarded for clearing two lines.
     */
    private static final int TWO_LINES = 100;

    /**
     * Points awarded for clearing three lines.
     */
    private static final int THREE_LINES = 300;

    /**
     * Points awarded for clearing four lines.
     */
    private static final int FOUR_LINES = 1200;

    /**
     * The current score of the user.
     */
    private int myScore;

    /**
     * The current level of the user.
     */
    private int myLevel;

    /**
     * The current number of lines cleared by the user.
     */
    private int myLinesCleared;


    /**
     * Manager for Property Change Listeners.
     */
//    private final PropertyChangeSupport myPcs;

    /**
     * Constructor for a Score object.
     */
    public Score() {
        myScore = 0;
        myLevel = 1;
        myLinesCleared = 0;
//        myPcs = new PropertyChangeSupport(this);
//        OtherInfo other = new OtherInfo();
//        Frame.
//        myPcs.addPropertyChangeListener(other);
    }

    /**
     * This method resets the instance fields
     * when a new game is started.
     */
    public void reset() {
        myScore = 0;
        myLevel = 1;
        myLinesCleared = 0;
    }
//    /**
//     * Calculates the current score after the piece has dropped.
//     * This method should be called at the end of Board.drop()
//     */
//    public void calculateScore() {
//
//        int theScore = 0;
//        if (true) { // if the piece dropped is frozen
//            theScore += 4;
//        }
//
//        // if 1 line  cleared theScore += ONE_LINE
//        // if 2 lines cleared theScore += TWO_LINES
//        // if 3 lines cleared theScore += THREE_LINES
//        // if 4 lines cleared theScore += FOUR_LINES
//        int level =  getLevel();
//        switch (calculateLinesCleared()) {
//            case 1 -> theScore += ONE_LINE * level;
//            case 2 -> theScore += TWO_LINES * level;
//            case 3 -> theScore += THREE_LINES * level;
//            case 4 -> theScore += FOUR_LINES * level;
//            default -> { // should never be reached
//            }
//        }
//        updateScore(theScore);
//    }
//
//
//    /**
//     * Calculates how many lines have been cleared.
//     * @return the number of lines cleared
//     */
//    private int calculateLinesCleared() {
//        int theLinesCleared = 0;
//        // how tf would this be calculated
//        updateLinesCleared(theLinesCleared);
//        return theLinesCleared;
//    }
//
//    /**
//     * Updates the instance field of the current score.
//     * @param theScore the number to be added to the total
//     */
//    private void updateScore(final int theScore) {
//        myScore += theScore;
//        // fire property change ? or in board class ?
//    }
//
//    /**
//     * Updates the instance field of the level.
//     * @param theLinesCleared the number of lines cleared
//     */
//    private void updateLevel(final int theLinesCleared) {
//        // says this should be done in SINGLE STATEMENT, not sure what to do here,
//        // but I feel like it will need the number of lines cleared because
//        // when ever the number of lines cleared ends in 1 or 5, user reaches a new level
//
//        //NOT RIGHT
//        if (theLinesCleared >= 4 ){
//            myLevel++;
//        }
//
//        // fire property change ? or in board class ?
//    }
//
//    /**
//     * Updates the instance field of the number of lines cleared.
//     * @param theLinesCleared the number to be added to the total
//     */
//    private void updateLinesCleared(final int theLinesCleared) {
//        myLinesCleared += theLinesCleared;
//        updateLevel(theLinesCleared);
//        // fire property change ? or in board class ?
//    }

    public void updateScore() {
        myScore += 10;
    }

    public void updateLevel() {
        myLevel += 1;
    }
    public void updateLevel(final int theLevel) {
        myLevel = theLevel;
        System.out.println("level: " + myLevel);
//        myPcs.firePropertyChange(Board.PROPERTY_SCORE, null, null);
    }

    public void updateLinesCleared() {
        myLinesCleared += 1;
    }
    /**
     * Returns the current Score.
     * @return the current score
     */
    public int getScore() {
        return myScore;
    }

    /**
     * Returns the current level.
     * @return the current level
     */
    public int getLevel() {
        return myLevel;
    }

    /**
     * Returns the number of lines cleared.
     * @return the number of lines cleared.
     */
    public int getLinesCleared() {
        return myLinesCleared;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals(Board.PROPERTY_SCORE)) {
            updateScore();
            //repaint(); // draws next tetris piece in panel
        } else if (theEvent.getPropertyName().equals(Board.PROPERTY_FROZEN_BLOCKS)) {
            updateLinesCleared();
        } else if (theEvent.getPropertyName().equals(Frame.PROPERTY_LEVEL)) {
            updateLevel((int)theEvent.getNewValue());
        }

    }
}