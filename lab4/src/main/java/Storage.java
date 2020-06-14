import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Storage <T> {
    private BlockingQueue<T> items;

    public Storage(int size) {
        items = new ArrayBlockingQueue<>(size);
    }

    T takeItem() throws InterruptedException {
        return items.take();
    }

    void putItem(T item) throws InterruptedException {
        items.put(item);
    }

    public int remainingCapacity() {
        return items.remainingCapacity();
    }
}
