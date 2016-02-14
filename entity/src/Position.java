
public class Position {
    private int column;
    private int row;
    private Disc occupiedBy;

    public Position(int column, int row, Disc occupiedBy) {
        this.column = column;
        this.row = row;
        this.occupiedBy = occupiedBy;
    }

    public Position(String column, String row, Disc occupiedBy) {
        this.column = Integer.parseInt(column);
        this.row = Integer.parseInt(row);       //TODO: Won't work. Implement properly
        this.occupiedBy = occupiedBy;
    }
}
