
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
    public String getName() {
        return this.name;
    }

    @Override
    public Disc getDiscColor() {
        return this.disc;
    }

    @Override
    public IPosition takeTurn() {
        String coordinates = console.askUser(this.name + " ,where do you want to play? (row, col)");

        if(coordinates.toLowerCase().contains("exit")){
            console.informUser("thanks for playing!");
            System.exit(1);
        }

        IPosition position = new Position(coordinates, disc);
        return position;
    }
}
