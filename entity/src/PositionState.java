import java.util.ArrayList;
import java.util.List;

public class PositionState {
    public List<IPosition> ladder;
    public List<IPosition> polarization;
    private Disc stateOwner;

    public PositionState(IPosition IPosition) {
        this.ladder = new ArrayList<>();
        this.polarization = new ArrayList<>();
        this.stateOwner = IPosition.getOccupiedBy();
    }

    public void addPositionToLadder(IPosition IPosition) {
        ladder.add(IPosition);
    }

    public void addPositionToPolarization(IPosition IPosition) {
        polarization.add(IPosition);
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
