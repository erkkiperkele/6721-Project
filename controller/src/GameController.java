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

//        //REFACTOR: To be extracted in its own class
//        testXWinsRight();
//        testOWinsLeft();
//        testXUnPolarized();
//        testTie();
//        testNotTie();

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


    //TODO: To be extracted in its own class.
    private void testXWinsRight() {
        boardController.resetBoard();
        Disc winner = Disc.None;

        playOn(new Position(1, 7, Disc.X));
        playOn(new Position(2, 6, Disc.X));
        playOn(new Position(3, 5, Disc.X));
        playOn(new Position(2, 7, Disc.X));
        playOn(new Position(3, 6, Disc.X));

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
    }

    public void testOWinsLeft() {
        boardController.resetBoard();
        Disc winner = Disc.None;

        playOn(new Position(5, 11, Disc.O));
        playOn(new Position(6, 11, Disc.O));
        playOn(new Position(6, 12, Disc.O));
        playOn(new Position(7, 12, Disc.O));
        playOn(new Position(7, 13, Disc.O));

        winner = boardController.getWinner(new Position(7, 13, Disc.O));
        console.informUser("And the winner is: " + winner);
    }

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


        winner = boardController.getWinner(new Position(6, 6, Disc.X));
        console.informUser("And the unpolarized winner is: " + winner);
    }

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

        winner = boardController.getWinner(new Position(6, 6, Disc.X));
        console.informUser("And the tie winner is: " + winner);
    }

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

        winner = boardController.getWinner(new Position(6, 6, Disc.X));
        console.informUser("And the not tie winner is: " + winner);
    }
}
