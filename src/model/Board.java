/*
 * TCSS 305
 * 
 * An implementation of the classic game "Tetris".
 */

package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.wallkicks.WallKick;

/**
 * Represents a Tetris board. Board objects communicate with clients via Observer pattern. 
 * <p>Clients can expect Board objects to call notifyObservers with four different
 * data types:</p>
 * <dl>
 * <dt>{@code List<Block[]>}</dt>
 * <dd>Represents the non-moving pieces on the Board. i.e. Frozen Blocks</dd>
 * <dt>{@link model.MovableTetrisPiece MovableTerisPiece}</dt>
 * <dd>Represents current moving Piece.</dd>
 * <dt>{@link model.TetrisPiece TertisPiece}</dt>
 * <dd>Represents next Piece.</dd>
 * <dt>{@code Integer[]}</dt>
 * <dd>The size of the array represents the number of rows of Frozen Blocks removed.</dd>
 * <dt>{@code Boolean}</dt>
 * <dd>When true, the game is over. </dd>
 * </dl>
 * 
 * @author Charles Bryan
 * @author Alan Fowler
 * @author Calvin Beardemphl, Rick Adams, Viktoria Dolojan, Tsion Fufa, Reilly Middlebrooks.
 * @version Winter 2023
 */
public class Board implements BoardInterface {

    // Class constants
    /**
     * A property name for the score.
     */
    public static final String PROPERTY_SCORE = "ekfuhad";

    /**
     * A property name for the current piece.
     */
    public static final String PROPERTY_CURRENT_PIECE = "This doesn't not matter";

    /**
     * A property name for drop.
     */
    public static final String PROPERTY_DROP = "This doesn't not matter!";

    /**
     * A property name for the frozen blocks.
     */
    public static final String PROPERTY_FROZEN_BLOCKS = "This doesn't not matter1!";

    /**
     * A property name for game over boolean.
     */
    public static final String PROPERTY_GAME_OVER = "This doesn't not matte!";

    /**
     * A property name for the next piece.
     */
    public static final String PROPERTY_NEXT_PIECE = "This doesn't not mate!";

    /**
     * A property name for the piece location.
     */
    public static final String PROPERTY_PIECE_LOCATION = "nlejielsdj";

    /**
     * Default width of a Tetris game board.
     */
    private static final int DEFAULT_WIDTH = 10;

    /**
     * Default height of a Tetris game board.
     */
    private static final int DEFAULT_HEIGHT = 20;

    // Instance fields
    /**
     * Width of the game board.
     */
    private final int myWidth;

    /**
     * Height of the game board.
     */
    private final int myHeight;

    /**
     * The frozen blocks on the board.
     */
    private final List<Block[]> myFrozenBlocks;

    /**
     * The game over state.
     */
    private boolean myGameOver;

    /**
     * Contains a non-random sequence of TetrisPieces to loop through.
     */
    private List<TetrisPiece> myNonRandomPieces;

    /**
     * The current index in the non-random piece sequence.
     */
    private int mySequenceIndex;

    /**
     * Piece that is next to play.
     */
    private TetrisPiece myNextPiece;

    /**
     * Piece that is currently movable.
     */
    private MovableTetrisPiece myCurrentPiece;

    /**
     * The Score object.
     */
    private Score myScore = new Score();

    /**
     * A flag to indicate when moving a piece down is part of a drop operation.
     * This is used to prevent the Board from notifying observers for each incremental
     * down movement in the drop.
     */
    private boolean myDrop;

    /**
     * Manager for Property Change Listeners.
     */
    private final PropertyChangeSupport myPcs;


    // Constructors
    /**
     * Default Tetris board constructor.
     * Creates a standard size tetris game board.
     */
    public Board() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Tetris board constructor for non-default sized boards.
     * 
     * @param theWidth Width of the Tetris game board.
     * @param theHeight Height of the Tetris game board.
     */
    public Board(final int theWidth, final int theHeight) {
        super();
        myWidth = theWidth;
        myHeight = theHeight;
        myFrozenBlocks = new LinkedList<>();
        myNonRandomPieces = new ArrayList<TetrisPiece>();
        mySequenceIndex = 0;
        myPcs = new PropertyChangeSupport(this);
        // myNextPiece and myCurrentPiece are initialized by the newGame() method.
    }

