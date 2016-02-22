
public class StartGame {


    //Board Size: h^2
    //Each points has 2 possible winning states for a high of 2
    //Goal state: (h-2)^2 -> 50 goal states

    public static void main(String[] args) {

        UIConsole console = UIConsole.getInstance();
        console.setTitle("Welcome to Pola\n");

        GameController game = new GameController();

        boolean wantsToPlay = true;
        while(wantsToPlay) {
            game.startNewGame();

            console.reset();
            String answer = console.askUser("Play another game?\n");
            wantsToPlay = answer.toLowerCase().contains("y");
        }
        console.informUser("Thanks for playing!");
    }
}
