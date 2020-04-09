package lab2.context;

import java.util.HashMap;
import java.util.Stack;

public class Context {
    private Stack<Double> stack;
    private HashMap<String, Double> definitions;

    public Context(){
        stack = new Stack<>();
        definitions = new HashMap<>();
    }

    public double pop() {
        return stack.pop();
    }

    public void push(double value) {
        stack.push(value);
    }

    public int getStackSize() {
        return stack.size();
    }

    public double peek() {
        return stack.peek();
    }

    public void put(String key, double value) {
        definitions.put(key, value);
    }

    public double get(String key) {
        return definitions.get(key);
    }
}