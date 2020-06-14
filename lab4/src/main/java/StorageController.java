public class StorageController<T> extends Thread {
    private final Factory factory;
    private final Storage<T> storage;

    StorageController(Storage<T> storage, Factory factory) {
        this.storage = storage;
        this.factory = factory;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                int remainingCapacity = storage.remainingCapacity();
                factory.work(remainingCapacity);
                if(remainingCapacity == 0) {
                    synchronized (storage) {
                        storage.wait();
                    }
                }
            } catch (InterruptedException exception) {
                interrupt();
            }
        }
    }
}
