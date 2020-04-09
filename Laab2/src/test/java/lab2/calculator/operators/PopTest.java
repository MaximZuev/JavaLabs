package lab2.calculator.operators;

import lab2.context.Context;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class PopTest {

    @Test
    public void execute() {
        Pop pop = new Pop();
        Context context = new Context();
        context.push(10);
        context.push(10);
        pop.execute(context, Collections.emptyList());
        assertEquals(1, context.getStackSize(), 0.0);
    }
}