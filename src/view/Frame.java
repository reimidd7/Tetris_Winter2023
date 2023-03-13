/**
 * group1-tetris game
 * tcss - 305A
 */

package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.Timer;
import model.Board;
import model.BoardInterface;
import model.Score;

/**
 * This class creates the Frame and GUI for the tetris game.
 *  It includes a menu bar at the top of the frame
 *
 * @author Reilly Middlebrooks, Tsion Fufa
 * @author Calvin Beardemphl, Viktoria Dolojan, Rick Adams
 * @version Winter 2023
 */
public class Frame extends JFrame implements PropertyChangeListener {
    /** Constant for the level property. */
    public static final String PROPERTY_LEVEL = "Level.";
    /** Generated serial UID. */
    @Serial
    private static final long serialVersionUID = 6663876407218089621L;
    /** Width of frame. */
    private static final int WIDTH = 400;
    /** Height of frame. */
    private static final int HEIGHT = 450;
    /** Height for the score panel. */
    private static final int PANEL_HEIGHT = 100;
    /** Width for the score panel. */
    private static final int PANEL_WIDTH = 400;

    /** Default width for the board. */
    private static final int DEFAULT_WIDTH = 10;
    /** Default height for the board. */
    private static final int DEFAULT_HEIGHT = 20;
    /** Pause menu label. */
    private static final String PAUSE = "Pause";

    /** Frame dimension. */
    private static final Dimension FRAME_DIMENSION = new Dimension(400, 450);
    /** Panel dimension. */
    private static final Dimension PANEL_DIMENSION = new Dimension(200, 400);

    /** Time constant. */
    private static final int TIME_CONST = 1000;
    /** Delay timer - level 1. */
    private static final int LEVEL_DELAY1 = 800;
    /** Delay timer - level 2. */
    private static final int LEVEL_DELAY2 = 750;
    /** Delay timer - level 3. */
    private static final int LEVEL_DELAY3 = 700;
    /** Delay timer - level 4. */
    private static final int LEVEL_DELAY4 = 650;
    /** Constant for update level 1. */
    private static final int UPDATE_LEVEL1 = 1;
    /** Constant for update level 2. */
    private static final int UPDATE_LEVEL2 = 2;
    /** Constant for update level 3. */
    private static final int UPDATE_LEVEL3 = 3;
    /** Constant for update level 4. */
    private static final int UPDATE_LEVEL4 = 4;
    /** Constant for update level 5. */
    private static final int UPDATE_LEVEL5 = 5;


