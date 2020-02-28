package com.example.testgamesurfaceview;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import androidx.appcompat.app.AppCompatActivity;

public class FlagActivity extends AppCompatActivity {

    private Flag flagView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        flagView = new Flag(this, size.x, size.y);
        setContentView(flagView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        flagView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        flagView.resume();
    }
}