package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import model.MovableTetrisPiece;
import model.Score;

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

    /** Width of frame. */
    private static final int WIDTH = 400;

    /** Height of frame. */
    private static final int HEIGHT = 450;

    /** Default width for the board. */
    private static final int DEFAULT_WIDTH = 10;
    /** Default height for the board. */
    private static final int DEFAULT_HEIGHT = 20;

    /** Frame dimension. */
    private static final Dimension FRAME_DIMENSION = new Dimension(400, 450);

    /** Panel dimension. */
    private static final Dimension PANEL_DIMENSION = new Dimension(200, 400);

    /** Time constant. */
    private static final int TIME_CONST = 1000;

    /** Timer object. */
    private static Timer timer;

    /** BoardInterface object. */
    private final BoardInterface myBoard;

    /** Tracks if game is in progress. */
    private static boolean myGameOver;

    /** The Score object. */
    private static Score myScore;


    public Frame(final BoardInterface theBoard) {
        super();

        //Create a board/model object from interface
        myBoard = theBoard;

        myScore = new Score();
        myGameOver = false;


        // Create the frame for the tetris game (aka the top most "panel")
        createTetrisFrame(WIDTH, HEIGHT);

        //add KeyListeners to the frame
        addKeyListener(new BoardKeyListener());

        setFocusable(true);
        requestFocus();
    }

//    public void setMyScore(final Score theScore) {
//        myScore = theScore;
//    }
//    public Score getMyScore() {
//        return myScore;
//    }

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
    public static JMenuBar createFileMenu() {
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
                e -> JOptionPane.showMessageDialog(null,
                        "Based off the classic Tetris rules."));
        help.add(rules);
        menuBar.add(help);

        final JMenu about = new JMenu("About");
        final JMenuItem abt = new JMenuItem("About Game");
        abt.addActionListener(
                e -> JOptionPane.showMessageDialog(null,
                        "This is a clone Tetris game."));
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
            Board.setDelay(800); // Update game delay for level 1
        });
        level2Item.addActionListener(e -> {
            Board.setDelay(600); // Update game delay for level 2
        });
        level3Item.addActionListener(e -> {
            Board.setDelay(400); // Update game delay for level 3
        });
        level4Item.addActionListener(e -> {
            Board.setDelay(200); // Update game delay for level 4
        });
        level5Item.addActionListener(e -> {
            Board.setDelay(100); // Update game delay for level 5
        });
        menuBar.add(difficultyMenu);

        final JMenu pause = new JMenu("Pause");
        final JButton pauseButton = new JButton("Pause");
        pause.add(pauseButton);
        pauseButton.addActionListener(
                e -> {
                    timer.stop();
                    final JFrame continueFrame = new JFrame("Paused");
                    final JPanel continuePanel = new JPanel();
                    final JLabel continueLabel = new JLabel("Game is currently paused. Would you like to continue?");
                    final JButton continueButton = new JButton("Continue");
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
                    myGameOver = false;
                    timer.stop();
                    timer.restart();
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
                        timer.start();
                        //myBoard.newGame();
                    } else {
                        JOptionPane.showMessageDialog(null, "Current game has not ended.");
                    }
                });
        menuBar.add(restart);

        return menuBar;
    }

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
        scoreFrame.setSize(WIDTH,100);
        scoreFrame.setVisible(true);
    }

    public static void createAndShowGUI() {
        // final Board board = new Board();

        // Get the Board size from the user
        final Dimension boardDimensions = getBoardSize();
        // Make a Board based on user inputted size
        final Board board = new Board((int) boardDimensions.getWidth(),
                (int) boardDimensions.getHeight());
        final Frame tetrisFrame = new Frame(board);
        board.addPropertyChangeListener(tetrisFrame);

        final NextPiece nextPiece = new NextPiece();
        final OtherInfo otherInfo = new OtherInfo();
        final BoardPanel boardPanel = new BoardPanel();

        board.addPropertyChangeListener(boardPanel);
        board.addPropertyChangeListener(nextPiece);

        // instantiate the timer and set the delay to 500ms
        timer = new Timer(TIME_CONST,
                e -> {
                    new Board().step();
                });

        // start the timer
        timer.start();

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
        int width = DEFAULT_WIDTH;
        int height = DEFAULT_HEIGHT;
        boolean validInput = false;

        // Prompt the user for the board size
        while (!validInput) {
            final String input = JOptionPane.showInputDialog(null,
                    "Enter board size (format: width x height):",
                    width + " x " + height);
            if (input == null) {
                // User clicked Cancel
                System.exit(0);
            }
            final String[] dimensions = input.split("x");
            if (dimensions.length == 2) {
                try {
                    width = Integer.parseInt(dimensions[0].trim());
                    height = Integer.parseInt(dimensions[1].trim());
                    if (width > 0 && height > 0) {
                        validInput = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid board size");
                    }
                } catch (final NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid board size");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid board size");
            }
        }
        return new Dimension(width, height);
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