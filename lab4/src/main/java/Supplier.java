import car.Detail;
import org.apache.log4j.Logger;

public class Supplier<T> extends Thread {
    private Storage<T> storage;
    private Class<T> detailCreator;

    private static final Logger supplierLog = Logger.getLogger(Supplier.class);

    public Supplier(Storage<T> storage, Class<T> detailCreator) {
        this.storage = storage;
        this.detailCreator = detailCreator;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                T detail = detailCreator.getDeclaredConstructor().newInstance();
                storage.putItem(detail);
                supplierLog.info("The supplier put the " + detail.toString() + " in the storage.");
                sleep(1000);
            } catch (InterruptedException exception) {
                interrupt();
            } catch (Exception e) {
                break;
            }
        }
    }
}
