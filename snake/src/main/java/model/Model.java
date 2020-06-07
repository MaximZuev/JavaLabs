package model;

import observer.Observable;
import observer.Observer;

import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Model implements Observable{
    private Map map;
    private Snake snake;
    private Timer timer;
    private Score score;
    private GameStatus gameStatus;
    private Difficulty difficulty;
    private Direction direction;
    private final ArrayList<Observer> observers;

    public Model() {
        try {
            loadScore();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        observers = new ArrayList<>();
        map = new Map();
        gameStatus = GameStatus.SETTING;
        difficulty = Difficulty.EASY_MODE;
        direction = Direction.RIGHT;
    }

    public void reset() {
        map.clear();
        gameStatus = GameStatus.SETTING;
        difficulty = Difficulty.EASY_MODE;
        direction = Direction.RIGHT;
        notifyObservers(Event.RESET);
    }

    public void start(Difficulty difficulty) {
        timer = new Timer();
        snake = new Snake(0, 0);

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                step();
                notifyObservers(Event.MOVE);
                if (isEnded()) {
                    score.addScore(getPoints());
                    try {
                        saveScore();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    notifyObservers(Event.END);
                    timer.cancel();
                }
            }
        };

        gameStatus = GameStatus.RUNNING;
        this.difficulty = difficulty;

        map.setCell(0, 0, Cell.SNAKE);
        map.putFood();

        notifyObservers(Event.START);
        timer.schedule(timerTask, 500, 500 - 200 * difficulty.ordinal());
    }

    private void step() {
        int xNext, yNext, xTail, yTail;
        Cell status;

        snake.setDirection(direction);

        xNext = snake.getNextCoordX();
        yNext = snake.getNextCoordY();
        status = map.getCell(xNext, yNext);

        xTail = snake.getXTail();
        yTail = snake.getYTail();
        map.setCell(xTail, yTail, Cell.NOTHING);
        switch (status) {
            case NOTHING:
                snake.move(false);
                map.setCell(xNext, yNext, Cell.SNAKE);
                break;
            case SNAKE:
                gameStatus = GameStatus.ENDED;
                break;
            case FOOD:
                snake.move(true);
                map.setCell(xTail, yTail, Cell.SNAKE);
                map.setCell(xNext, yNext, Cell.SNAKE);
                map.putFood();
                break;
        }

    }

    private int getPoints() {
        return (difficulty.ordinal() + 1) * snake.getLength() * 10;
    }

    private void loadScore() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir"), "src/main/resources/score.ser"));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        score = (Score) objectInputStream.readObject();
    }

    private void saveScore() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(new File(System.getProperty("user.dir"), "src/main/resources/score.ser"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(score);
        objectOutputStream.close();
    }

    private boolean isEnded() {
        return (gameStatus == GameStatus.ENDED);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Map getMap() {
        return map;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Event event) {
        for (Observer obs : observers) obs.update(event);
    }
}
