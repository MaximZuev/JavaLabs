package controller;

import model.Difficulty;
import model.Direction;
import model.Model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (model.getGameStatus()) {
            case RUNNING:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        model.setDirection(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_LEFT:
                        model.setDirection(Direction.LEFT);
                        break;
                    case KeyEvent.VK_UP:
                        model.setDirection(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        model.setDirection(Direction.DOWN);
                        break;
                    default:
                        break;
                }
                break;
            case SETTING:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1:
                        model.start(Difficulty.EASY_MODE);
                        break;
                    case KeyEvent.VK_2:
                        model.start(Difficulty.NORMAL_MODE);
                        break;
                    case KeyEvent.VK_3:
                        model.start(Difficulty.HARD_MODE);
                        break;
                    default:
                        break;
                }
                break;
            case ENDED:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_R:
                        model.reset();
                        break;
                }
                break;
        }
    }
}
