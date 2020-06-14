package car;

import java.util.UUID;

public class Detail {
    private final UUID id;

    public Detail() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
