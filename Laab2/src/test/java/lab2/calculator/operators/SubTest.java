package lab2.calculator.operators;

import lab2.context.Context;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class SubTest {

    @Test
    public void execute() {
        Sub sub = new Sub();
        Context context = new Context();
        context.push(12);
        context.push(100);
        sub.execute(context, Collections.emptyList());
        assertEquals(-88, context.peek(), 0.0);
    }
}