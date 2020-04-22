package org.myitschool.androidsnake.game;

import org.myitschool.androidsnake.Constants;

import java.util.ArrayList;
import java.util.List;

class SnakeSegment {
    int dir = Constants.DIR_BOTTOM;
    int x, y;

    SnakeSegment(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void update() {
        switch (dir) {
            case Constants.DIR_TOP: y -= Constants.STEP;
                break;
            case Constants.DIR_RIGHT: x += Constants.STEP;
                break;
            case Constants.DIR_BOTTOM: y += Constants.STEP;
                break;
            case Constants.DIR_LEFT: x -= Constants.STEP;
                break;
        }
    }
}

class Snake {

    private List<SnakeSegment> segments = new ArrayList<>();

    Snake() {
        segments.add(new SnakeSegment(1, 1));
    }

    void update() {
        for(SnakeSegment s: segments)
            s.update();
    }

    void setHeadDirection(int dir) {
        if(segments.size() == 0)
            return;
        segments.get(segments.size() - 1).dir = dir;
    }

    List<SnakeSegment> getSegments() {
        return segments;
    }
}
