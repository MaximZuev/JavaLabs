package lab2.calculator.operators;

import lab2.context.Context;
import org.apache.log4j.Logger;

import java.util.List;

public class Define implements Operator {
    @Override
    public void execute(Context context, List<String> args) {
        String key;
        double value;

        if (args.size() != 2) {
            Logger.getLogger(this.getClass()).error("Invalid args.");
            throw new IllegalArgumentException();
        }

        key = args.get(0);

        try {
            value = Double.parseDouble(args.get(1));
        } catch (NumberFormatException e) {
            Logger.getLogger(this.getClass()).error("Invalid args.");
            throw new IllegalArgumentException("DEFINE");
        }

        context.put(key, value);

        Logger.getLogger(this.getClass()).info("DEFINE: " + key + " = " + value + ".");
    }
}
