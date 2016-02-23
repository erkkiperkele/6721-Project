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

    public String toString(){
        String boardString = "";

        int rowNumber = 1;
        int boarHeight = this.board.getHeight();
        while(rowNumber <= boarHeight){
            boardString += printRow(rowNumber) + "\n";
            ++rowNumber;
        }

        boardString += "\n1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13 \n";
        return boardString;
    }

    private String printRow(int rowNumber){
        List<Position> row = board.getRow(rowNumber);
        int whiteSpacesCount = board.getHeight() - rowNumber;

        String rowString = printTabs(whiteSpacesCount);

        for(Position position : row){
            rowString += position.getOccupiedByString() + "\t";
        }
        rowString += printTabs(whiteSpacesCount -1);
        return rowString;
    }

    private String printTabs(int whiteSpacesCount) {
        String tabs="";
        int i = 0;
        while(i < whiteSpacesCount){
            tabs += "\t";
            ++i;
        }
        return tabs;
    }
}
