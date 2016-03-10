import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBoardController {

    private UIConsole console = UIConsole.getInstance();
    private static BoardSession boardSession;

    @BeforeClass
    public static void testSetup() {
        boardSession = BoardSession.getInstance();
    }

    @AfterClass
    public static void testCleanup() {
        boardSession.resetBoard();
    }

    @Test
    public void testXWinsRight() {
        boardSession.resetBoard();
        Disc winner = Disc.None;

        playOn(new Position(1, 7, Disc.X));
        playOn(new Position(2, 6, Disc.X));
        playOn(new Position(3, 5, Disc.X));
        playOn(new Position(2, 7, Disc.X));
        playOn(new Position(3, 6, Disc.X));

        console.displayBoard();

        winner = boardSession.getWinner(new Position(1, 7, Disc.X));
        console.informUser("And the winner is: " + winner);
        winner = boardSession.getWinner(new Position(2, 6, Disc.X));
        console.informUser("And the winner is: " + winner);
        winner = boardSession.getWinner(new Position(3, 5, Disc.X));
        console.informUser("And the winner is: " + winner);
        winner = boardSession.getWinner(new Position(2, 7, Disc.X));
        console.informUser("And the winner is: " + winner);
        winner = boardSession.getWinner(new Position(3, 6, Disc.X));
        console.informUser("And the winner is: " + winner);

        assertEquals(winner, Disc.X);
    }

    @Test
    public void testOWinsLeft() {
        boardSession.resetBoard();
        Disc winner = Disc.None;

        playOn(new Position(5, 11, Disc.O));
        playOn(new Position(6, 11, Disc.O));
        playOn(new Position(6, 12, Disc.O));
        playOn(new Position(7, 12, Disc.O));
        playOn(new Position(7, 13, Disc.O));

        console.displayBoard();

        winner = boardSession.getWinner(new Position(7, 13, Disc.O));
        console.informUser("testXWinsRight winner is: " + winner);

        assertEquals(winner, Disc.O);
    }

    @Test
    public void testXUnPolarized() {
        boardSession.resetBoard();
        Disc winner = Disc.None;

        playOn(new Position(4, 7, Disc.X));
        playOn(new Position(5, 6, Disc.X));
        playOn(new Position(6, 5, Disc.X));
        playOn(new Position(5, 7, Disc.X));
        playOn(new Position(6, 6, Disc.X));
        playOn(new Position(4, 5, Disc.O));
        playOn(new Position(6, 7, Disc.O));

        console.displayBoard();

        winner = boardSession.getWinner(new Position(6, 6, Disc.X));
        console.informUser("testXUnPolarized winner is: " + winner);

        assertEquals(winner, Disc.None);
    }

    @Test
    public void testTie() {
        boardSession.resetBoard();
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

        winner = boardSession.getWinner(new Position(6, 6, Disc.X));
        console.informUser("testTie winner is: " + winner);

        assertEquals(winner, Disc.Tie);
    }

    @Test
    public void testNotTie() {
        boardSession.resetBoard();
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

        winner = boardSession.getWinner(new Position(6, 6, Disc.X));
        console.informUser("testNotTie winner is: " + winner);

        assertEquals(winner, Disc.X);
    }

    private Disc playOn(IPosition nextPosition) {

        boardSession.setPosition(nextPosition);
        console.setBoard(boardSession.toString());
        Disc occupiedBy = boardSession.getOccupiedBy(nextPosition.getRow(), nextPosition.getCol());
        return occupiedBy;
    }
}
