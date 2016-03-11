import java.util.List;

public class BoardSession {
    private static BoardSession ourInstance = new BoardSession();

    private Board board;

    public static BoardSession getInstance() {
        return ourInstance;
    }

    private BoardSession() {
        this.board = new Board();
    }

    public Board cloneBoard(){
        return new Board(this.board);
    }

    public void setPosition(IPosition position) {
        try{
            board.setPosition(position);
        }
        catch(IndexOutOfBoundsException e) {
            // It is tolerated to try and set an invalid position.
        }
    }

    public Disc getOccupiedBy(int row, int col) {
        return board.getOccupiedBy(row, col);
    }

    public void resetBoard() {
        this.board = new Board();
    }

    public Disc getWinner(IPosition position){
        return this.board.getWinner(position);
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
        List<IPosition> row = board.getRow(rowNumber);
        int whiteSpacesCount = board.getHeight() - rowNumber;

        String rowString = printTabs(whiteSpacesCount);

        for (IPosition IPosition : row) {
            rowString += IPosition.getOccupiedByString() + "\t";
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
