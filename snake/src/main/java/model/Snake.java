package model;

import config.MyConfig;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private int length;
    private Direction direction;
    private List<CellOfSnake> snake = new LinkedList<>();

    public Snake(int x, int y) {
        snake.add(new CellOfSnake(x, y));
        length = 1;
        direction = Direction.RIGHT;
    }

    public void move(boolean flagGrow) {
        int x, y;

        x = getNextCoordX();
        y = getNextCoordY();

        this.add(x, y);
        if (flagGrow) {
            ++length;
        } else {
            snake.remove(0);
        }
    }


    private class CellOfSnake {
        private int x;
        private int y;

        private CellOfSnake(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    private void add(int x, int y) {
        snake.add(new CellOfSnake(x, y));
    }

    public void setDirection(Direction direction) {
        if (this.direction.equals(getReverseDirection(direction))) return;
        this.direction = direction;
    }

    private Direction getReverseDirection(Direction direction) {
        Direction reverseDirection = Direction.UP;
        switch (direction) {
            case DOWN:
                reverseDirection = Direction.UP;
                break;
            case UP:
                reverseDirection = Direction.DOWN;
                break;
            case RIGHT:
                reverseDirection = Direction.LEFT;
                break;
            case LEFT:
                reverseDirection = Direction.RIGHT;
                break;
        }
        return reverseDirection;
    }

    public int getNextCoordX() {
        int x = getXHead();
        switch (direction) {
            case UP:
                x = (x + 1) % MyConfig.MAP_HEIGHT;
                break;
            case DOWN:
                if (x == 0) {
                    x = MyConfig.MAP_HEIGHT;
                }
                --x;
                break;
            default:
                break;
        }
        return x;
    }

    public int getNextCoordY() {
        int y = getYHead();
        switch (direction) {
            case RIGHT:
                y = (y + 1) % MyConfig.MAP_WIDTH;
                break;
            case LEFT:
                if (y == 0) {
                    y = MyConfig.MAP_WIDTH;
                }
                --y;
                break;
            default:
                break;
        }
        return y;
}

    public int getXHead() {
        return snake.get(snake.size() - 1).x;
    }

    public int getYHead() {
        return snake.get(snake.size() - 1).y;
    }

    public int getXTail() {
        return snake.get(0).x;
    }

    public int getYTail() {
        return snake.get(0).y;
    }

    public int getLength() {
        return length;
    }
}
