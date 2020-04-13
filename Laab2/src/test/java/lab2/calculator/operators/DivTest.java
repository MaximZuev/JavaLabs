package lab2.calculator.operators;

import lab2.context.Context;
import lab2.exeptions.InvalidArgsForOperatorsExceptions;
import lab2.exeptions.NotEnoughElementsInTheStackExceptions;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class DivTest {

    @Test
    public void execute() throws NotEnoughElementsInTheStackExceptions, InvalidArgsForOperatorsExceptions {
        Div division = new Div();
        Context context = new Context();
        context.push(-1);
        context.push(10);
        division.execute(context, Collections.emptyList());
        assertEquals(-0.1, context.peek(), 0.0);
    }
}