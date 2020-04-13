package lab2.calculator.operators;

import lab2.context.Context;
import lab2.exeptions.InvalidArgsForOperatorsExceptions;
import org.apache.log4j.Logger;

import java.util.List;

public class Define implements Operator {
    private static final Logger defineLog = Logger.getLogger(Define.class);
    @Override
    public void execute(Context context, List<String> args) throws InvalidArgsForOperatorsExceptions {
        String key;
        double value;

        if (args.size() != 2) {
            throw new InvalidArgsForOperatorsExceptions();
        }

        key = args.get(0);

        try {
            value = Double.parseDouble(args.get(1));
        } catch (NumberFormatException ex) {
            throw new InvalidArgsForOperatorsExceptions();
        }

        context.put(key, value);

        defineLog.info("DEFINE: " + key + " = " + value + ".");
    }
}
