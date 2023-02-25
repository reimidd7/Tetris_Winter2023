package util;

import model.Board;

/**
 * Sandbox driver fort testing git.
 * @author rick_adams/group1-tetris.
 * @version 2023 Winter.
 *
 */
public final class SandBox {
    private SandBox() {

    }
    /**
     * Main method driver.
     * @param theArgs command-line argument.
     */
    public static void main(final String[] theArgs) {
        final Board board = new Board();
        board.newGame();
        System.out.println(board);

        board.step();
        System.out.println(board);
        board.rotateCW();
        System.out.println(board);
        board.rotateCW();
        System.out.println(board);
        board.rotateCW();
        System.out.println(board);
        board.rotateCW();
        System.out.println(board);
        board.drop();
        System.out.println(board);

    }

}
