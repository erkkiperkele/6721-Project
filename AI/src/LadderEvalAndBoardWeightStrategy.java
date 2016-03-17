import java.util.Arrays;

public class LadderEvalAndBoardWeightStrategy implements IStrategy {


    private Board boardWeights;
    private Board boardCounterWeights;
    private Disc aiDisc;


    public LadderEvalAndBoardWeightStrategy(Disc aiDisc) {
        this.boardCounterWeights = BoardWeights.getBoardCounterWeights();
        this.boardWeights = BoardWeights.getBoardWeights();
        this.aiDisc = aiDisc;
    }

    /**
     * Calculates the score depending on:
     * - how close is the player from winning on all possible states
     * - how important is the position on which the player played
     * (important for example at the beginning, when no state is even close to winning to take the best positions)
     *
     * @param node
     * @return
     */
    @Override
    public HeuristicNode calculateScore(HeuristicNode node, Disc currentTurn) {

        double score = 0.0;

        score += calculateScoreOnDiagonal(node, StateName.Left);
        score += calculateScoreOnDiagonal(node, StateName.Right);
        score += calculateStaticWeightScore(node);  //Helps decide between 2 states with identical score (eg: 0)
        node.setScore(score);

        return node;
    }

    private double calculateScoreOnDiagonal(HeuristicNode node, StateName stateName) {

        Double score = 0.0;

        // This gives you the last position played, other positions have already been calculated
        IPosition pos = node.getParentNode().getPosition();

        Coor[] stateCoors = coordinatesHelper.getStatesCoor(stateName);
        for (Coor stateCoor : stateCoors) {
            PositionState positionState = node.getBoard().getPositionState(pos, stateName, stateCoor);
            score += calculatePositionStateScore(positionState);
        }

        return score;
    }

    /**
     * This method gives a score for a given position depending on:
     * - is the state countered by the opponent
     * - how many discs have already been played on this state (how close am I to win on this state)
     *
     * @param positionState
     * @return
     */
    private Double calculatePositionStateScore(PositionState positionState) {

        Double discScore = 1.0;
        Double scoreFactor = 1.0;
        Double totalScore = 0.0;

        if (!positionState.canWin()) {
            return totalScore;
        }

        if (positionState.isBeingCounterPolarized()) {
            scoreFactor = 0.5;
        }

        // -1 because we don't want to count the position currently being evaluated in the final score (so it can be 0)
        double positionsPlayedOnLadder = positionState.ladder
                .stream()
                .filter(p -> p.getOccupiedBy() == positionState.getOwner())
                .count() - 1;

        // Give more importance to a position that has a single state but very close to winning
        // Than to a position that has 10 possible states, but each of those state have a single disc.
        discScore *= positionsPlayedOnLadder;

        totalScore = scoreFactor * positionsPlayedOnLadder * discScore;

        return totalScore;
    }

    /**
     * This simple heuristic checks the static value of each position occupied by AI on the board
     * A board's position score is the sum of the possibilities it offers to attain a goal state (ladder)
     * Added to the possibilities it has to counter a goal state (polarization)
     *
     * @param node the state to be evaluated
     * @return the total score for all positions on the board.
     */
    private double calculateStaticWeightScore(HeuristicNode node) {

        int boardWeightsSum = Arrays.stream(node.getBoard().getPositions())
                .filter(p -> p.getOccupiedBy() == this.aiDisc)
                .map(p -> (ScoredPosition) boardWeights.getPosition(p.getRow(), p.getCol()))
                .mapToInt(s -> s.getScore())
                .sum();

        int boardCounterWeightSum = Arrays.stream(node.getBoard().getPositions())
                .filter(p -> p.getOccupiedBy() == this.aiDisc)
                .map(p -> (ScoredPosition) boardCounterWeights.getPosition(p.getRow(), p.getCol()))
                .mapToInt(s -> s.getScore())
                .sum();

        return boardWeightsSum + boardCounterWeightSum;
    }
}
