public class HeuristicNode {
    private Board board;
    private HeuristicNode parentNode;
    private double score;
    private Position position;

    public HeuristicNode(Board board) {
        this.board = board;
    }

    public HeuristicNode(Position nodeToExtend, Board board, HeuristicNode parentNode) {

        this.board = board;
        this.parentNode = parentNode;
        this.position = nodeToExtend;
    }

    public Board getBoard() {
        return board;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public HeuristicNode getParentNode() {
        return parentNode;
    }

    public HeuristicNode extendNode(Position nodeToExtend, Disc currentTurn) {


        Board newState = new Board(this.getBoard());
        Position positionToPlay = new Position(nodeToExtend.getRow(), nodeToExtend.getCol(), currentTurn);
        newState.setPosition(positionToPlay);

        HeuristicNode childNode = new HeuristicNode(positionToPlay, newState, this);

        return childNode;
    }

    public boolean willWin(){

        if(this.position != null){
            return this.board.getWinner(this.position) != Disc.None;
        }

        return false;
    }
}
