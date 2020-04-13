package lab2.calculator.operators;

import lab2.context.Context;
import lab2.exeptions.InvalidArgsForOperatorsExceptions;
import lab2.exeptions.NotEnoughElementsInTheStackExceptions;

import java.util.List;

public interface Operator {
    void execute(Context context, List<String> args) throws InvalidArgsForOperatorsExceptions, NotEnoughElementsInTheStackExceptions;
}
