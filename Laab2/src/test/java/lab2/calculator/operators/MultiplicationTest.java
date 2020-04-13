package lab2.calculator.operators;

import lab2.context.Context;
import lab2.exeptions.InvalidArgsForOperatorsExceptions;
import lab2.exeptions.NotEnoughElementsInTheStackExceptions;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class MultiplicationTest {

    @Test
    public void execute() throws NotEnoughElementsInTheStackExceptions, InvalidArgsForOperatorsExceptions {
        Multiplication multiplication = new Multiplication();
        Context context = new Context();
        context.push(-1.9);
        context.push(10);
        multiplication.execute(context, Collections.emptyList());
        assertEquals(-19, context.peek(), 0.0);
    }
}