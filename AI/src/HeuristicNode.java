public class HeuristicNode {
    private Board board;
    private HeuristicNode parentNode;
    private int score;
    private Position position;

    public HeuristicNode(Board board, HeuristicNode parentNode) {

        this.board = board;
        this.parentNode = parentNode;
    }

    public Board getBoard() {
        return board;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public HeuristicNode extendNode(Position nodeToExtend, Disc currentTurn) {


        Board newState = new Board(this.getBoard());
        Position positionToPlay = new Position(nodeToExtend.getRow(), nodeToExtend.getCol(), currentTurn);
        newState.setPosition(positionToPlay);

        HeuristicNode childNode = new HeuristicNode(newState, this);

        return childNode;
    }
}
