public class AIPlayer implements IPlayer {

    private String name;
    private Disc disc;
    private MiniMax minimax;

    public AIPlayer(String name, Disc disc) {
        this.name = name;
        this.disc = disc;

        this.minimax = new MiniMax(decideOnStrategy(), disc);
    }

    private IStrategy decideOnStrategy(){


        IStrategy strategy = new LadderEvalAndBoardWeightStrategy(this.disc);
        return strategy;
    }

    @Override
    public IPosition takeTurn() {

        Board currentBoard = BoardSession.getInstance().cloneBoard();
        return this.minimax.findNextMove(currentBoard);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Disc getDiscColor() {
        return disc;
    }
}
