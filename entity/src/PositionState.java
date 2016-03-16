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

    public Disc getOwner() {
        return stateOwner;
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

    public boolean canWin() {

        return !isLadderInvalid() && !isCounterPolarized();
    }

    public boolean isBeingCounterPolarized() {
        boolean canBeCounterPolarized = !this.polarization
                .stream()
                .anyMatch(p -> p.getOccupiedBy() == Disc.Invalid);
        boolean counterPolarizationHasStarted = getLadderCounterPolarizationCount() == 1;

        return canBeCounterPolarized && counterPolarizationHasStarted;
    }

    private boolean isLadderInvalid() {
        return this.ladder
                .stream()
                .anyMatch(p ->
                        (p.getOccupiedBy() == this.getOwner().invert())
                        ||
                        (p.getOccupiedBy() == Disc.Invalid)
                );
    }

    private boolean isCounterPolarized() {

        return getLadderCounterPolarizationCount() == 2;
    }

    private long getLadderCounterPolarizationCount() {
        return this.polarization
                .stream()
                .filter(p -> p.getOccupiedBy() == this.getOwner().invert())
                .count();
    }
}
