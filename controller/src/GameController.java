public class GameController {

    private IPlayer playerX;
    private IPlayer playerO;
    private UIConsole console;
    private BoardSession boardSession;

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
        String player1Name = console.askUser("Who plays X? (to use AI, name should start with [AI])");
        addNewPlayer(player1Name, Disc.X);

        String player2Name = console.askUser("Who plays O? (to use AI, name should start with [AI])");
        addNewPlayer(player2Name, Disc.O);
    }

    private void addNewPlayer(String player1Name, Disc disc) {

        IPlayer player = IsAI(player1Name)
                ? new AIPlayer(console, player1Name, disc)
                : new HumanPlayer(console, player1Name, disc);

        if (disc == Disc.X){
            playerX = player;
        }
        if (disc == Disc.O){
            playerO = player;
        }
    }

    private boolean IsAI(String playerName) {
        return playerName.toUpperCase().startsWith("AI ");
    }

    private void setBoard() {
        this.boardSession = BoardSession.getInstance();
        this.boardSession.resetBoard();
        console.setBoard(boardSession.toString());
        console.displayBoard();
    }

    private void play() {
        IPosition currentPosition = new Position();
        IPlayer currentPlayer = playerX;
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

            winner = boardSession.getWinner(currentPosition);

            currentPlayer = currentPlayer == playerX
                    ? playerO
                    : playerX;
        }
        console.informUser("And the winner is: " + winner);
    }

    private Disc playOn(IPosition nextPosition) {

        Disc occupiedBy = boardSession.getOccupiedBy(nextPosition.getRow(), nextPosition.getCol());

        if (occupiedBy == Disc.None) {
            boardSession.setPosition(nextPosition);
            console.setBoard(boardSession.toString());
            console.displayBoard();
            occupiedBy = nextPosition.getOccupiedBy();
        } else if (occupiedBy == Disc.O || occupiedBy == Disc.X) {
            occupiedBy = Disc.Occupied;
        }
        return occupiedBy;
    }
}
