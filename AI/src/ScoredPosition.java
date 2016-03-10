public class ScoredPosition implements IPosition{
    private IPosition position;
    private int score;

    public ScoredPosition(IPosition position, int score) {
        this.position = position;
        this.score = score;
    }

    @Override
    public Disc getOccupiedBy() {
        return this.position.getOccupiedBy();
    }

    @Override
    public int getRow() {
        return this.position.getRow();
    }

    @Override
    public int getCol() {
        return this.position.getCol();
    }

    @Override
    public String getOccupiedByString() {
        return this.position.getOccupiedByString();
    }

    public int getScore() {
        return score;
    }
}
