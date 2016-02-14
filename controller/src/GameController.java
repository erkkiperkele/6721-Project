public class GameController {

    private IPlayer player1;
    private IPlayer player2;

    public GameController() {
        //TODO: get console injected

    }

    public void startNewGame(){

        initialize();
        play();

    }

    private void initialize() {
        //TODO:
        // setBoard();
        // setPlayers();
    }

    private void play() {
        Position currentPosition;
        IPlayer currentPlayer = player1;
        Winner winner = Winner.None;

        while (winner == Winner.None)
        {
            currentPosition = currentPlayer.takeTurn();
            playOn(currentPosition);
            winner = determineWinner();
            currentPlayer = currentPlayer == player1
                    ? player2
                    : player1;

        }
    }

    private void playOn(Position nextPosition) {
        //TODO:
        // - validate position
        // - update UI
    }

    private Winner determineWinner() {
        //TODO: real logic to determine if game is over.
        return Winner.None;
    }
}
