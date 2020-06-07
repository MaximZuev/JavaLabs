package observer;

import model.Event;

public interface Observer {
    public void update(Event event);
}
