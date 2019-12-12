package org.myitschool.androidsnake.game;

public class GameMap {

    private int width;
    private int height;

    private char[][] map;
    private char[][] buffer;
    private Snake snake;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;

        map = new char[height][width];
        buffer = new char[height][width];
        snake = new Snake();

        createMap();
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        // обновляем буффер
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                buffer[y][x] = map[y][x];
        // пишем сегменты
        for(Snake.Segment seg: snake.getSegments())
            if(seg != null)
                buffer[seg.y][seg.x] = '*';
        // строим строку
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++)
                sb.append(buffer[y][x]);
            sb.append("\n");
        }
        sb.append("\n");
        // устанавливаем как текст
        return sb.toString();
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

}
