package lab2.calculator.operators;

import lab2.context.Context;
import org.apache.log4j.Logger;

import java.util.List;

public class Push implements Operator {
    @Override
    public void execute(Context context, List<String> args) {
        double value;

        if (args.size() != 1) {
            Logger.getLogger(this.getClass()).error("Invalid args.");
            throw new IllegalArgumentException();
        }

        try {
            value = context.get(args.get(0));
        } catch (NullPointerException e) {
            try {
                value = Double.parseDouble(args.get(0));
            } catch (NumberFormatException ex) {
                Logger.getLogger(this.getClass()).error("Invalid args.");
                throw new IllegalArgumentException();
            }
        }

        context.push(value);

        Logger.getLogger(this.getClass()).info("PUSH " + value + ".");
    }
}