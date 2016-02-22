public class GameController {

    private IPlayer player1;
    private IPlayer player2;
    private UIConsole console;
    private BoardController boardController;

    public GameController() {
        this.console = UIConsole.getInstance();
    }

    public void startNewGame(){

        initialize();
//        play();

    }

    private void initialize() {
        this.boardController = BoardController.getInstance();
        console.setBoard(boardController.toString());
        console.display();


        playOn(new Position(1,1,Disc.Black));
        playOn(new Position(7,13,Disc.White));
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
        boardController.setPosition(nextPosition);
        console.setBoard(boardController.toString());
        console.display();
    }

    private Winner determineWinner() {
        //TODO: real logic to determine if game is over.
        return Winner.None;
    }
}
