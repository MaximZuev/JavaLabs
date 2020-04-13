package lab2.calculator.operators;

import lab2.context.Context;
import lab2.exeptions.InvalidArgsForOperatorsExceptions;
import lab2.exeptions.NotEnoughElementsInTheStackExceptions;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class SumTest {

    @Test
    public void execute() throws NotEnoughElementsInTheStackExceptions, InvalidArgsForOperatorsExceptions {
        Sum sum = new Sum();
        Context context = new Context();
        context.push(-12);
        context.push(12);
        sum.execute(context, Collections.emptyList());
        assertEquals(0, context.peek(), 0.0);
    }
}