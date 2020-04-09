package lab2.calculator.operators;

import lab2.context.Context;

import java.util.EmptyStackException;
import java.util.List;

public interface Operator {
    void execute(Context context, List<String> args) throws IllegalArgumentException, EmptyStackException;
}
