package org.myitschool.androidsnake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import org.myitschool.androidsnake.controller.OnSwipeTouchListener;
import org.myitschool.androidsnake.game.GameMap;
import org.myitschool.androidsnake.view.GameView;

public class MainActivity extends AppCompatActivity {

    @Override @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameView gameView = findViewById(R.id.gameView);
        GameMap map = new GameMap(20, 20);
        gameView.setMap(map);

        findViewById(R.id.btn_up).setOnClickListener(v -> map.onSwipeTop());
        findViewById(R.id.btn_right).setOnClickListener(v -> map.onSwipeRight());
        findViewById(R.id.btn_left).setOnClickListener(v -> map.onSwipeLeft());
        findViewById(R.id.btn_down).setOnClickListener(v -> map.onSwipeBottom());
    }

}
