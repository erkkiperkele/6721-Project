
public class Position {
    private int colNumber;
    private int rowNumber;
    private Disc occupiedBy;

    public Position(int row, int column, Disc occupiedBy) {
        this.colNumber = column;
        this.rowNumber = row;
        this.occupiedBy = occupiedBy;
    }

    public Position(String positionPlayed, Disc occupiedBy) {

        //REFACTOR: Extract this in its own class.
        //TODO: Support for letters
        String[] coordinates = positionPlayed
                .trim()
                .toLowerCase()
                .split(",");

        this.rowNumber = Integer.parseInt(coordinates[0]);
        this.colNumber = Integer.parseInt(coordinates[1]);
        this.occupiedBy = occupiedBy;
    }

    public Position() {
        this.occupiedBy = Disc.None;
    }

    public Position(Position source) {

        this.rowNumber = source.getRow();
        this.colNumber = source.getCol();
        this.occupiedBy = source.getOccupiedBy();
    }

    public Disc getOccupiedBy() {
        return occupiedBy;
    }

    public int getRow() {
        return rowNumber;
    }

    public int getCol() {
        return colNumber;
    }

    public String getOccupiedByString() {
        switch (this.occupiedBy) {
            case X:
                return "X";
            case O:
                return "O";
            default:
                return ".";
        }
    }
}
