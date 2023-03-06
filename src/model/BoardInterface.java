package model;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 *  The Interface for the Board class
 *  which defines its API.
 *
 * @author Calvin Beardemphl
 * @author Rick Adams, Viktoria Dolojan, Tsion Fufa, Reilly Middlebrooks.
 * @version Winter 2023
 */
public interface BoardInterface {

    /**
     * Get the width of the board.
     *
     * @return Width of the board.
     */
    int getWidth();

    /**
     * Get the height of the board.
     *
     * @return Height of the board.
     */
    int getHeight();


    /**
     * Resets the board for a new game.
     * This method must be called before the first game
     * and before each new game.
     */
    void newGame();

    /**
     * Sets a non-random sequence of pieces to loop through.
     *
     * @param thePieces the List of non-random TetrisPieces.
     */
    void setPieceSequence(List<TetrisPiece> thePieces);

    /**
     * Advances the board by one 'step'.
     * This could include
     * - moving the current piece down 1 line
     * - freezing the current piece if appropriate
     * - clearing full lines as needed
     */
    void step();

    /**
     * Try to move the movable piece down.
     * Freeze the Piece in position if down tries to move into an illegal state.
     * Clear full lines.
     */
    void down();

    /**
     * Try to move the movable piece left.
     */
    void left();

    /**
     * Try to move the movable piece right.
     */
    void right();

    /**
     * Try to rotate the movable piece in the clockwise direction.
     */
    void rotateCW();

    /**
     * Try to rotate the movable piece in the counter-clockwise direction.
     */
    void rotateCCW();

    /**
     * Drop the piece until piece is set.
     */
    void drop();

    // toString() maybe ?????


    /**
     * Adds a PropertyChangeListener to PropertyChangeSupport.
     *
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Adds a PropertyChangeListener with a Property name to PropertyChangeSupport.
     *
     * @param thePropertyName The name of the property that has been changed
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);

    /**
     * Removes a PropertyChangeListener from PropertyChangeSupport.
     *
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Removes a PropertyChangeListener with a Property name from PropertyChangeSupport.
     *
     * @param thePropertyName The name of the property that has been changed
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(String thePropertyName,
                                      PropertyChangeListener theListener);
}
