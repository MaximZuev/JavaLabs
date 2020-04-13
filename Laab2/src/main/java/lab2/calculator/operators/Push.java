package lab2.calculator.operators;

import lab2.context.Context;
import lab2.exeptions.InvalidArgsForOperatorsExceptions;
import org.apache.log4j.Logger;

import java.util.List;

public class Push implements Operator {
    private static final Logger pushLog = Logger.getLogger(Push.class);
    @Override
    public void execute(Context context, List<String> args) throws InvalidArgsForOperatorsExceptions {
        double value;

        if (args.size() != 1) {
            throw new InvalidArgsForOperatorsExceptions();
        }

        if (context.isDefine(args.get(0))) {
            value = context.get(args.get(0));
        } else {
            try {
                value = Double.parseDouble(args.get(0));
            } catch (NumberFormatException ex) {
                throw new InvalidArgsForOperatorsExceptions();
            }
        }

        context.push(value);

        pushLog.info("PUSH " + value + ".");
    }
}