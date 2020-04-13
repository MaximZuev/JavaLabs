package lab2.calculator.operators;

import lab2.context.Context;
import lab2.exeptions.InvalidArgsForOperatorsExceptions;
import lab2.exeptions.NotEnoughElementsInTheStackExceptions;
import org.apache.log4j.Logger;

import java.util.List;

public class Sqrt implements Operator{
    private static final Logger sqrtLog = Logger.getLogger(Sqrt.class);
    @Override
    public void execute(Context context, List<String> args) throws InvalidArgsForOperatorsExceptions, NotEnoughElementsInTheStackExceptions {
        double value, newValue;

        if (args.size() != 0) {
            throw new InvalidArgsForOperatorsExceptions();
        }
        if (context.getStackSize() < 1) {
            throw new NotEnoughElementsInTheStackExceptions();
        }

        value = context.pop();

        newValue = Math.sqrt(value);

        context.push(newValue);

        sqrtLog.info("SQRT(" + value + ") = " + newValue + ".");
    }
}
