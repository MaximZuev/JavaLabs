import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Factory factory = new Factory();
            factory.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
