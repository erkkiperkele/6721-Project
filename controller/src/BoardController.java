import java.util.List;

public class BoardController {
    private static BoardController ourInstance = new BoardController();

    private Board board;

    public static BoardController getInstance() {
        return ourInstance;
    }

    private BoardController() {
        this.board = new Board();
    }

    public void setPosition(Position position) {
        board.setPosition(position);
    }

    public Disc getOccupiedBy(int row, int col) {
        return board.getOccupiedBy(row, col);
    }

    public void resetBoard() {
        this.board = new Board();
    }

    public Disc getWinner(Position position) {

        Disc winsOnLeft = getWinnerOnDiagonal(position, StateName.Left);
        if (winsOnLeft != null) {
            return winsOnLeft;
        }

        Disc winsOnRight = getWinnerOnDiagonal(position, StateName.Right);
        if (winsOnRight != null) {
            return winsOnRight;
        }

        if (this.board.isFull()) {
            return Disc.Tie;
        }

        return Disc.None;
    }

    private Disc getWinnerOnDiagonal(Position position, StateName stateName) {
        Coor[] stateCoors = coordinatesHelper.getStatesCoor(stateName);
        for (Coor stateCoor : stateCoors) {
            PositionState positionState = getPositionState(position, stateName, stateCoor);
            if (positionState.hasWon()) {
                return positionState.getOwner();
            }
        }
        return null;
    }

    public PositionState getPositionState(Position position, StateName stateName, Coor stateCoor) {

        PositionState positionState = new PositionState(position);

        Coor[] ladderCoor = coordinatesHelper.getLadderCoor(stateName, stateCoor);
        for (Coor coor : ladderCoor) {

            int colToCheck = position.getCol() + coor.getX();
            int rowToCheck = position.getRow() + coor.getY();
            Position currentLadderPos = this.board.getPosition(
                    rowToCheck,
                    colToCheck);

            if (currentLadderPos.getOccupiedBy() != position.getOccupiedBy()) {
                return positionState;
            }
            positionState.addPositionToLadder(new Position(currentLadderPos));
        }


        int invert = stateName == StateName.Left
                ? 1
                : -1;
        Coor[] polarizationCoor = new Coor[]{
                new Coor(ladderCoor[0].getX() + 2 * invert, ladderCoor[0].getY()),
                new Coor(ladderCoor[0].getX(), ladderCoor[0].getY() + 2)
        };
        for (Coor coor : polarizationCoor) {

            int colToCheck = position.getCol() + coor.getX();
            int rowToCheck = position.getRow() + coor.getY();
            Position currentPolarizationPos = this.board.getPosition(
                    rowToCheck,
                    colToCheck);

            if (currentPolarizationPos.getOccupiedBy() != position.getOccupiedBy().invert()) {
                return positionState;
            }
            positionState.addPositionToPolarization(new Position(currentPolarizationPos));
        }

        return positionState;
    }

    public String toString() {
        String boardString = "";

        int rowNumber = 1;
        int boarHeight = this.board.getHeight();
        while (rowNumber <= boarHeight) {
            boardString += printRow(rowNumber) + "\n";
            ++rowNumber;
        }

        boardString += "\n1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13 \n";
        return boardString;
    }

    private String printRow(int rowNumber) {
        List<Position> row = board.getRow(rowNumber);
        int whiteSpacesCount = board.getHeight() - rowNumber;

        String rowString = printTabs(whiteSpacesCount);

        for (Position position : row) {
            rowString += position.getOccupiedByString() + "\t";
        }
        rowString += printTabs(whiteSpacesCount - 1);
        return rowString;
    }

    private String printTabs(int whiteSpacesCount) {
        String tabs = "";
        int i = 0;
        while (i < whiteSpacesCount) {
            tabs += "\t";
            ++i;
        }
        return tabs;
    }
}
