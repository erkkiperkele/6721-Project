
public class Position {
    private int colNumber;
    private int rowNumber;
    private Disc occupiedBy;

    public Position(int row, int column, Disc occupiedBy) {
        this.colNumber = column;
        this.rowNumber = row;
        this.occupiedBy = occupiedBy;
    }

    public Position(String row, String column, Disc occupiedBy) {
        this.colNumber = Integer.parseInt(column);
        this.rowNumber = Integer.parseInt(row);       //TODO: Won't work. Implement properly
        this.occupiedBy = occupiedBy;
    }

    public Position() {
        this.occupiedBy = Disc.None;
    }

    public String getOccupiedByString(){

        switch (this.occupiedBy){
            case Black:
                return "X";
            case White:
                return "O";
            default:
                return ".";
        }
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColNumber() {
        return colNumber;
    }
}
