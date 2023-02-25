package viewer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/** This class creates the Frame for the tetris game. It includes a menu bar at the top */
public class Frame {

    /** Width of frame. */
    private static final int WIDTH = 1000;

    /** Height of frame. */
    private static final int HEIGHT = 500;

    ///Possible constructor needed later.///

    //main method TESTING PURPOSES ONLY... for now
    public static void main(final String[] theArgs) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createTetrisFrame(WIDTH, HEIGHT); // the method call for createTetrisFrame
            }
        });
    }

    //Used to create the rough frame for our tetris project.
    public static void createTetrisFrame(final int theWidth, final int theHeight) {
        final JFrame tFrame = new JFrame();
        tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tFrame.setSize(theWidth, theHeight); //set height and width
        tFrame.setVisible(true);

        tFrame.setJMenuBar(createFileMenu()); //add menu bar to the frame
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
}
