public class AIPlayer implements IPlayer {

    private UIConsole console;
    private String name;
    private Disc disc;
    private MiniMaxStrategy strategy;

    public AIPlayer(UIConsole console, String name, Disc disc) {
        this.console = console;
        this.name = name;
        this.disc = disc;
        this.strategy = new MiniMaxStrategy(disc);
    }

    @Override
    public IPosition takeTurn() {

        Board currentBoard = BoardSession.getInstance().cloneBoard();
        return this.strategy.findNextMove(currentBoard);
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
