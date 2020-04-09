package lab2;

import lab2.calculator.Calculator;

import java.io.IOException;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        log.info("The calculator started working.");

        try {
            calculator.calculate("src/main/resources/file.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("The calculator is finished.");
    }
}
