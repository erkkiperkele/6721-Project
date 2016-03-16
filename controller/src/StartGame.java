
public class StartGame {

    public static void main(String[] args) {

        UIConsole console = UIConsole.getInstance();
        console.informUser("Welcome to Pola\n");
        GameController game = new GameController();

        boolean wantsToPlay = true;
        while (wantsToPlay) {
            game.startNewGame();

            console.reset();
            String answer = console.askUser("Play another game?\n");
            wantsToPlay = answer.toLowerCase().contains("y");
        }
        console.informUser("Thanks for playing!");
    }
}
