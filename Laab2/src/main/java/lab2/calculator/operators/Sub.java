package lab2.calculator.operators;

import lab2.context.Context;
import org.apache.log4j.Logger;

import java.util.EmptyStackException;
import java.util.List;

public class Sub implements Operator {
    @Override
    public void execute(Context context, List<String> args) {
        double value, value1, value2;

        if (args.size() != 0) {
            Logger.getLogger(this.getClass()).error("Invalid args.");
            throw new IllegalArgumentException();
        }
        if (context.getStackSize() < 2) {
            Logger.getLogger(this.getClass()).error("Stack is empty.");
            throw new EmptyStackException();
        }

        value1 = context.pop();
        value2 = context.pop();

        value = value2 - value1;

        context.push(value);

        Logger.getLogger(this.getClass()).info(value1 + " - " + value2 + " = " + value + ".");
    }
}
