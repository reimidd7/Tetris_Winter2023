package view;

import javax.swing.*;
import java.awt.*;

/** This class creates the Frame for the tetris game. It includes a menu bar at the top */
public class Frame extends JFrame {

    /** Width of frame. */
    private static final int WIDTH = 400;

    /** Height of frame. */
    private static final int HEIGHT = 400;

    ///Possible constructor needed later.///
    public Frame() {
        super();
//        createAndShowGUI();
        final JFrame tetrisFrame = createTetrisFrame(WIDTH, HEIGHT);

        final JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(WIDTH, HEIGHT);
        final NextPiece nextPiece = new NextPiece();
        final OtherInfo otherInfo = new OtherInfo();
        final BoardPanel boardPanel = new BoardPanel();
        panel.add(nextPiece, BorderLayout.LINE_END);
        panel.add(otherInfo, BorderLayout.LINE_START);
        panel.add(boardPanel, BorderLayout.CENTER);
        tetrisFrame.add(panel, BorderLayout.CENTER);
//        tetrisFrame.setLayout(new BorderLayout());
//
//        JPanel eastInfo = new JPanel(new BorderLayout());
//        eastInfo.add(nextPiece, BorderLayout.PAGE_START);
//        eastInfo.add(otherInfo, BorderLayout.PAGE_END);

        tetrisFrame.add(boardPanel, BorderLayout.PAGE_START);
        tetrisFrame.add(nextPiece, BorderLayout.CENTER);
        tetrisFrame.add(otherInfo, BorderLayout.LINE_END);
//        tetrisFrame.add(eastInfo, BorderLayout.EAST);
//        tetrisFrame.add(nextPiece, BorderLayout.EAST);


        //eastInfo.setVisible(true);
        tetrisFrame.setVisible(true);
    }

    //Used to create the rough frame for our tetris project.
    public static JFrame createTetrisFrame(final int WIDTH, final int HEIGHT) {
        final JFrame tFrame = new JFrame();
        tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tFrame.setSize(WIDTH, HEIGHT); //set height and width

        tFrame.setJMenuBar(createFileMenu()); //add menu bar to the frame
        tFrame.setVisible(true);

        return tFrame;
    }

    //Create a file menu with event handlers.
    public static JMenuBar createFileMenu() {
        final JMenuBar menuBar = new JMenuBar();

        final JMenu menu = new JMenu("File");
        final JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(
                e -> {
                    System.exit(0);
                });
        menu.add(exit);
        menuBar.add(menu);

        final JMenu help = new JMenu("Help");
        final JMenuItem rules = new JMenuItem("Rules");
        rules.addActionListener(
                e -> {
                    JOptionPane.showMessageDialog(null, "Best of Luck");
                });
        help.add(rules);
        menuBar.add(help);

        final JMenu about = new JMenu("About");
        final JMenuItem abt = new JMenuItem("About Game");
        abt.addActionListener(
                e -> {
                    JOptionPane.showMessageDialog(null, "This is a tetris game");
                });
        about.add(abt);
        menuBar.add(about);

        return menuBar;
    }

    private static void createAndShowGUI() {
//        final JFrame tetrisFrame = createTetrisFrame(WIDTH, HEIGHT);
//        final NextPiece nextPiece = new NextPiece();
//        final OtherInfo otherInfo = new OtherInfo();
//        final BoardPanel boardPanel = new BoardPanel();
//
//        tetrisFrame.setLayout(new BorderLayout());
////
////        JPanel eastInfo = new JPanel(new BorderLayout());
////        eastInfo.add(nextPiece, BorderLayout.PAGE_START);
////        eastInfo.add(otherInfo, BorderLayout.PAGE_END);
//
//        tetrisFrame.add(boardPanel, BorderLayout.CENTER);
//        tetrisFrame.add(nextPiece, BorderLayout.LINE_START);
//        tetrisFrame.add(otherInfo, BorderLayout.LINE_END);
////        tetrisFrame.add(eastInfo, BorderLayout.EAST);
////        tetrisFrame.add(nextPiece, BorderLayout.EAST);
//
//
//        //eastInfo.setVisible(true);
//        tetrisFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Frame();
    }
}
