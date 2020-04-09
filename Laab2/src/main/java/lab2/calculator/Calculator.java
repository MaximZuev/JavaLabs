package lab2.calculator;

import lab2.calculator.operators.Operator;
import lab2.context.Context;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Calculator {
    private Context context;

    public Calculator() {
        context = new Context();
    }

    public void calculate(String fileName) throws IOException {
        Scanner scanner = fileName.equals("") ?  new Scanner(System.in) : new Scanner(new File(fileName));

        while(scanner.hasNextLine()){
            List<String> args;
            String [] words;
            String operatorName;
            Operator operator;
            words = scanner.nextLine().split("\\s+");

            if (words[0].equals("")) {
                continue;
            }

            operatorName = words[0];
            args = Arrays.asList(words).subList(1, words.length);

            operator = OperatorFactory.getInstance().createOperator(operatorName);

            if (operator != null) {
                try {
                    operator.execute(context, args);
                } catch (IllegalArgumentException | EmptyStackException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
