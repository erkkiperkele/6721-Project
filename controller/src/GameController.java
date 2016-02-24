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
        play();

    }

    private void initialize() {

        setPlayers();
        setBoard();

        //TODO: Remove once implemented
        runTests();
    }

    private void setPlayers() {
        String player1Name = console.askUser("Who plays X?");
        player1 = new HumanPlayer(console, player1Name, Disc.X);

        String player2Name = console.askUser("Who plays O?");
        player2 = new HumanPlayer(console, player2Name, Disc.O);
    }

    private void setBoard() {
        this.boardController = BoardController.getInstance();
        console.setBoard(boardController.toString());
        console.displayBoard();
    }

    private void play() {
        Position currentPosition;
        IPlayer currentPlayer = player1;
        Winner winner = Winner.None;

        while (winner == Winner.None)
        {
            Disc occupiedBy = Disc.None;
            while (occupiedBy != currentPlayer.getDiscColor()){
                currentPosition = currentPlayer.takeTurn();
                occupiedBy = playOn(currentPosition);
                if(occupiedBy != currentPlayer.getDiscColor()){
                    console.informUser(occupiedBy + " : This position is not available. Please try again.");
                }
            }

            winner = determineWinner();
            currentPlayer = currentPlayer == player1
                    ? player2
                    : player1;
        }
    }

    private Disc playOn(Position nextPosition) {

        Disc occupiedBy = boardController.getOccupiedBy(nextPosition.getRowNumber(), nextPosition.getColNumber());

        if(occupiedBy == Disc.None){
            boardController.setPosition(nextPosition);
            console.setBoard(boardController.toString());
            console.displayBoard();
            occupiedBy = nextPosition.getOccupiedBy();
        }
        else if (occupiedBy == Disc.O || occupiedBy == Disc.X){
            occupiedBy = Disc.Occupied;
        }
        return occupiedBy;
    }

    private Winner determineWinner() {
        //TODO: real logic to determine if game is over.
        return Winner.Player2;
    }

    private void runTests(){
        playOn(new Position(1,7,Disc.X));
        playOn(new Position(2,8,Disc.O));
        playOn(new Position(7,7,Disc.O));
        playOn(new Position(7,13,Disc.O));
    }
}
