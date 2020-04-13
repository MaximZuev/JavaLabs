package lab2.calculator;

import lab2.calculator.operators.Operator;
import lab2.context.Context;
import lab2.exeptions.InvalidArgsForOperatorsExceptions;
import lab2.exeptions.NotEnoughElementsInTheStackExceptions;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Calculator {
    private Context context;
    private static final Logger calculatorLog = Logger.getLogger(Calculator.class);

    public Calculator() {
        context = new Context();
    }

    public void calculate(InputStream inputStream) throws IOException {
        Scanner scanner = new Scanner(inputStream);

        while(scanner.hasNextLine()){
            List<String> args;
            String [] words;
            String operatorName;
            Operator operator;
            words = scanner.nextLine().split("\\s+");

            if (words[0].equals("")) {
                continue;
            }

            if (words[0].equals("end")) {
                break;
            }

            operatorName = words[0];
            args = Arrays.asList(words).subList(1, words.length);

            operator = OperatorFactory.getInstance().createOperator(operatorName);

            if (operator != null) {
                try {
                    operator.execute(context, args);
                } catch (InvalidArgsForOperatorsExceptions ex) {
                    calculatorLog.error("Invalid args for " + operatorName + ".");
                } catch (NotEnoughElementsInTheStackExceptions ex) {
                    calculatorLog.error("Not enough elements for " + operatorName + " in stack.");
                }
            } else {
                calculatorLog.error("Operator " + operatorName + " was not found.");
            }
        }
    }
}