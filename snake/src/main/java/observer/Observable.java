package observer;

import model.Event;

public interface Observable {
    public void addObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers(Event event);
}
