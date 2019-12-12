package org.myitschool.androidsnake.game;

import org.myitschool.androidsnake.Constants;

public class Snake {

    private Segment[] segments = new Segment[100];

    public class Segment {
        int x, y;
        int dir = Constants.DIR_DOWN;

        public Segment(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }



    public Snake() {
        segments[0] = new Segment(1, 1);
    }

    public Segment[] getSegments() {
        return segments;
    }

}
