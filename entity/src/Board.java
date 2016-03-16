import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int height = 7;
    private final int width = 13;
    private IPosition[] board;
    private int discCount;

    public Board() {
        this.board = new Position[height * height];

        this.discCount = 0;
        populateBoard();
    }

    public Board(Board board) {
        this.board = new Position[height * height];

        discCount = board.getDiscCount();

        int index = 0;
        for (IPosition position : board.getPositions()){
            this.board[index] = new Position(position);
            ++index;
        }
    }

    public Board(IPosition[] positions){

        this.board = positions;
        this.discCount = height * height;
    }

    public int getHeight() {
        return height;
    }

    private void populateBoard() {

        int index = 0;
        int row = 1;
        while (row <= height){
            int colmin = height-row+1;
            int colmax = height +row-1;
            int col = colmin;
            while(col <= colmax){
                this.board[index] = new Position(row, col, Disc.None);
                ++index;
                ++col;
            }
            ++row;
        }

    }

    public List<IPosition> getRow(int rowNumber) {

        int start = getPositionIndex(rowNumber, height - rowNumber + 1);
        int end = start + 2 * rowNumber - 2;

        List<IPosition> row = new ArrayList<>();
        int index = 0;
        while (start <= end) {
            row.add(index, board[start]);
            ++start;
            ++index;
        }
        return row;
    }

    public IPosition getPosition(int row, int col) {
        if (isValid(row, col)) {
            int index = getPositionIndex(row, col);
            return board[index];
        }

        return new Position(row, col, Disc.Invalid);
    }

    public Disc getOccupiedBy(int row, int col) {

        boolean colIsValid = col > (height - row) && col < (height + row);
        boolean rowIsValid = row > 0 && row < 8;

        if (colIsValid && rowIsValid) {
            return getPosition(row, col).getOccupiedBy();
        } else {
            return Disc.Invalid;
        }
    }

    public void setPosition(IPosition position)
        throws IndexOutOfBoundsException {

        if (isValid(position.getRow(), position.getCol())) {
            int index = getPositionIndex(position.getRow(), position.getCol());
            ++discCount;
            board[index] = new Position(position.getRow(), position.getCol(), position.getOccupiedBy());
        }
        else{
            throw new IndexOutOfBoundsException("board.setPosition(): position is invalid");
        }
    }

    private int getPositionIndex(int rowNumber, int colNumber) {
        int rowIndex = rowNumber * (rowNumber - 2) + 1;
        int index = rowIndex - height + rowNumber + colNumber - 1;
        return index;
    }

    public boolean isFull() {
        return discCount == height * height;
    }

    private boolean isValid(int row, int col) {

        boolean colIsValid = col > (height - row) && col < (height + row);
        if (!colIsValid) {
            return colIsValid;
        }

        boolean rowIsValid = row > 0 && row < 8;
        return rowIsValid;
    }

    public int getDiscCount() {
        return discCount;
    }

    public final IPosition[] getPositions() {
        return this.board;
    }

    public Disc getWinner(IPosition position) {

        Disc winsOnLeft = getWinnerOnDiagonal(position, StateName.Left);
        if (winsOnLeft != null) {
            return winsOnLeft;
        }

        Disc winsOnRight = getWinnerOnDiagonal(position, StateName.Right);
        if (winsOnRight != null) {
            return winsOnRight;
        }

        if (this.isFull()) {
            return Disc.Tie;
        }

        return Disc.None;
    }

    private Disc getWinnerOnDiagonal(IPosition position, StateName stateName) {
        Coor[] stateCoors = coordinatesHelper.getStatesCoor(stateName);
        for (Coor stateCoor : stateCoors) {
            PositionState positionState = getPositionState(position, stateName, stateCoor);
            if (positionState.hasWon()) {
                return positionState.getOwner();
            }
        }
        return null;
    }

    public PositionState getPositionState(IPosition position, StateName stateName, Coor stateCoor) {

        PositionState positionState = new PositionState(position);

        Coor[] ladderCoor = coordinatesHelper.getLadderCoor(stateName, stateCoor);
        for (Coor coor : ladderCoor) {

            int colToCheck = position.getCol() + coor.getX();
            int rowToCheck = position.getRow() + coor.getY();
            IPosition currentLadderPos = this.getPosition(
                    rowToCheck,
                    colToCheck);
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
            IPosition currentPolarizationPos = this.getPosition(
                    rowToCheck,
                    colToCheck);

            positionState.addPositionToPolarization(new Position(currentPolarizationPos));
        }

        return positionState;
    }
}
