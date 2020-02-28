package com.example.testgamesurfaceview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class RectangleActivity extends AppCompatActivity {

    private Rectangle rectangleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangle);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        rectangleView = new Rectangle(this, size.x, size.y);
        setContentView(rectangleView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        rectangleView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        rectangleView.resume();
    }
}