    // Public queries
    @Override
    public int getWidth() {
        return myWidth;
    }

    @Override
    public int getHeight() {
        return myHeight;
    }

    @Override
    public void newGame() {
        // saved old values - added for firePropertyChange
        final List<Block[]> oldFrozenBlocks = new LinkedList<Block[]>(myFrozenBlocks);
        final boolean oldGameOver = myGameOver;
        final MovableTetrisPiece oldPiece = myCurrentPiece;
        final TetrisPiece oldNextPiece = myNextPiece;
//        final boolean oldDrop = myDrop;

        mySequenceIndex = 0;
        myFrozenBlocks.clear();
        for (int h = 0; h < myHeight; h++) {
            myFrozenBlocks.add(new Block[myWidth]);
        }

        myGameOver = false;
        myCurrentPiece = nextMovablePiece(true);
        myDrop = false;
        myScore.reset();

        // TODO Publish Update!
        myPcs.firePropertyChange(PROPERTY_FROZEN_BLOCKS, oldFrozenBlocks, myFrozenBlocks);
        myPcs.firePropertyChange(PROPERTY_GAME_OVER, oldGameOver, myGameOver);
        myPcs.firePropertyChange(PROPERTY_CURRENT_PIECE, oldPiece, myCurrentPiece);
        myPcs.firePropertyChange(PROPERTY_NEXT_PIECE, oldNextPiece, myNextPiece);
        myPcs.firePropertyChange(PROPERTY_PIECE_LOCATION,
                oldPiece,
                new MovableTetrisPiece(myCurrentPiece.getTetrisPiece(),
                        new Point(myCurrentPiece.getPosition().x(), myCurrentPiece.getPosition().y()),
                        myCurrentPiece.getRotation()));

        // myPcs.firePropertyChange(PROPERTY_DROP, oldDrop, myDrop);
        // Restarts states - everything -
        // PROPERTY_FROZEN_BLOCKS
        // PROPERTY_GAME_OVER
        // PROPERTY_CURRENT_PIECE
        // PROPERTY_DROP
        // PROPERTY_NEXT_PIECE not affected ???
    }

    @Override
    public void setPieceSequence(final List<TetrisPiece> thePieces) {
        myNonRandomPieces = new ArrayList<>(thePieces);
        mySequenceIndex = 0;
        myCurrentPiece = nextMovablePiece(true);

    }

    @Override
    public void step() {
        /*
         * Calling the down() method from here should be sufficient
         * to advance the board by one 'step'.
         * However, more code could be added to this method
         * to implement additional functionality
         */
        down();
    }

    @Override
    public void down() {
        if (myCurrentPiece != null) {
            // saved old value - added for firePropertyChange
            final MovableTetrisPiece oldPiece = myCurrentPiece;
            final List<Block[]> oldFrozenBlocks = new LinkedList<Block[]>(myFrozenBlocks);

            if (!move(myCurrentPiece.down())) {
                // the piece froze, so clear lines and update current piece
                addPieceToBoardData(myFrozenBlocks, myCurrentPiece);
                checkRows();
                if (!myGameOver) {
                    myCurrentPiece = nextMovablePiece(false);

                }
                // TODO Publish Update!
                myPcs.firePropertyChange(PROPERTY_CURRENT_PIECE, oldPiece, myCurrentPiece);
                myPcs.firePropertyChange(PROPERTY_FROZEN_BLOCKS, oldFrozenBlocks, myFrozenBlocks);
                myPcs.firePropertyChange(PROPERTY_PIECE_LOCATION,
                        oldPiece,
                        new MovableTetrisPiece(myCurrentPiece.getTetrisPiece(),
                                new Point(myCurrentPiece.getPosition().x(), myCurrentPiece.getPosition().y()),
                                myCurrentPiece.getRotation()));
                // Changing state
                // PROPERTY_CURRENT_PIECE
            }
        }
    }

    @Override
    public void left() {
        if (myCurrentPiece != null) {
            move(myCurrentPiece.left());
        }
    }

    @Override
    public void right() {
        if (myCurrentPiece != null) {
            move(myCurrentPiece.right());
        }
    }

