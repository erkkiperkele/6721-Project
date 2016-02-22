import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Board {

    private final int height = 7;
    private final int width = 13;
    private Position[] board;


    public Board() {
        board = new Position[height*height];
        populateBoard();

    }

    public int getHeight() {
        return height;
    }

    private void populateBoard() {
        int i = this.board.length;
        while(i>0){
            this.board[i-1] = new Position();
            --i;
        }
    }

    public List<Position> getRow(int rowNumber){

        int start = getPositionIndex(rowNumber,1);
        int end = start + 2 * rowNumber -2;

        List<Position> row = new ArrayList<>();
        int index = 0;
        while(start<=end){
            row.add(index, board[start]);
            ++start;
            ++index;
        }
        return row;
    }

    public Position getPosition(int rowNumber, int colNumber) {
        int index = getPositionIndex(rowNumber, colNumber);
        return board[index];
    }

    public void setPosition(Position position){
        int index = getPositionIndex(position.getRowNumber(), position.getColNumber());
        board[index] = position;
    }

    private int getPositionIndex(int rowNumber, int colNumber) {
        int rowIndex = rowNumber * (rowNumber-2) + 1;
        int index = rowIndex + colNumber -1;
        return index;
    }
}
