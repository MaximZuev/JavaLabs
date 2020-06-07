package view;

import config.MyConfig;
import model.Event;
import model.Map;
import model.Score;
import observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JFrame implements Observer {
    private JPanel jPanel = new JPanel();
    private Font font = new Font("Monospaced", Font.PLAIN, 40);
    private Map map;
    private Score score;
    private JComponent gameField;

    public View(Map map, Score score) {
        super("Snake");
        this.map = map;
        this.score = score;

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - MyConfig.FRAME_WIDTH / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - MyConfig.FRAME_HEIGHT / 2,
                MyConfig.FRAME_WIDTH,
                MyConfig.FRAME_HEIGHT
        );
        add(jPanel);

        setStartFrame();
    }

    public void setStartFrame() {
        jPanel.removeAll();
        jPanel.setLayout(new GridLayout(4, 1));
        jPanel.add(textLabel("Choose mode"));
        jPanel.add(textLabel("1 - Easy mode"));
        jPanel.add(textLabel("2 - Normal mode"));
        jPanel.add(textLabel("3 - Hard mode"));
        jPanel.revalidate();
    }

    public void setGameFrame() {
        jPanel.removeAll();
        jPanel.setLayout(new BorderLayout());

        gameField = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                Image snakeImg = new ImageIcon(getClass().getResource("/snake.png")).getImage();
                Image foodImg = new ImageIcon(getClass().getResource("/food.png")).getImage();
                Image nothingImg = new ImageIcon(getClass().getResource("/nothing.png")).getImage();
                for (int i = 0; i < MyConfig.MAP_HEIGHT; ++i) {
                    for (int j = 0; j < MyConfig.MAP_WIDTH; ++j) {
                        switch(map.getCell(i, j)){
                            case NOTHING:
                                g2.drawImage(nothingImg, j * 25, (MyConfig.MAP_HEIGHT - i - 1) * 25, null);
                                break;
                            case SNAKE:
                                g2.drawImage(snakeImg, j * 25, (MyConfig.MAP_HEIGHT - i - 1) * 25, null);
                                break;
                            case FOOD:
                                g2.drawImage(foodImg, j * 25, (MyConfig.MAP_HEIGHT - i - 1) * 25, null);
                                break;
                        }
                    }
                }
            }
        };

        jPanel.add(gameField);
        gameField.revalidate();
    }

    public void setEndFrame() {
        ArrayList<Integer> info = score.getInfo();

        jPanel.removeAll();
        jPanel.setLayout(new GridLayout(8, 1));
        jPanel.add(textLabel("Game over"));
        jPanel.add(textLabel("Total score"));
        jPanel.add(textLabel(score.getLastTry().toString()));
        jPanel.add(textLabel("Best score"));
        for (Integer i = 1; i <= 3; ++i) {
            jPanel.add(textLabel(i.toString() + " " + info.get(3 - i).toString()));
        }
        jPanel.add(textLabel("R to restart"));
        jPanel.revalidate();
    }

    private JLabel textLabel(String text) {
        JLabel textLabel = new JLabel();
        textLabel.setFont(font);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText(text);
        return textLabel;
    }


    @Override
    public void update(Event event) {
        switch (event) {
            case MOVE:
                gameField.repaint();
                break;
            case START:
                setGameFrame();
                break;
            case END:
                setEndFrame();
                break;
            case RESET:
                setStartFrame();
                break;
        }
    }
}
