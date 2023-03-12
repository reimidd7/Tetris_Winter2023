package view;

import model.Board;
import model.BoardInterface;
import model.MovableTetrisPiece;
import model.Score;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

/**
 * This class creates the Frame and GUI for the tetris game.
 *  It includes a menu bar at the top of the frame
 *
 * @author Reilly Middlebrooks
 * @author Tsion Fufa
 * @author Calvin Beardemphl, Viktoria Dolojan, Rick Adams
 * @version Winter 2023
 */
public class Frame extends JFrame implements PropertyChangeListener {

    public static final String PROPERTY_LEVEL = "edskjhfa";


    /** Width of frame. */
    private static final int WIDTH = 400;

    /** Height of frame. */
    private static final int HEIGHT = 450;

    /** Frame dimension. */
    private static final Dimension FRAME_DIMENSION = new Dimension(400, 450);

    /** Panel dimension. */
    private static final Dimension PANEL_DIMENSION = new Dimension(200, 400);

    /** Time constant. */ // TODO: Large only for testing purposes. Make smaller.
    private static final int TIME_CONST = 1000;

    /** Timer object. */
    private static Timer timer;

    /** BoardInterface object. */
    private final BoardInterface myBoard;

    /** Tracks if game is in progress. */
    private static boolean myGameOver = false;

    /** The Score object. */
    private static Score myScore;

    /** Movable Tetris Piece object. */
    private MovableTetrisPiece myCurrentPiece; //TODO: Need to find a way to instantiate. For use in property change method

    public Frame(final BoardInterface theBoard) {
        super();

        //Create a board/model object from interface
        myBoard = theBoard;

//        myCurrentPiece = new MovableTetrisPiece(myCurrentPiece.getTetrisPiece(),
//                myCurrentPiece.getPosition()); //TODO: How to instantiate?

        // Create the frame for the tetris game (aka the top most "panel")
        createTetrisFrame(WIDTH, HEIGHT);

        //add KeyListeners to the frame
        addKeyListener(new BoardKeyListener());

        setFocusable(true);
        requestFocus();
        createTimer();
    }

