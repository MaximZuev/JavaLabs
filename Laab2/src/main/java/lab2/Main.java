package lab2;

import lab2.calculator.Calculator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class Main {
    private static final Logger mainLog = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        InputStream inputStream;

        mainLog.info("The calculator started working.");

        if (args.length == 1) {
            try {
                inputStream = new FileInputStream(args[0]);

                mainLog.info("Used " + args[0] + ".");
            } catch (FileNotFoundException e) {
                inputStream = System.in;

                mainLog.error("File " + args[0] + " not found. Used System.in.");
            }
        } else {
            inputStream = System.in;

            mainLog.info("Used System.in.");
        }

        try {
            calculator.calculate(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        mainLog.info("The calculator is finished.\n");
    }
}
