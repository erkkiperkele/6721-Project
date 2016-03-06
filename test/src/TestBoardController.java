import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBoardController {

    private UIConsole console = UIConsole.getInstance();
    private static BoardController boardController;

    @BeforeClass
    public static void testSetup() {
        boardController = BoardController.getInstance();
    }

    @AfterClass
    public static void testCleanup() {
        boardController.resetBoard();
    }

    @Test
    public void testXWinsRight() {
        boardController.resetBoard();
        Disc winner = Disc.None;

        playOn(new Position(1, 7, Disc.X));
        playOn(new Position(2, 6, Disc.X));
        playOn(new Position(3, 5, Disc.X));
        playOn(new Position(2, 7, Disc.X));
        playOn(new Position(3, 6, Disc.X));

        console.displayBoard();

        winner = boardController.getWinner(new Position(1, 7, Disc.X));
        console.informUser("And the winner is: " + winner);
        winner = boardController.getWinner(new Position(2, 6, Disc.X));
        console.informUser("And the winner is: " + winner);
        winner = boardController.getWinner(new Position(3, 5, Disc.X));
        console.informUser("And the winner is: " + winner);
        winner = boardController.getWinner(new Position(2, 7, Disc.X));
        console.informUser("And the winner is: " + winner);
        winner = boardController.getWinner(new Position(3, 6, Disc.X));
        console.informUser("And the winner is: " + winner);

        assertEquals(winner, Disc.X);
    }

    @Test
    public void testOWinsLeft() {
        boardController.resetBoard();
        Disc winner = Disc.None;

        playOn(new Position(5, 11, Disc.O));
        playOn(new Position(6, 11, Disc.O));
        playOn(new Position(6, 12, Disc.O));
        playOn(new Position(7, 12, Disc.O));
        playOn(new Position(7, 13, Disc.O));

        console.displayBoard();

        winner = boardController.getWinner(new Position(7, 13, Disc.O));
        console.informUser("testXWinsRight winner is: " + winner);

        assertEquals(winner, Disc.O);
    }

    @Test
    public void testXUnPolarized() {
        boardController.resetBoard();
        Disc winner = Disc.None;

        playOn(new Position(4, 7, Disc.X));
        playOn(new Position(5, 6, Disc.X));
        playOn(new Position(6, 5, Disc.X));
        playOn(new Position(5, 7, Disc.X));
        playOn(new Position(6, 6, Disc.X));
        playOn(new Position(4, 5, Disc.O));
        playOn(new Position(6, 7, Disc.O));

        console.displayBoard();

        winner = boardController.getWinner(new Position(6, 6, Disc.X));
        console.informUser("testXUnPolarized winner is: " + winner);

        assertEquals(winner, Disc.None);
    }

    @Test
    public void testTie() {
        boardController.resetBoard();
        Disc winner = Disc.None;

        int row = 7;
        while (row > 0) {
            int col = 13;
            while (col > 0) {

                Disc player = row % 2 == 0
                        ? Disc.X
                        : Disc.O;
                playOn(new Position(row, col, player));
                --col;
            }
            --row;
        }

        console.displayBoard();

        winner = boardController.getWinner(new Position(6, 6, Disc.X));
        console.informUser("testTie winner is: " + winner);

        assertEquals(winner, Disc.Tie);
    }

    @Test
    public void testNotTie() {
        boardController.resetBoard();
        Disc winner = Disc.None;

        int row = 7;
        while (row > 0) {
            int col = 13;
            while (col > 0) {

                Disc player = Disc.X;
                playOn(new Position(row, col, player));
                --col;
            }
            --row;
        }

        console.displayBoard();

        winner = boardController.getWinner(new Position(6, 6, Disc.X));
        console.informUser("testNotTie winner is: " + winner);

        assertEquals(winner, Disc.X);
    }

    private Disc playOn(Position nextPosition) {

        boardController.setPosition(nextPosition);
        console.setBoard(boardController.toString());
        Disc occupiedBy = boardController.getOccupiedBy(nextPosition.getRow(), nextPosition.getCol());
        return occupiedBy;
    }
}
