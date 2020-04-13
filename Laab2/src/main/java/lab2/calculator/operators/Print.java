package lab2.calculator.operators;

import lab2.context.Context;
import lab2.exeptions.InvalidArgsForOperatorsExceptions;
import lab2.exeptions.NotEnoughElementsInTheStackExceptions;
import org.apache.log4j.Logger;

import java.util.List;

public class Print implements Operator {
    private static final Logger printLog = Logger.getLogger(Print.class);
    @Override
    public void execute(Context context, List<String> args) throws InvalidArgsForOperatorsExceptions, NotEnoughElementsInTheStackExceptions {
        double value;

        if (args.size() != 0) {
            throw new InvalidArgsForOperatorsExceptions();
        }
        if (context.getStackSize() < 1) {
            throw new NotEnoughElementsInTheStackExceptions();
        }

        value = context.peek();

        System.out.println(value);

        printLog.info("PRINT " + value + ".");
    }
}
