import java.util.Arrays;

public class MiniMax {

    private int depthBound = 3;
    private Disc aiDisc;
    private IStrategy strategy;

    public MiniMax(IStrategy strategy, Disc aiDisc) {
        this.strategy = strategy;
        this.aiDisc = aiDisc;
    }

    public IPosition findNextMove(Board currentState) {

        HeuristicNode currentNode = new HeuristicNode(currentState);
        HeuristicNode evaluatedNode = minimax(currentNode, depthBound, this.aiDisc);
        System.out.println("Final Score: " + evaluatedNode.getScore());
        return evaluatedNode.getPosition();
    }

    private HeuristicNode minimax(HeuristicNode currentNode, int depth, Disc currentTurn) {

        Position[] nodesToExtend = Arrays.stream(currentNode.getBoard().getPositions())
                .filter(p -> p.getOccupiedBy() == Disc.None)
                .toArray(Position[]::new);

        if(nodesToExtend.length < 25){
            this.depthBound = 4;
        }

        boolean isLeaf = nodesToExtend.length == 0;

        if (depth == 0 || isLeaf) {
            return this.strategy.calculateScore(currentNode, currentTurn);
        }

        if (IsMax(currentTurn)) {
            HeuristicNode bestPosition = null;

            for (Position nodeToExtend : nodesToExtend) {
                HeuristicNode childNode = currentNode.extendNode(nodeToExtend, currentTurn);

                if (childNode.willWin()) {
                    childNode.setScore(1000 - depth);
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

                if (childNode.willWin()) {
                    childNode.setScore(-1000 + depth);
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


    private boolean IsMax(Disc currentDisc) {
        return currentDisc == this.aiDisc;
    }
}
