package lab2.calculator.operators;

import lab2.context.Context;
import lab2.exeptions.InvalidArgsForOperatorsExceptions;
import lab2.exeptions.NotEnoughElementsInTheStackExceptions;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class SqrtTest {

    @Test
    public void execute() throws NotEnoughElementsInTheStackExceptions, InvalidArgsForOperatorsExceptions {
        Sqrt sqrt = new Sqrt();
        Context context = new Context();
        context.push(100);
        sqrt.execute(context, Collections.emptyList());
        assertEquals(10, context.peek(), 0.0);
    }
}