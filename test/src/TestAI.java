import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAI {


    private static UIConsole console = UIConsole.getInstance();
    private static BoardSession boardSession;

    @BeforeClass
    public static void testSetup() {
        boardSession = BoardSession.getInstance();

        boardSession.resetBoard();
        console.setBoard(boardSession.toString());
    }

    @Test
    public void testAgressivity() {

        boardSession.resetBoard();
        IPlayer aiPlayer = new AIPlayer("", Disc.X);

        playOn(aiPlayer.takeTurn());
        playOn(aiPlayer.takeTurn());
        playOn(aiPlayer.takeTurn());
        playOn(aiPlayer.takeTurn());
        playOn(aiPlayer.takeTurn());

        IPosition lastPositionPlayed =aiPlayer.takeTurn();
        playOn(lastPositionPlayed);

//        console.displayBoard();

        Disc winner = boardSession.getWinner(lastPositionPlayed);
        console.informUser("Winner is: " + winner);
        assertEquals(Disc.X, winner);
    }

    @Test
    public void testDefense(){

        boardSession.resetBoard();
        IPlayer aiPlayer = new AIPlayer("", Disc.X);

        playOn(new Position(3,7, Disc.O));
        playOn(new Position(4,7, Disc.O));
        playOn(new Position(4,6, Disc.O));
        playOn(new Position(5,6, Disc.O));

        IPosition lastPositionPlayed =aiPlayer.takeTurn();
        playOn(lastPositionPlayed);


        Disc winner = boardSession.getWinner(lastPositionPlayed);
        console.informUser("Winner is: " + winner);

        assertEquals(Disc.None, winner);
        assertEquals(lastPositionPlayed.getRow(), 5);
        assertEquals(lastPositionPlayed.getCol(), 5);
    }

    private Disc playOn(IPosition nextPosition) {

        boardSession.setPosition(nextPosition);
        console.setBoard(boardSession.toString());
        console.displayBoard();
        Disc occupiedBy = nextPosition.getOccupiedBy();
        return occupiedBy;
    }
}
