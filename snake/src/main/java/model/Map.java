package model;

import config.MyConfig;

public class Map {
    private Cell[][] map = new Cell[MyConfig.MAP_HEIGHT][MyConfig.MAP_WIDTH];

    public Map() {
        for (int i = 0; i < MyConfig.MAP_HEIGHT; ++i) {
            for (int j = 0; j < MyConfig.MAP_WIDTH; ++j) {
                map[i][j] = Cell.NOTHING;
            }
        }
    }

    public void setCell(int x, int y, Cell status) {
        map[x][y] = status;
    }

    public void putFood() {
        int x, y;

        do {
            x = (int) (Math.random() * MyConfig.MAP_HEIGHT);
            y = (int) (Math.random() * MyConfig.MAP_WIDTH);
        } while (map[x][y] != Cell.NOTHING);

        map[x][y] = Cell.FOOD;
    }

    public Cell getCell(int x, int y) {
        return map[x][y];
    }

    public void clear() {
        for (int i = 0; i < MyConfig.MAP_HEIGHT; ++i) {
            for (int j = 0; j < MyConfig.MAP_WIDTH; ++j) {
                map[i][j] = Cell.NOTHING;
            }
        }
    }

}
