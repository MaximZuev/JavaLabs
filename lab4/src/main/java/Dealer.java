import car.Car;
import org.apache.log4j.Logger;

public class Dealer extends Thread {
    public final Storage<Car> carStorage;

    private static final Logger dealerLog = Logger.getLogger(Dealer.class);

    public Dealer(Storage<Car> carStorage) {
        this.carStorage = carStorage;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Car car;
                synchronized (carStorage) {
                    car = carStorage.takeItem();
                    carStorage.notifyAll();
                }
                dealerLog.info("The dealer sold the " + car.toString() + " from the storage.");
                sleep(1000);
            } catch (InterruptedException exception) {
                dealerLog.error("The dealer could not sold the car from the storage.");
                interrupt();
            }
        }
    }
}
