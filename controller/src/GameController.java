public class GameController {

    private IPlayer player1;
    private IPlayer player2;
    private UIConsole console;
    private BoardController boardController;

    public GameController() {
        this.console = UIConsole.getInstance();
    }

    public void startNewGame() {

        initialize();
        play();
    }

    private void initialize() {

        setPlayers();
        setBoard();
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
        Position currentPosition = new Position();
        IPlayer currentPlayer = player1;
        Disc winner = Disc.None;

        while (winner == Disc.None) {
            Disc occupiedBy = Disc.None;
            while (occupiedBy != currentPlayer.getDiscColor()) {

                currentPosition = currentPlayer.takeTurn();
                occupiedBy = playOn(currentPosition);

                if (occupiedBy != currentPlayer.getDiscColor()) {
                    console.informUser(occupiedBy + " : This position is not available. Please try again.");
                }
            }

            winner = boardController.getWinner(currentPosition);

            currentPlayer = currentPlayer == player1
                    ? player2
                    : player1;
        }
        console.informUser("And the winner is: " + winner);
    }

    private Disc playOn(Position nextPosition) {

        Disc occupiedBy = boardController.getOccupiedBy(nextPosition.getRow(), nextPosition.getCol());

        if (occupiedBy == Disc.None) {
            boardController.setPosition(nextPosition);
            console.setBoard(boardController.toString());
            console.displayBoard();
            occupiedBy = nextPosition.getOccupiedBy();
        } else if (occupiedBy == Disc.O || occupiedBy == Disc.X) {
            occupiedBy = Disc.Occupied;
        }
        return occupiedBy;
    }
}
