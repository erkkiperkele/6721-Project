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
        console.displayBoard();
    }

    @Test
    public void testAgressivity() {
        IPlayer aiPlayer = new AIPlayer("", Disc.X);

        playOn(aiPlayer.takeTurn());
        playOn(aiPlayer.takeTurn());
        playOn(aiPlayer.takeTurn());
        playOn(aiPlayer.takeTurn());
//        playOn(aiPlayer.takeTurn());

        console.displayBoard();
        //TODO: Assert won
    }

    // REFACTOR: Should not duplicate this code from GameController
    private Disc playOn(IPosition nextPosition) {

        Disc occupiedBy = boardSession.getOccupiedBy(nextPosition.getRow(), nextPosition.getCol());

        boardSession.setPosition(nextPosition);
        console.setBoard(boardSession.toString());
        console.displayBoard();
        occupiedBy = nextPosition.getOccupiedBy();
        return occupiedBy;
    }
}
