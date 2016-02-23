
public class HumanPlayer implements IPlayer {

    private UIConsole console;
    private String name;
    private Disc disc;

    public HumanPlayer(UIConsole console, String name, Disc disc) {
        this.console = console;
        this.name = name;
        this.disc = disc;
    }

    @Override
    public Position takeTurn() {
        String coordinates =  console.askUser("Where do you want to play? (col,row)");

        Position position = new Position(coordinates, disc);
        return position;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
