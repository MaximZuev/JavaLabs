package lab2.calculator.operators;

import lab2.context.Context;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class SqrtTest {

    @Test
    public void execute() {
        Sqrt sqrt = new Sqrt();
        Context context = new Context();
        context.push(100);
        sqrt.execute(context, Collections.emptyList());
        assertEquals(10, context.peek(), 0.0);
    }
}