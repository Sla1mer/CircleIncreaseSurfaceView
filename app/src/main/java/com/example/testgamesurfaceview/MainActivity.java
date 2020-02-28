package com.example.testgamesurfaceview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button circle;
    private Button flag;
    private Button rectangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circle = findViewById(R.id.circle);
        flag = findViewById(R.id.flag);
        rectangle = findViewById(R.id.rectangle);

        rectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rectangleIntent = new Intent(MainActivity.this, RectangleActivity.class);
                startActivity(rectangleIntent);
            }
        });

        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent flagIntent = new Intent(MainActivity.this, FlagActivity.class);
                startActivity(flagIntent);
            }
        });

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent circleIntent = new Intent(MainActivity.this, CircleActivity.class);
                startActivity(circleIntent);
            }
        });
    }
}
