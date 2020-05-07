package org.myitschool.androidsnake.game;

import org.myitschool.androidsnake.Constants;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    public static class Segment {
        public int x;
        public int y;

        Segment(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private List<Segment> segments = new ArrayList<>();
    private int dir = Constants.DIR_BOTTOM;
    private Segment head;

    Snake() {
        head = new Segment(1, 1);
        segments.add(head);
    }

    void update(GameMap map) {
        synchronized (this) {
            Segment next = new Segment(head.x, head.y);

            switch (dir) {
                case Constants.DIR_TOP:
                    next.y -= Constants.STEP;
                    break;
                case Constants.DIR_RIGHT:
                    next.x += Constants.STEP;
                    break;
                case Constants.DIR_BOTTOM:
                    next.y += Constants.STEP;
                    break;
                case Constants.DIR_LEFT:
                    next.x -= Constants.STEP;
                    break;
            }

            switch (map.at(next.x, next.y)) {
                case '*':
                    map.addBonus();
                    break;
                case ' ':
                    segments.remove(segments.size() - 1);
                    break;
            }

            head = next;
            segments.add(0, next);
        }
    }

    void setHeadDirection(int dir) {
        this.dir = dir;
    }

    public List<Segment> getSegments() {
        return segments;
    }
}
