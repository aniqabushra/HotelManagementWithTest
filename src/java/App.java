import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        View view = new View();
        Controller controller = new Controller(view);
        controller.run();
    }
}
