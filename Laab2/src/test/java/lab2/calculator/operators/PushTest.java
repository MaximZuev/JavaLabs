package lab2.calculator.operators;

import lab2.context.Context;
import lab2.exeptions.InvalidArgsForOperatorsExceptions;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class PushTest {

    @Test
    public void execute() throws InvalidArgsForOperatorsExceptions {
        Push push = new Push();
        Context context = new Context();
        push.execute(context, Collections.singletonList("8"));
        assertEquals(8, context.peek(), 0.0);
        context.put("val", 101);
        push.execute(context, Collections.singletonList("val"));
        assertEquals(101, context.peek(), 0.0);
    }
}