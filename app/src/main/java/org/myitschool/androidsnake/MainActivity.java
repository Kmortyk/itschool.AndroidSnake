package org.myitschool.androidsnake;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Looper;
import android.widget.TextView;

import org.myitschool.androidsnake.game.GameMap;

public class MainActivity extends AppCompatActivity {

    private GameMap gameMap;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // найти view
        textView = findViewById(R.id.textView);
        textView.setTypeface(Typeface.MONOSPACE);
        // инициализация карты
        gameMap = new GameMap(10, 10);
        // запустить основной цикл игры в отдельном потоке
        new Thread(this::gameLoop).start();
    }

    /**
     * WARNING !!! Запускать в отдельном потоке !!!
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
            // gameMap.update();
        }

    }

}
