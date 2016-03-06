import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int height = 7;
    private final int width = 13;
    private Position[] board;
    private int discCount;


    public Board() {
        board = new Position[height * height];
        populateBoard();

    }

    public int getHeight() {
        return height;
    }

    private void populateBoard() {
        int i = this.board.length;
        while (i > 0) {
            this.board[i - 1] = new Position();
            --i;
        }
    }

    public List<Position> getRow(int rowNumber) {

        int start = getPositionIndex(rowNumber, height - rowNumber + 1);
        int end = start + 2 * rowNumber - 2;

        List<Position> row = new ArrayList<>();
        int index = 0;
        while (start <= end) {
            row.add(index, board[start]);
            ++start;
            ++index;
        }
        return row;
    }

    public Position getPosition(int row, int col) {
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

    public void setPosition(Position position)
        throws IndexOutOfBoundsException {

        if (isValid(position.getRow(), position.getCol())) {
            int index = getPositionIndex(position.getRow(), position.getCol());
            ++discCount;
            board[index] = position;
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
}
