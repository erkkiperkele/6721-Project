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

        HeuristicNode currentNode = new HeuristicNode(currentState, null);
        HeuristicNode evaluatedNode = minimax(currentNode, depthBound, this.aiDisc);
        return evaluatedNode.getPosition();
    }

    private HeuristicNode minimax(HeuristicNode currentNode, int depth, Disc currentTurn) {

        Position[] nodesToExtend = Arrays.stream(currentNode.getBoard().getPositions())
                .filter(p -> p.getOccupiedBy() == Disc.None)
                .toArray(Position[]::new);

        boolean isLeaf = nodesToExtend.length == 0;

        if (depth == 0 || isLeaf) {
            return calculateStaticWeightScore(currentNode);
        }

        if (IsMax(currentTurn)) {
            HeuristicNode bestPosition = null;

            for (Position nodeToExtend : nodesToExtend) {
                HeuristicNode childNode = currentNode.extendNode(nodeToExtend, currentTurn);

                //TODO: calculate next minimax only if currentPlayer didn't win,
                // else return very high or very low score

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

                //TODO: calculate next minimax only if currentPlayer didn't win,
                // else return very high or very low score

                HeuristicNode candidateValue = minimax(childNode, depth - 1, currentTurn.invert());
                if (worsePosition == null || candidateValue.getScore() <= worsePosition.getScore()) {
                    worsePosition = candidateValue;
                }
            }

            return worsePosition;
        }
    }

    /**
     * This simple heuristic checks the static value of each position occupied by AI on the board
     * A board's position score is the sum of the possibilities it offers to attain a goal state (ladder)
     * Added to the possibilities it has to counter a goal state (polarization)
     *
     * @param node the state to be evaluated
     * @return the total score for all positions on the board.
     */
    private HeuristicNode calculateStaticWeightScore(HeuristicNode node) {

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

        node.setScore(boardWeightsSum + boardCounterWeightSum);
        return node;
    }


    private boolean IsMax(Disc currentDisc) {
        return currentDisc == this.aiDisc;
    }
}
