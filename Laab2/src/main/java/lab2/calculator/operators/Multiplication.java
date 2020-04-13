package lab2.calculator.operators;

import lab2.context.Context;
import lab2.exeptions.InvalidArgsForOperatorsExceptions;
import lab2.exeptions.NotEnoughElementsInTheStackExceptions;
import org.apache.log4j.Logger;

import java.util.List;

public class Multiplication implements Operator {
    private static final Logger multiplicationLog = Logger.getLogger(Multiplication.class);
    @Override
    public void execute(Context context, List<String> args) throws InvalidArgsForOperatorsExceptions, NotEnoughElementsInTheStackExceptions {
        double value, value1, value2;

        if (args.size() != 0) {
            throw new InvalidArgsForOperatorsExceptions();
        }
        if (context.getStackSize() < 2) {
            throw new NotEnoughElementsInTheStackExceptions();
        }

        value1 = context.pop();
        value2 = context.pop();

        value = value1 * value2;

        context.push(value);

        multiplicationLog.info(value1 + " * " + value2 + " = " + value + ".");
    }
}
