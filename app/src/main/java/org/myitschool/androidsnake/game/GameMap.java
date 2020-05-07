package org.myitschool.androidsnake.game;

import org.myitschool.androidsnake.Constants;

import java.util.ArrayList;
import java.util.List;

public class GameMap {

    private int width;
    private int height;

    private char[][] map;
    private char[][] buffer;
    private final Snake snake;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;

        map = new char[height][width];
        buffer = new char[height][width];
        snake = new Snake();

        createMap();
        addBonus();
    }

    public void update() {
        snake.update(this);
    }

    private void createMap() {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                if(y == 0 || x == 0 || y == height - 1 || x == width - 1)
                    map[y][x] = '#'; // стенки лабиринта
                else
                    map[y][x] = ' '; // пустые клетки
            }
    }

    public void onSwipeTop() {
        snake.setHeadDirection(Constants.DIR_TOP);
    }

    public void onSwipeRight() {
        snake.setHeadDirection(Constants.DIR_RIGHT);
    }
    public void onSwipeLeft() {
        snake.setHeadDirection(Constants.DIR_LEFT);
    }
    public void onSwipeBottom() {
        snake.setHeadDirection(Constants.DIR_BOTTOM);
    }

    public void addBonus() {
        class Empty {
            int x, y;

            public Empty(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        List<Empty> emptys = new ArrayList<Empty>();

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                if(map[y][x] == ' ') {
                    emptys.add(new Empty(x, y));
                }

        int r = (int) (Math.random()*emptys.size());
        Empty e = emptys.get(r);
        map[e.y][e.x] = '*';
    }

    public boolean inBoundsX(int x) {
        return x >= 1 && x < width - 1;
    }

    public boolean inBoundsY(int y) {
        return y >= 1 && y < height - 1;
    }

    public char at(int x, int y) {
        return map[y][x];
    }

    public char[][] getCharMap() {
        return map;
    }

    public Snake getSnake() {
        return snake;
    }
}
