package viewer;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Frame {
    private static final int WIDTH = 1000;

    private static final int HEIGHT = 500;

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
                createTetrisFrame(WIDTH, HEIGHT);
            } // the method call for createTetrisFrame
        });
    }

    //Used to create the rough frame for our tetris project.
    public static void createTetrisFrame(final int theWidth, final int theHeight) {
        final JFrame tFrame = new JFrame();
        tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tFrame.setSize(theWidth, theHeight); //set height and width
        tFrame.setVisible(true);
    }

    //Create a file menu with event handlers
}
