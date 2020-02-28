package com.example.testgamesurfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Rectangle extends SurfaceView implements Runnable {


    volatile boolean playing;
    private Thread gameThread = null;
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private Canvas canvas;
    private float x;
    private float changeX;
    private float rectY;
    private float rectX;

    private boolean touch;

    public Rectangle(Context context, int screenX, int screenY) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        rectY = screenY;
        changeX = screenX/2;
        rectX = screenX;
    }

    @Override
    public void run() {
        while (playing){
            draw();
            update();
        }
    }

    public void update(){
        if (x < changeX && touch == true){
            changeX--;
        }else if (x > changeX && touch == true){
            changeX++;
        }
    }

    public void draw(){
        if (surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            paint.setColor(Color.RED);
            canvas.drawRect(0, 0, changeX,rectY, paint);
            paint.setColor(Color.BLUE);
            canvas.drawRect(changeX, 0, rectX,rectY, paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause(){
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_UP:
                touch = false;
                System.out.println("Нет касания");
                break;
            case MotionEvent.ACTION_DOWN:
                touch = true;
                x = event.getX();
                System.out.println("Есть касание");
                break;
        }
        return true;
    }
}