
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
        String coordinates =  console.askUser(this.name + " ,where do you want to play? (row, col)");

        Position position = new Position(coordinates, disc);
        return position;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Disc getDiscColor() {
        return this.disc;
    }
}
