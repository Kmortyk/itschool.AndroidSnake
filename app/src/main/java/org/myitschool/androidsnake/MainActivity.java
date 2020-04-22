package org.myitschool.androidsnake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Looper;
import android.text.Layout;
import android.widget.TextView;
import android.widget.Toast;

import org.myitschool.androidsnake.controller.OnSwipeTouchListener;
import org.myitschool.androidsnake.game.GameMap;

public class MainActivity extends AppCompatActivity {

    private GameMap gameMap;
    private TextView textView;
    private ConstraintLayout gameLayout;

    @Override @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find views
        textView = findViewById(R.id.text_view);
        gameLayout = findViewById(R.id.game_layout);
        textView.setTypeface(Typeface.MONOSPACE);

        // set controllers
        gameLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() { gameMap.onSwipeTop(); }
            public void onSwipeRight() { gameMap.onSwipeRight(); }
            public void onSwipeLeft() { gameMap.onSwipeLeft(); }
            public void onSwipeBottom() { gameMap.onSwipeBottom(); }
        });

        // init game map and main loop
        gameMap = new GameMap(20, 12);
        new Thread(this::gameLoop).start();
    }

    /**
     * Run only in the separate thread
     */
    private void gameLoop() {
        if(Looper.myLooper() == Looper.getMainLooper())
                throw new RuntimeException("Can't start game loop in the main thread");

        while (true) {
            // текст устанавливаем в главном потоке
            runOnUiThread(() -> {
                textView.setText(gameMap.getText());
            });
            // усыпить поток на 1 секунду
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameMap.update();
        }

    }

}