    @Override
    public void rotateCW() {
        if (myCurrentPiece != null) {
            if (myCurrentPiece.getTetrisPiece() == TetrisPiece.O) {
                move(myCurrentPiece.rotateCW());
            } else {
                final MovableTetrisPiece cwPiece = myCurrentPiece.rotateCW();
                final Point[] offsets = WallKick.getWallKicks(cwPiece.getTetrisPiece(),
                                                    myCurrentPiece.getRotation(),
                                                    cwPiece.getRotation());
                for (final Point p : offsets) {
                    final Point offsetLocation = cwPiece.getPosition().transform(p);
                    final MovableTetrisPiece temp = cwPiece.setPosition(offsetLocation);
                    if (move(temp)) {
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void rotateCCW() {
        if (myCurrentPiece != null) {
            if (myCurrentPiece.getTetrisPiece() == TetrisPiece.O) {
                move(myCurrentPiece.rotateCCW());
            } else {
                final MovableTetrisPiece ccwPiece = myCurrentPiece.rotateCCW();
                final Point[] offsets = WallKick.getWallKicks(ccwPiece.getTetrisPiece(),
                                                    myCurrentPiece.getRotation(),
                                                    ccwPiece.getRotation());
                for (final Point p : offsets) {
                    final Point offsetLocation = ccwPiece.getPosition().transform(p);
                    final MovableTetrisPiece temp = ccwPiece.setPosition(offsetLocation);
                    if (move(temp)) {
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void drop() {
        if (!myGameOver) {
            myDrop = true;
            while (isPieceLegal(myCurrentPiece.down())) {
                down();  // move down as far as possible
            }
            myDrop = false;
            down();  // move down one more time to freeze in place
            myPcs.firePropertyChange(PROPERTY_SCORE, null, myScore);
            //myScore.calculateScore(); // I believe this would go here, but I'm not sure
        }
    }

    @Override
    public String toString() {
        final List<Block[]> board = getBoard();
        board.add(new Block[myWidth]);
        board.add(new Block[myWidth]);
        board.add(new Block[myWidth]);
        board.add(new Block[myWidth]);
        if (myCurrentPiece != null) {
            addPieceToBoardData(board, myCurrentPiece);
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = board.size() - 1; i >= 0; i--) {
            final Block[] row = board.get(i);
            sb.append('|');
            for (final Block c : row) {
                if (c == null) {
                    sb.append(' ');
                } else {
                    sb.append('*');
                }
            }
            sb.append("|\n");
            if (i == this.myHeight) {
                sb.append(' ');
                for (int j = 0; j < this.myWidth; j++) {
                    sb.append('-');
                }
                sb.append('\n');
            }
        }
        sb.append('|');
        for (int w = 0; w < myWidth; w++) {
            sb.append('-');
        }
        sb.append('|');
        return sb.toString();
        // Represents the state
    }

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }

    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);

    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);

    }
    
    // Private helper methods
    /**
     * Helper function to check if the current piece can be shifted to the
     * specified position.
     * 
     * @param theMovedPiece the position to attempt to shift the current piece
     * @return True if the move succeeded
     */
    private boolean move(final MovableTetrisPiece theMovedPiece) {
        // saved old values - added for firePropertyChange
        final MovableTetrisPiece oldPiece = myCurrentPiece;
        boolean result = false;
        if (isPieceLegal(theMovedPiece)) {
            myCurrentPiece = theMovedPiece;
            result = true;
            if (!myDrop) {
                // TODO Publish Update!
                myPcs.firePropertyChange(PROPERTY_CURRENT_PIECE, oldPiece, myCurrentPiece);
                myPcs.firePropertyChange(PROPERTY_PIECE_LOCATION,
                        oldPiece,
                        new MovableTetrisPiece(myCurrentPiece.getTetrisPiece(),
                                new Point(myCurrentPiece.getPosition().x(), myCurrentPiece.getPosition().y()),
                                myCurrentPiece.getRotation()));
            }
        }
        return result;
        // Changing state
        // PROPERTY_CURRENT_PIECE
    }

    /**
     * Helper function to test if the piece is in a legal state.
     * 
     * Illegal states:
     * - points of the piece exceed the bounds of the board
     * - points of the piece collide with frozen blocks on the board
     * 
     * @param thePiece MovableTetrisPiece to test.
     * @return Returns true if the piece is in a legal state; false otherwise
     */
    private boolean isPieceLegal(final MovableTetrisPiece thePiece) {
        boolean result = true;
        
        for (final Point p : thePiece.getBoardPoints()) {
            if (p.x() < 0 || p.x() >= myWidth) {
                result = false;
            }
            if (p.y() < 0) {
                result = false;
            }
        }
        return result && !collision(thePiece);      
    }

    /**
     * Adds a movable Tetris piece into a list of board data.
     * 
     * Allows a single data structure to represent the current piece
     * and the frozen blocks.
     * 
     * @param theFrozenBlocks Board to set the piece on.
     * @param thePiece Piece to set on the board.
     */
    private void addPieceToBoardData(final List<Block[]> theFrozenBlocks,
                                     final MovableTetrisPiece thePiece) {
        for (final Point p : thePiece.getBoardPoints()) {
            setPoint(theFrozenBlocks, p, thePiece.getTetrisPiece().getBlock());
        }
    }

    /**
     * Checks the board for complete rows.
     */
    private void checkRows() {
        // saved old values - added for firePropertyChange
        final List<Block[]> oldFrozenBlocks = new LinkedList<Block[]>(myFrozenBlocks);
        final MovableTetrisPiece oldPiece = myCurrentPiece;
        final TetrisPiece oldNextPiece = myNextPiece;
        final List<Integer> completeRows = new ArrayList<>();
        for (final Block[] row : myFrozenBlocks) {
            boolean complete = true;
            for (final Block b : row) {
                if (b == null) {
                    complete = false;
                    break;
                }
            }
            if (complete) {
                completeRows.add(myFrozenBlocks.indexOf(row));
             // TODO Publish Update!
                myPcs.firePropertyChange(PROPERTY_CURRENT_PIECE, oldPiece, myCurrentPiece);
                myPcs.firePropertyChange(PROPERTY_FROZEN_BLOCKS, oldFrozenBlocks, myFrozenBlocks);
                myPcs.firePropertyChange(PROPERTY_NEXT_PIECE, oldNextPiece, myNextPiece);

//                myPcs.firePropertyChange(PROPERTY_PIECE_LOCATION,
//                        oldPiece,
//                        new MovableTetrisPiece(myCurrentPiece.getTetrisPiece(),
//                                new Point(myCurrentPiece.getPosition().x(), myCurrentPiece.getPosition().y()),
//                                myCurrentPiece.getRotation()));
            }
        }
        // loop through list backwards removing items by index
        if (!completeRows.isEmpty()) {
            for (int i = completeRows.size() - 1; i >= 0; i--) {
                final Block[] row = myFrozenBlocks.get(completeRows.get(i));
                myFrozenBlocks.remove(row);
                myFrozenBlocks.add(new Block[myWidth]);
                myPcs.firePropertyChange(PROPERTY_FROZEN_BLOCKS, oldFrozenBlocks, myFrozenBlocks);
            }
        }

        // Changing state
        // PROPERTY_FROZEN_BLOCKS
    }
    
    /**
     * Helper function to copy the board.
     * 
     * @return A new copy of the board.
     */
    private List<Block[]> getBoard() {
        final List<Block[]> board = new ArrayList<Block[]>();
        for (final Block[] row : myFrozenBlocks) {
            board.add(row.clone());
        }
        return board;
    }

    /**
     * Determines if a point is on the game board.
     * 
     * @param theBoard Board to test.
     * @param thePoint Point to test.
     * @return True if the point is on the board otherwise false.
     */
    private boolean isPointOnBoard(final List<Block[]> theBoard, final Point thePoint) {
        return thePoint.x() >= 0 && thePoint.x() < myWidth && thePoint.y() >= 0
               && thePoint.y() < theBoard.size();
    }

    /**
     * Sets a block at a board point.
     * 
     * @param theBoard Board to set the point on.
     * @param thePoint Board point.
     * @param theBlock Block to set at board point.
     */
    private void setPoint(final List<Block[]> theBoard,
                          final Point thePoint,
                          final Block theBlock) {
        // saved old value - added for firePropertyChange
        final boolean oldGameOver = myGameOver;
        final MovableTetrisPiece oldPiece = myCurrentPiece;
        
        if (isPointOnBoard(theBoard, thePoint)) { 
            final Block[] row = theBoard.get(thePoint.y());
            row[thePoint.x()] = theBlock;
        } else if (!myGameOver) {
            myGameOver = true;
            // TODO Publish Update!
            myPcs.firePropertyChange(PROPERTY_CURRENT_PIECE, oldPiece, myCurrentPiece);
            myPcs.firePropertyChange(PROPERTY_GAME_OVER, oldGameOver, myGameOver);
        }
        // Changing state
        // PROPERTY_GAME_OVER
    }

    /**
     * Returns the block at a specific board point.
     * 
     * @param thePoint the specific Point to check
     * @return the Block type at point or null if no block exists.
     */
    private Block getPoint(final Point thePoint) {
        Block b = null;
        if (isPointOnBoard(myFrozenBlocks, thePoint)) {
            b = myFrozenBlocks.get(thePoint.y())[thePoint.x()];
        }
        return b;
    }

    /**
     * Helper function to determine of a movable block has collided with set
     * blocks.
     * 
     * @param theTest movable TetrisPiece to test for collision.
     * @return Returns true if any of the blocks has collided with a set board
     *         block.
     */
    private boolean collision(final MovableTetrisPiece theTest) {
        boolean res = false;
        for (final Point p : theTest.getBoardPoints()) {
            if (getPoint(p) != null) {
                res = true;
            }
        }
        return res;
    }

    /**
     * Gets the next MovableTetrisPiece.
     * 
     * @param theRestart Restart the non-random cycle.
     * @return A new MovableTetrisPiece.
     */
    private MovableTetrisPiece nextMovablePiece(final boolean theRestart) {
        if (myNextPiece == null || theRestart) {
            prepareNextMovablePiece();
        }

        final TetrisPiece next = myNextPiece;
        
        int startY = myHeight - 1;
        if (myNextPiece == TetrisPiece.I) {
            startY--; 
        }

        prepareNextMovablePiece();
        return new MovableTetrisPiece(
                       next,
                       new Point((myWidth - myNextPiece.getWidth()) / 2, startY));
    }
    
    /**
     * Prepares the Next movable piece for preview.
     */
    private void prepareNextMovablePiece() {
        // saved old values - added for firePropertyChange
        final TetrisPiece oldPiece = myNextPiece;
        final boolean share = myNextPiece != null;
        if (myNonRandomPieces == null || myNonRandomPieces.isEmpty()) {
            myNextPiece = TetrisPiece.getRandomPiece();
        } else {
            mySequenceIndex %= myNonRandomPieces.size();
            myNextPiece = myNonRandomPieces.get(mySequenceIndex++);
        }
        if (share && !myGameOver) {
            // TODO Publish Update!
            myPcs.firePropertyChange(PROPERTY_NEXT_PIECE, oldPiece, myNextPiece);
            // Changing state
            // PROPERTY_NEXT_PIECE
        }
    }

    public static void setDelay(int i) {
        // TODO Auto-generated method stub

    }
    
    // Inner classes
    /**
     * A class to describe the board data to registered Observers.
     * The board data includes the current piece and the frozen blocks.
     */
    protected final class BoardData {
        
        /**
         * The board data to pass to observers.
         */
        private final List<Block[]> myBoardData;

        /**
         * Constructor of the Board Data object.
         */
        protected BoardData() {
            myBoardData = getBoard();
            myBoardData.add(new Block[myWidth]);
            myBoardData.add(new Block[myWidth]);
            myBoardData.add(new Block[myWidth]);
            myBoardData.add(new Block[myWidth]);
            if (myCurrentPiece != null) {
                addPieceToBoardData(myBoardData, myCurrentPiece);
            }
        }

        /**
         * Copy and return the board's data.
         * 
         * @return Copy of the Board Data.
         */
        protected List<Block[]> getBoardData() {
            final List<Block[]> board = new ArrayList<Block[]>();
            for (final Block[] row : myBoardData) {
                board.add(row.clone());
            }
            return board;
        }
    } // end inner class BoardData
} // end of Board class