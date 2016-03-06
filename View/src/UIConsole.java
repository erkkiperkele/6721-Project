import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class UIConsole {

    private static UIConsole instance = new UIConsole();

    private String header;
    private String body;
    private PrintStream output;
    private Scanner input;

    private UIConsole() {
        output = System.out;
        input = new Scanner(System.in);

        this.header = "Welcome to Pola!";
        this.body = "\n\n\n\n\n\n\n\n\n";
    }

    public static UIConsole getInstance() {
        return instance;
    }

    private String getHeader() {
        return header;
    }

    private String getBody() {
        return body;
    }

    public void setBoard(String body) {
        this.body = body + "\n";
    }

    public String askUser(String question) {
        output.println(question);
        return input.nextLine();
    }

    public void informUser(String info) {
        output.println(info);
    }

    public void displayBoard() {
        output.print(getBody());
    }

    public void reset() {
        this.header = "";
        this.body = "";
    }
}
