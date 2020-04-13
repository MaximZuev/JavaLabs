package lab2.calculator.operators;

import lab2.context.Context;
import org.apache.log4j.Logger;

import java.util.EmptyStackException;
import java.util.List;

public class Pop implements Operator{
    @Override
    public void execute(Context context, List<String> args) throws IllegalArgumentException, EmptyStackException {
        double value;

        if (args.size() != 0) {
            Logger.getLogger(this.getClass()).error("Invalid args.");
            throw new IllegalArgumentException();
        }
        if (context.getStackSize() < 1) {
            Logger.getLogger(this.getClass()).error("Stack is empty.");
            throw new EmptyStackException();
        }

        value = context.pop();

        Logger.getLogger(this.getClass()).info("POP " + value + ".");
    }
}