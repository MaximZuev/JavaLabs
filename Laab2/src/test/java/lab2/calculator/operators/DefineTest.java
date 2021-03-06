package lab2.calculator.operators;

import lab2.context.Context;
import lab2.exeptions.InvalidArgsForOperatorsExceptions;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DefineTest {

    @Test
    public void execute() throws InvalidArgsForOperatorsExceptions {
        Define definition = new Define();
        Context context = new Context();
        definition.execute(context, Arrays.asList("a", "4"));
        assertEquals(4, context.get("a"), 0.0);
    }
}