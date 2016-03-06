import java.util.ArrayList;
import java.util.List;

public class PositionState {
    public List<Position> ladder;
    public List<Position> polarization;
    private Disc stateOwner;

    public PositionState(Position position) {
        this.ladder = new ArrayList<>();
        this.polarization = new ArrayList<>();
        this.stateOwner = position.getOccupiedBy();
    }

    public void addPositionToLadder(Position position){
        ladder.add(position);
    }

    public void addPositionToPolarization(Position position){
        polarization.add(position);
    }

    public boolean hasWon() {

        Disc opponent = stateOwner == Disc.O
                ? Disc.X
                : Disc.O;

        long polarizationCount = polarization.stream()
                .filter(position -> position.getOccupiedBy() == opponent)
                .count();
        long positionCount = ladder.stream()
                .filter(position -> position.getOccupiedBy() == stateOwner)
                .count();

        return positionCount == 5 && polarizationCount != 2;
    }

    public Disc getOwner() {
        return stateOwner;
    }
}
