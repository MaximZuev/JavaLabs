package car;

import java.util.UUID;

public class Car {
    private final UUID id;
    private Engine engine;
    private Body body;
    private Accessory accessory;

    public Car(Engine engine, Body body, Accessory accessory) {
        this.engine = engine;
        this.body = body;
        this.accessory = accessory;
        id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Car: " + id + engine.toString() + body.toString() + accessory.toString();
    }
}
