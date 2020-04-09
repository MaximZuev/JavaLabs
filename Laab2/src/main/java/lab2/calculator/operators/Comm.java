package lab2.calculator.operators;

import lab2.context.Context;
import org.apache.log4j.Logger;

import java.util.List;

public class Comm implements Operator{
    @Override
    public void execute(Context context, List<String> args) {
        Logger.getLogger(this.getClass()).info("Comment: " + String.join(" ", args) + ".");
    }
}