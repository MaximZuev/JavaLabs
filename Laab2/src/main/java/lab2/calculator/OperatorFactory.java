package lab2.calculator;

import lab2.calculator.operators.Operator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OperatorFactory {
    private volatile static OperatorFactory factory = null;
    private Properties properties = new Properties();
    private static final Logger operatorFactoryLog = Logger.getLogger(OperatorFactory.class);

    private OperatorFactory() throws IOException {
        operatorFactoryLog.info("Operator factory was created.");

        InputStream inputStream = OperatorFactory.class.getClassLoader().getResourceAsStream("operators.properties");

        if (inputStream != null) {
            properties.load(inputStream);
            inputStream.close();
        } else {
            operatorFactoryLog.error("Operator properties were not found.");
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
            operatorFactoryLog.info("Operator " + name + " was created.");
        } catch (Exception e) {
            return null;
        }

        return operator;
    }
}