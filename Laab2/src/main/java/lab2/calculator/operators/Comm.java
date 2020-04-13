package lab2.calculator.operators;

import lab2.context.Context;
import org.apache.log4j.Logger;

import java.util.List;

public class Comm implements Operator{
    private static final Logger commLog = Logger.getLogger(Comm.class);
    @Override
    public void execute(Context context, List<String> args) {
        commLog.info("Comment: " + String.join(" ", args) + ".");
    }
}