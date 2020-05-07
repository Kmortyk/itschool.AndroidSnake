package org.myitschool.androidsnake.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import org.myitschool.androidsnake.Constants;
import org.myitschool.androidsnake.game.GameMap;
import org.myitschool.androidsnake.game.Snake;

import java.util.List;

public class GameView extends View {

    private static final int MAX_SLEEP_FRAMES = 20;

    private Paint emptyPaint;
    private Paint snakeSegmentPaint;
    private Paint bonusPaint;
    private Paint wallPaint;

    private GameMap map;

    private int sleepFrames = MAX_SLEEP_FRAMES;

    public GameView(Context context) { super(context); initPaints(); }
    public GameView(Context context, @Nullable AttributeSet attrs) { super(context, attrs); initPaints(); }
    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); initPaints(); }

    private void initPaints() {
        emptyPaint = new Paint();
        emptyPaint.setStyle(Paint.Style.FILL);
        emptyPaint.setColor(Color.GRAY);

        snakeSegmentPaint = new Paint();
        snakeSegmentPaint.setStyle(Paint.Style.FILL);
        snakeSegmentPaint.setColor(Color.GREEN);

        bonusPaint = new Paint();
        bonusPaint.setStyle(Paint.Style.FILL);
        bonusPaint.setColor(Color.RED);

        wallPaint = new Paint();
        wallPaint.setStyle(Paint.Style.FILL);
        wallPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(++sleepFrames >= MAX_SLEEP_FRAMES) {
            map.update();
            sleepFrames = 0;
        }

        drawMap(canvas);
        drawSnake(canvas);
        invalidate();
    }

    private void drawMap(Canvas canvas) {
        char[][] charMap = map.getCharMap();

        for (int i = 0; i < charMap.length; i++) {
            for (int j = 0; j < charMap[0].length; j++) {
                float x = Constants.CANVAS_CELL_SIZE * j + Constants.CANVAS_OFFSET;
                float y = Constants.CANVAS_CELL_SIZE * i + Constants.CANVAS_OFFSET;

                canvas.drawRect(x, y, x + Constants.CANVAS_CELL_SIZE, y + Constants.CANVAS_CELL_SIZE, emptyPaint);
                switch (charMap[i][j]) {
                    case '*':
                        drawCircle(canvas, bonusPaint, x, y);
                        break;
                    case '#':
                        canvas.drawRect(x, y, x + Constants.CANVAS_CELL_SIZE, y + Constants.CANVAS_CELL_SIZE, wallPaint);
                        break;
                }
            }
        }
    }

    private void drawSnake(Canvas canvas) {

        List<Snake.Segment> segs = map.getSnake().getSegments();
        for (int i = 0; i < segs.size(); i++) {
            float x = segs.get(i).x * Constants.CANVAS_CELL_SIZE + Constants.CANVAS_OFFSET;
            float y = segs.get(i).y * Constants.CANVAS_CELL_SIZE + Constants.CANVAS_OFFSET;
            drawCircle(canvas, snakeSegmentPaint, x, y);
        }
    }

    public void drawCircle(Canvas canvas, Paint paint, float x, float y) {
        float half = Constants.CANVAS_CELL_SIZE*0.5f;
        canvas.drawCircle(x + half, y + half, half, paint);
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
}