    //Used to create the rough frame for our tetris project.
    public void createTetrisFrame(final int theWidth, final int theHeight) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Group 1 - Tetris Game");
        setSize(theWidth, theHeight); //set height and width
        setResizable(false);
        setJMenuBar(createFileMenu()); //add menu bar to the frame
        setVisible(true);
    }

    //Create a file menu with event handlers.
    public JMenuBar createFileMenu() {
        final JMenuBar menuBar = new JMenuBar();

        final JMenu menu = new JMenu("File");
        final JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(
                e -> System.exit(0));
        menu.add(exit);
        menuBar.add(menu);

        final JMenu help = new JMenu("Help");
        final JMenuItem rules = new JMenuItem("Rules");
        rules.addActionListener(
                e -> JOptionPane.showMessageDialog(null, "Based off the classic Tetris rules."));
        help.add(rules);
        menuBar.add(help);

        final JMenu about = new JMenu("About");
        final JMenuItem abt = new JMenuItem("About Game");
        abt.addActionListener(
                e -> JOptionPane.showMessageDialog(null, "This is a clone Tetris game."));
        about.add(abt);
        menuBar.add(about);

        // Create "Difficulty" submenu with 5 difficulty levels
        final JMenu difficultyMenu = new JMenu("Difficulty");
        final ButtonGroup difficultyGroup = new ButtonGroup();
        final JRadioButtonMenuItem level1Item = new JRadioButtonMenuItem("Level 1");
        final JRadioButtonMenuItem level2Item = new JRadioButtonMenuItem("Level 2");
        final JRadioButtonMenuItem level3Item = new JRadioButtonMenuItem("Level 3");
        final JRadioButtonMenuItem level4Item = new JRadioButtonMenuItem("Level 4");
        final JRadioButtonMenuItem level5Item = new JRadioButtonMenuItem("Level 5");
        level1Item.setSelected(true); // Default level is 1
        difficultyGroup.add(level1Item);
        difficultyGroup.add(level2Item);
        difficultyGroup.add(level3Item);
        difficultyGroup.add(level4Item);
        difficultyGroup.add(level5Item);
        difficultyMenu.add(level1Item);
        difficultyMenu.add(level2Item);
        difficultyMenu.add(level3Item);
        difficultyMenu.add(level4Item);
        difficultyMenu.add(level5Item);
        level1Item.addActionListener(e -> {
            timer.setDelay(TIME_CONST); // Update game delay for level 1
//            firePropertyChange(PROPERTY_LEVEL, null, 1);
            myScore.updateLevel(1);

        });
        level2Item.addActionListener(e -> {
            timer.setDelay(800); // Update game delay for level 2
//            firePropertyChange(PROPERTY_LEVEL, null, 2);
            myScore.updateLevel(2);
        });
        level3Item.addActionListener(e -> {
            timer.setDelay(600); // Update game delay for level 3
//            firePropertyChange(PROPERTY_LEVEL, null, 3);
            myScore.updateLevel(3);
        });
        level4Item.addActionListener(e -> {
            timer.setDelay(400); // Update game delay for level 4
            myScore.updateLevel(4);
        });
        level5Item.addActionListener(e -> {
            timer.setDelay(200); // Update game delay for level 5
            myScore.updateLevel(5);
        });
        menuBar.add(difficultyMenu);

        final JMenu pause = new JMenu("Pause");
        final JButton pauseButton = new JButton("Pause");
        pause.add(pauseButton);
        pauseButton.addActionListener(
                e -> {
                    timer.stop();
                    JFrame continueFrame = new JFrame("Paused");
                    JPanel continuePanel = new JPanel();
                    JLabel continueLabel = new JLabel("Game is currently paused. Would you like to continue?");
                    JButton continueButton = new JButton("Continue");
                    continuePanel.add(continueLabel);
                    continuePanel.add(continueButton);
                    continueFrame.add(continuePanel);
                    continueFrame.setSize(WIDTH,100);
                    continueFrame.setVisible(true);
                    continueButton.addActionListener(
                            actionEvent -> {
                                timer.start();
                                continueFrame.setVisible(false);
                            }
                    );
                });
        menuBar.add(pause);

        final JMenu end = new JMenu("End Game");
        final JButton endButton = new JButton("End Game");
        end.add(endButton);
        endButton.addActionListener(
                e -> {
                    // TODO: end current game
                    myGameOver = false;
                    timer.stop();
//                    timer.restart();
                    myScore.reset();
                    createGameOver(); // displays game stats
                });
        menuBar.add(end);

        final JMenu restart = new JMenu("New Game");
        final JButton restartButton = new JButton("Start New Game");
        restart.add(restartButton);
        restartButton.addActionListener(
                e -> {
                    // TODO: start new game
                    if (!myGameOver) {
                        myGameOver = true;
                        timer.setDelay(TIME_CONST);
                        timer.start();
                        myBoard.newGame();
                    } else {
                        JOptionPane.showMessageDialog(null, "Current game has not ended.");
                    }
                });
        menuBar.add(restart);

        return menuBar;
    }

    public static void createGameOver() {
        JFrame scoreFrame = new JFrame("GAME OVER");
        JPanel scorePanel = new JPanel();
        JLabel score = new JLabel("Score: " + myScore.getScore());
        JLabel level = new JLabel("Level: " + myScore.getLevel());
        JLabel lines = new JLabel("Lines Cleared: " + myScore.getLinesCleared());
        scorePanel.add(score);
        scorePanel.add(level);
        scorePanel.add(lines);
        scoreFrame.add(scorePanel);
        scoreFrame.setSize(WIDTH,100);
        scoreFrame.setVisible(true);
    }


    public static void createAndShowGUI() {
        // final Board board = new Board();

        // Get the Board size from the user - easter egg
        Dimension boardDimensions = getBoardSize();
        // Make a Board based on user inputted size
        final Board board = new Board((int) boardDimensions.getWidth(), (int) boardDimensions.getHeight());
        final Frame tetrisFrame = new Frame(board);
        board.addPropertyChangeListener(tetrisFrame);

        final NextPiece nextPiece = new NextPiece();
        final OtherInfo otherInfo = new OtherInfo();
        final BoardPanel boardPanel = new BoardPanel();
        myScore = new Score();

        board.addPropertyChangeListener(boardPanel);
        board.addPropertyChangeListener(nextPiece);
        board.addPropertyChangeListener(otherInfo);
        board.addPropertyChangeListener(myScore);


        // sets the min and max size of frame
        tetrisFrame.setLayout(new GridLayout(1, 2));
        tetrisFrame.setMinimumSize(FRAME_DIMENSION);
        tetrisFrame.setMaximumSize(FRAME_DIMENSION);

        // creates panel for next piece panel on top of other info panel
        final JPanel eastInfo = new JPanel(new GridLayout(2, 1));
        eastInfo.add(nextPiece);
        eastInfo.add(otherInfo);

        // sets size of board panel and places it on the left of the frame
        boardPanel.setPreferredSize(PANEL_DIMENSION);
        tetrisFrame.add(boardPanel);

        // sets size of info panel and places it on the right of the frame
        eastInfo.setPreferredSize(PANEL_DIMENSION);
        tetrisFrame.add(eastInfo);

        tetrisFrame.pack();
        tetrisFrame.setVisible(true);

        board.newGame();

    }

    private static Dimension getBoardSize() {
        int width = 10;
        int height = 20;
        boolean validInput = false;

        // Prompt the user for the board size
//        while (!validInput) {
        String input = JOptionPane.showInputDialog(null,
                "Enter board size (format: width x height):",
                width + " x " + height);
        if (input == null) {
            // User clicked Cancel
            System.exit(0);
        }
        // easter egg
        JOptionPane.showMessageDialog(null, "Hahaha you can't do that!");
//            validInput = true;


//            String[] dimensions = input.split("x");
//            if (dimensions.length == 2) {
//                try {
//                    width = Integer.parseInt(dimensions[0].trim());
//                    height = Integer.parseInt(dimensions[1].trim());
//                    if (width > 0 && height > 0) {
//                        validInput = true;
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Invalid board size");
//                    }
//                } catch (NumberFormatException e) {
//                    JOptionPane.showMessageDialog(null, "Invalid board size");
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "Invalid board size");
//            }
//        }
        return new Dimension(width, height);
    }

    private void createTimer() {
        // instantiate the timer and set the delay to 500ms
        timer = new Timer(TIME_CONST,
                e -> { // call the appropriate method from the Interface defined in Model Update
                    myBoard.step();
                });

        // start the timer
        timer.start();

    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals(Board.PROPERTY_GAME_OVER)) {
            myGameOver = (boolean) theEvent.getNewValue();
            createGameOver();
        }
    }

    /**
     * Inner class for key listeners in order to move the piece.
     *
     * @author Reilly Middlebrooks
     */
    class BoardKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (theEvent.getKeyCode() == KeyEvent.VK_A
                    || theEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                System.out.println("Left");
                myBoard.right();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_D
                    || theEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                System.out.println("Right");
                myBoard.left();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_W
                    || theEvent.getKeyCode() == KeyEvent.VK_UP) {
                //Do I need to make this different for each piece some CCW and some CW
                System.out.println("UP");
                myBoard.rotateCW();
                myBoard.rotateCCW();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_S
                    || theEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                System.out.println("Down");
                myBoard.down();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                System.out.println("drop");
                myBoard.drop();
            }
        }
    }
} // end of frame class