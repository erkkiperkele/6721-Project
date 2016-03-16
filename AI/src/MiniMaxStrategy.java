import java.util.Arrays;

public class MiniMaxStrategy {

    private final int depthBound = 2;
    private Board boardWeights;
    private Board boardCounterWeights;
    private Disc aiDisc;

    public MiniMaxStrategy(Disc aiDisc) {
        this.boardWeights = BoardWeights.getBoardWeights();
        this.boardCounterWeights = BoardWeights.getBoardCounterWeights();
        this.aiDisc = aiDisc;
    }

    public IPosition findNextMove(Board currentState) {

        HeuristicNode currentNode = new HeuristicNode(currentState);
        HeuristicNode evaluatedNode = minimax(currentNode, depthBound, this.aiDisc);
        return evaluatedNode.getPosition();
    }

    private HeuristicNode minimax(HeuristicNode currentNode, int depth, Disc currentTurn) {

        Position[] nodesToExtend = Arrays.stream(currentNode.getBoard().getPositions())
                .filter(p -> p.getOccupiedBy() == Disc.None)
                .toArray(Position[]::new);

        boolean isLeaf = nodesToExtend.length == 0;

        if (depth == 0 || isLeaf) {
            return calculateScore(currentNode, currentTurn);
        }

        if (IsMax(currentTurn)) {
            HeuristicNode bestPosition = null;

            for (Position nodeToExtend : nodesToExtend) {
                HeuristicNode childNode = currentNode.extendNode(nodeToExtend, currentTurn);

                 if(childNode.willWin()){
                     childNode.setScore(1000);
                     return childNode;
                 }

                HeuristicNode candidateValue = minimax(childNode, depth - 1, currentTurn.invert());
                if (bestPosition == null || candidateValue.getScore() >= bestPosition.getScore()) {

                    Position position = new Position(nodeToExtend.getRow(), nodeToExtend.getCol(), currentTurn);
                    candidateValue.setPosition(position);
                    bestPosition = candidateValue;
                }
            }

            return bestPosition;

        } else {
            HeuristicNode worsePosition = null;

            for (Position nodeToExtend : nodesToExtend) {
                HeuristicNode childNode = currentNode.extendNode(nodeToExtend, currentTurn);

                if(childNode.willWin()){
                    childNode.setScore(-1000);
                    return childNode;
                }

                HeuristicNode candidateValue = minimax(childNode, depth - 1, currentTurn.invert());
                if (worsePosition == null || candidateValue.getScore() <= worsePosition.getScore()) {
                    worsePosition = candidateValue;
                }
            }

            return worsePosition;
        }
    }

    /**
     * Calculates the score depending on:
     * - how close is the player from winning on all possible states
     * - how important is the position on which the player played
     *   (important for example at the beginning, when no state is even close to winning to take the best positions)
     * @param node
     * @return
     */
    private HeuristicNode calculateScore(HeuristicNode node, Disc currentTurn) {

        double score = 0.0;
        int minScoreFactor = IsMax(currentTurn)
                ? 1
                : -1;

        score += calculateScoreOnDiagonal(node, StateName.Left);
        score += calculateScoreOnDiagonal(node, StateName.Right);
        score += calculateStaticWeightScore(node);
        score *= minScoreFactor;
        node.setScore(score);

        return node;
    }

    private double calculateScoreOnDiagonal(HeuristicNode node, StateName stateName) {

        Double score = 0.0;

        Coor[] stateCoors = coordinatesHelper.getStatesCoor(stateName);
        for (Coor stateCoor : stateCoors) {
            PositionState positionState = node.getBoard().getPositionState(node.getPosition(), stateName, stateCoor);
            score += calculatePositionStateScore(positionState);
        }

        return score;
    }

    /**
     * This method gives a score for a given position depending on:
     * - is the state countered by the opponent
     * - how many discs have already been played on this state (how close am I to win on this state)
     * @param positionState
     * @return
     */
    private Double calculatePositionStateScore(PositionState positionState){

        Double discScore = 10.0;
        Double scoreFactor = 1.0;
        Double totalScore = 0.0;

        if (!positionState.canWin()){
            return totalScore;
        }

        if(positionState.isBeingCounterPolarized()){
            scoreFactor = 0.5;
        }

        // -1 because we don't want to count the position currently being evaluated in the final score (so it can be 0)
        long positionsPlayedOnLadder = positionState.ladder
                .stream()
                .filter(p -> p.getOccupiedBy() == positionState.getOwner())
                .count() -1;

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

    private boolean IsMax(Disc currentDisc) {
        return currentDisc == this.aiDisc;
    }
}
