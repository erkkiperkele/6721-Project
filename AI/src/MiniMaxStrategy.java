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

        return minimax(currentState, depthBound, this.aiDisc);
    }

    private ScoredPosition minimax(Board currentState, int depth, Disc currentTurn) {


        Position[] nodesToExtend = Arrays.stream(currentState.getPositions())
                .filter(p -> p.getOccupiedBy() == Disc.None)
                .toArray(Position[]::new);

        boolean isLeaf = nodesToExtend.length == 0;

        if (depth == 0 || isLeaf){
            return calculateStaticWeightScore(currentState);
        }

        if(IsMax(currentTurn)){
            ScoredPosition bestPosition = new ScoredPosition(null, -999999);

            for(Position nodeToExtend : nodesToExtend){
                Board nextState = extendNode(currentState, nodeToExtend, currentTurn);

                //TODO: calculate next minimax only if currentPlayer didn't win,
                // else return very high or very low score

                ScoredPosition candidateValue = minimax(nextState, depth - 1, currentTurn.invert());
                if (candidateValue.getScore() >= bestPosition.getScore()){

                    if(depth == this.depthBound){
                        Position position = new Position(nodeToExtend.getRow(), nodeToExtend.getCol(), currentTurn);
                        bestPosition = new ScoredPosition(position, candidateValue.getScore());
                    }
                }
            }
            return bestPosition;
        }

        else{
            ScoredPosition worsePosition = new ScoredPosition(null, 999999);

            for(Position nodeToExtend : nodesToExtend){
                Board nextNode = extendNode(currentState, nodeToExtend, currentTurn);

                //TODO: calculate next minimax only if currentPlayer didn't win,
                // else return very high or very low score

                ScoredPosition candidateValue = minimax(nextNode, depth - 1, currentTurn.invert());
                if (candidateValue.getScore() <= worsePosition.getScore()){
                    worsePosition = candidateValue;
                }
            }
            return worsePosition;
        }
    }

    //REFACTOR: Minimax should not know how to extend a leaf
    private Board extendNode(Board currentState, Position nodeToExtend, Disc currentTurn){
        Board newState = new Board(currentState);
        Position positionToPlay = new Position(nodeToExtend.getRow(), nodeToExtend.getCol(), currentTurn);
        newState.setPosition(positionToPlay);

        return newState;
    }

    /**
     * This simple heuristic checks the static value of each position occupied by AI on the board
     * A board's position score is the sum of the possibilities it offers to attain a goal state (ladder)
     * Added to the possibilities it has to counter a goal state (polarization)
     * @param state the state to be evaluated
     * @return the total score for all positions on the board.
     */
    private ScoredPosition calculateStaticWeightScore(Board state) {

        int boardWeightsSum = Arrays.stream(state.getPositions())
                .filter(p -> p.getOccupiedBy() == this.aiDisc)
                .map(p -> (ScoredPosition)boardWeights.getPosition(p.getRow(), p.getCol()))
                .mapToInt(s -> s.getScore())
                .sum();

        int boardCounterWeightSum = Arrays.stream(state.getPositions())
                .filter(p -> p.getOccupiedBy() == this.aiDisc)
                .map(p -> (ScoredPosition)boardCounterWeights.getPosition(p.getRow(), p.getCol()))
                .mapToInt(s -> s.getScore())
                .sum();

        return new ScoredPosition(null, boardWeightsSum + boardCounterWeightSum);
    }


    private boolean IsMax(Disc currentDisc){
        return currentDisc == this.aiDisc;
    }
}
