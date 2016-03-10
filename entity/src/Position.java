
public class Position implements IPosition {
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

    public Position(IPosition source) {

        this.rowNumber = source.getRow();
        this.colNumber = source.getCol();
        this.occupiedBy = source.getOccupiedBy();
    }

    @Override
    public Disc getOccupiedBy() {
        return occupiedBy;
    }

    @Override
    public int getRow() {
        return rowNumber;
    }

    @Override
    public int getCol() {
        return colNumber;
    }

    @Override
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