    /** Timer object. */
    private static Timer timer;
    /** Tracks if game is in progress. */
    private static boolean myGameOver;
    /** The Score object. */
    private static Score myScore;
    /** BoardInterface object. */
    private final BoardInterface myBoard;
    /**
     * Public constructor.
     *
     * @param theBoard returns BoardInterface.
     */
    public Frame(final BoardInterface theBoard) {
        super();
        //Create a board/model object from interface
        myBoard = theBoard;
        addKeyListener(new BoardKeyListener());
        setFocusable(true);
        requestFocus();
        createTimer();
        frameHelper();
        setVisible(true);
    }
    private void frameHelper() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Group 1 - Tetris Game");
        setSize(WIDTH, HEIGHT); //set height and width
        setResizable(false);
        setJMenuBar(createMenuBar()); //add menu bar to the frame
        setLocationRelativeTo(null);
        myScore = new Score();
        myGameOver = false;
    }
    /**
     * Central Menu bar creator to mount the menus.
     *
     * @return returns the Menu JMenuBar.
     */
    public static JMenuBar createMenuBar() {
        final JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(createFileMenu());
        jMenuBar.add(createDifficultyMenu());
        jMenuBar.add(createRestartEndPauseMenu());
        return jMenuBar;
    }
    /**
     * File menu creator.
     *
     * @return returns a File JMenu.
     */
    private static JMenu createFileMenu() {
        final JMenu menu = new JMenu("File");
        final JMenuItem exit = new JMenuItem("Exit");
        final JMenuItem rules = new JMenuItem("Rules");
        final JMenuItem help = new JMenuItem("Help");
        final JMenuItem about = new JMenuItem("About Game");
        rules.addActionListener(
                e -> JOptionPane.showMessageDialog(null,
                        "Use the provided controls to navigate floating blocks"
                                + "to clear out lefts over block-debris, "
                                + "and advance levels!"));

        menu.add(rules);
        help.addActionListener(
                e -> JOptionPane.showMessageDialog(null,
                        "Based off the classic Tetris rules."));
        menu.add(help);
        about.addActionListener(
                e -> JOptionPane.showMessageDialog(null,
                        "This is a clone Tetris game."));
        menu.add(about);
        exit.addActionListener(
                e -> System.exit(0));
        menu.add(exit);


        return menu;
    }
    /**
     * Menu for the games difficulty settings.
     *
     * @return returns a difficultyMenu JMenu.
     */
    private static JMenu createDifficultyMenu() {
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
            myScore.updateLevel(UPDATE_LEVEL1);
        });
        level2Item.addActionListener(e -> {
            timer.setDelay(LEVEL_DELAY1); // Update game delay for level 2
            myScore.updateLevel(UPDATE_LEVEL2);
        });
        level3Item.addActionListener(e -> {
            timer.setDelay(LEVEL_DELAY2); // Update game delay for level 3
            myScore.updateLevel(UPDATE_LEVEL3);
        });
        level4Item.addActionListener(e -> {
            timer.setDelay(LEVEL_DELAY3); // Update game delay for level 4
            myScore.updateLevel(UPDATE_LEVEL4);
        });
        level5Item.addActionListener(e -> {
            timer.setDelay(LEVEL_DELAY4); // Update game delay for level 5
            myScore.updateLevel(UPDATE_LEVEL5);
        });
        return difficultyMenu;
    }
    /**
     * The restart and end-game menu.
     *
     * @return returns Restart/End/PauseGame JMenu.
     */
    private static JMenu createRestartEndPauseMenu() {
        final JMenu end = new JMenu("Restart/End/Pause Game");
        final JButton endButton = new JButton("End Game");
        final JButton restartButton = new JButton("Restart Game");
        final JButton pauseButton = new JButton(PAUSE);

        end.add(restartButton);
        restartButton.addActionListener(
                e -> {
                    if (myGameOver) {
                        myGameOver = false;
                        JOptionPane.showMessageDialog(null,
                                "Current game has not ended.");
                    } else {
                        timer.start();
                    }
                });
        end.add(endButton);
        endButton.addActionListener(
                e -> {
                    myGameOver = true;
                    // myGameOver = false;
                    timer.stop();
                    myScore.reset();
                    createGameOver(); // displays game stats
                });
        end.add(pauseButton);
        pauseButton.addActionListener(
                e -> {
                    timer.stop();
                    final JFrame continueFrame = new JFrame("Paused");
                    final JPanel continuePanel = new JPanel();
                    final JLabel continueLabel = new JLabel("Game is currently paused. "
                            + "Would you like to continue?");
                    final JButton continueButton = new JButton("Continue");
                    continuePanel.add(continueLabel);
                    continuePanel.add(continueButton);
                    continueFrame.add(continuePanel);
                    continueFrame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
                    continueFrame.setLocationRelativeTo(null);
                    continueFrame.setVisible(true);
                    continueButton.addActionListener(
                            actionEvent -> {
                                timer.start();
                                continueFrame.setVisible(false);
                            }
                    );
                });
        return end;
    }

    /** Game over menu. */
    public static void createGameOver() {
        final JFrame scoreFrame = new JFrame("GAME OVER");
        final JPanel scorePanel = new JPanel();
        final JLabel score = new JLabel("Score: " + myScore.getScore());
        final JLabel level = new JLabel("Level: " + myScore.getLevel());
        final JLabel lines = new JLabel("Lines Cleared: " + myScore.getLinesCleared());
        scorePanel.add(score);
        scorePanel.add(level);
        scorePanel.add(lines);
        scoreFrame.add(scorePanel);
        scoreFrame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        scoreFrame.setVisible(true);
    }

    /** Graphical user interface for the game. */
    public static void createAndShowGUI() {

        // Get the Board size from the user - Easter egg
        final Dimension boardDimensions = getBoardSize();
        // Make a Board based on user inputted size
        final Board board = new Board((int) boardDimensions.getWidth(),
                (int) boardDimensions.getHeight());
        final Frame tetrisFrame = new Frame(board);
        board.addPropertyChangeListener(tetrisFrame);

        final NextPiece nextPiece = new NextPiece();
        final OtherInfo otherInfo = new OtherInfo();
        final BoardPanel boardPanel = new BoardPanel();
        myScore = new Score();

        board.addPropertyChangeListener(boardPanel);
        board.addPropertyChangeListener(nextPiece);

        board.addPropertyChangeListener(myScore);
        // sets the min and max size of frame
        tetrisFrame.setLayout(new GridLayout(1, 2));
        tetrisFrame.setMinimumSize(FRAME_DIMENSION);
        tetrisFrame.setMaximumSize(FRAME_DIMENSION);
        // creates panel for next piece panel on top of other info panel
        final JPanel eastInfo = new JPanel(new GridLayout(2, 1));
        eastInfo.add(nextPiece);
        eastInfo.add(otherInfo);
//          board.addPropertyChangeListener(otherInfo);
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
    /**
     * Accessor method for the board size.
     * @return the board size (Dimension).
     */
    private static Dimension getBoardSize() {
        final int width = DEFAULT_WIDTH;
        final int height = DEFAULT_HEIGHT;
        // Prompt the user for the board size
        final String input = JOptionPane.showInputDialog(null,
                "Enter board size (format: width x height):",
                width + " x " + height);
        if (input == null) {
            System.exit(0); // User clicked Cancel
        }
        JOptionPane.showMessageDialog(null,
                "Haha you can't do that!"); // easter egg
        return new Dimension(width, height);
    }
    /**Creates the game timer. */
    private void createTimer() {
        // instantiate the timer and set the delay to 500ms
        timer = new Timer(TIME_CONST,
                // call the appropriate method from the Interface defined in Model Update
                e -> myBoard.step());
        timer.start(); // start the timer
    }
    /**
     * Property change listener for Observer Design Pattern.
     *
     * @param theEvent A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
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
    private class BoardKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            keyHelperOne(theEvent);
            keyHelperTwo(theEvent);
        }
        /**
         * Helper method for keyboard commands.
         *
         * @param theEvent the event key object.
         */
        private void keyHelperOne(final KeyEvent theEvent) {
            if (theEvent.getKeyCode() == KeyEvent.VK_A) {
                myBoard.right();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_D) {
                myBoard.left();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_W) {
                myBoard.rotateCW();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_S) {
                myBoard.down();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                myBoard.drop();
            }
        }
        /**
         * Helper method for keyboard commands.
         *
         * @param theEvent the event key object.
         */
        private void keyHelperTwo(final KeyEvent theEvent) {
            if (theEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                myBoard.right();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                myBoard.left();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_UP) {
                myBoard.rotateCW();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                myBoard.down();
            }
        }
    }
} // end of frame class