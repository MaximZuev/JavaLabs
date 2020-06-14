import car.Accessory;
import car.Body;
import car.Car;
import car.Engine;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Factory {
    private Storage<Engine> engineStorage;
    private Storage<Body> bodyStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Car> carStorage;

    private StorageController<Car> storageController;

    private Supplier<Body> bodySupplier;
    private Supplier<Engine> engineSupplier;
    private ArrayList<Supplier<Accessory>> accessorySuppliers;

    private ArrayList<Dealer> dealers;

    private ExecutorService workers;

    private static final Logger factoryLog = Logger.getLogger(Factory.class);

    public Factory() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = Factory.class.getClassLoader().getResourceAsStream("factory.properties");

        if (inputStream != null) {
            properties.load(inputStream);
            inputStream.close();
            factoryLog.info("Operator properties were uploaded.");
        } else {
            factoryLog.error("Operator properties were not found.");
        }

        engineStorage = new Storage<>(Integer.parseInt(properties.getProperty("engineStorageSize")));
        factoryLog.info("An engine storage of size " + Integer.parseInt(properties.getProperty("engineStorageSize")) + " was created.");
        bodyStorage = new Storage<>(Integer.parseInt(properties.getProperty("bodyStorageSize")));
        factoryLog.info("A body storage of size " + Integer.parseInt(properties.getProperty("bodyStorageSize")) + " was created.");
        accessoryStorage = new Storage<>(Integer.parseInt(properties.getProperty("accessoryStorageSize")));
        factoryLog.info("An accessory storage of size " + Integer.parseInt(properties.getProperty("accessoryStorageSize")) + " was created.");
        carStorage = new Storage<>(Integer.parseInt(properties.getProperty("carStorageSize")));
        factoryLog.info("A car storage of size " + Integer.parseInt(properties.getProperty("carStorageSize")) + " was created.");

        bodySupplier = new Supplier<>(bodyStorage, Body.class);
        factoryLog.info("The body supplier was created.");
        engineSupplier = new Supplier<>(engineStorage, Engine.class);
        factoryLog.info("The engine supplier was created.");
        accessorySuppliers = new ArrayList<>();
        dealers = new ArrayList<>();

        for (int i = 0; i < Integer.parseInt(properties.getProperty("accessorySuppliers")); ++i) {
            accessorySuppliers.add(new Supplier<>(accessoryStorage, Accessory.class));
        }

        factoryLog.info(Integer.parseInt(properties.getProperty("accessorySuppliers")) + " accessory suppliers were created.");

        for (int i = 0; i < Integer.parseInt(properties.getProperty("dealers")); i++) {
            dealers.add(new Dealer(carStorage));
        }

        factoryLog.info(Integer.parseInt(properties.getProperty("dealers")) + " dealers were created.");

        workers = Executors.newFixedThreadPool(Integer.parseInt(properties.getProperty("workers")));

        factoryLog.info(Integer.parseInt(properties.getProperty("workers")) + " workers were created.");
    }

    private synchronized Car buildCar() throws InterruptedException {
        Body body = bodyStorage.takeItem();
        Engine engine = engineStorage.takeItem();
        Accessory accessory = accessoryStorage.takeItem();

        return new Car(engine, body, accessory);
    }

    public void start() {
        storageController = new StorageController<>(carStorage, this);
        storageController.start();
        bodySupplier.start();
        engineSupplier.start();
        for (Supplier<Accessory> i : accessorySuppliers) {
            i.start();
        }
        for (Dealer i : dealers) {
            i.start();
        }
        factoryLog.info("The factory started working.");
    }

    public void work(int count) {
        for (int i = 0; i < count; i++) {
            workers.execute(() -> {
                try {
                    Car car = buildCar();
                    carStorage.putItem(car);
                    factoryLog.info("The worker build and put the " + car.toString() + " in the car storage.");
                } catch (InterruptedException e) {
                    factoryLog.error("The worker could not build the car.");
                    Thread.currentThread().interrupt();
                }
            });
        }
    }
}
