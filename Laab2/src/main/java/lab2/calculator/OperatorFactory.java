package lab2.calculator;

import lab2.calculator.operators.Operator;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class OperatorFactory {
    private volatile static OperatorFactory factory = null;
    private Properties properties = new Properties();

    private OperatorFactory() throws IOException {
        Logger.getLogger(this.getClass()).info("Operator factory was created.");
        try {
            properties.load(new FileInputStream("src/main/resources/operators.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(this.getClass()).error("Operator properties were not found.");
        }
    }

    public static OperatorFactory getInstance() throws IOException {
        if (factory == null) {
            synchronized (OperatorFactory.class) {
                if (factory == null) {
                    factory = new OperatorFactory();
                }
            }
        }

        return factory;
    }

    public Operator createOperator(String name) {
        Operator operator;

        try {
            operator = (Operator) Class.forName(properties.getProperty(name)).getDeclaredConstructor().newInstance();
            Logger.getLogger(this.getClass()).info("Operator " + name + " was created.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass()).error("Operator " + name + " was not found.");
            return null;
        }

        return operator;
    }
}