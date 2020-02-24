package com.example.testgamesurfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyDraw extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private Canvas canvas;
    private int radius = 10;
    private boolean touch;

    public MyDraw(Context context, int screenX, int screenY) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
    }

    @Override
    public void run() {
        while (playing){
            draw();
            update();
        }
    }

    public void update(){
        if (touch == true){
            radius++;
        }else if (touch == false){
            System.out.println("Стоп");
        }
    }

    public void draw(){
        if (surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            paint.setColor(Color.WHITE);
            canvas.drawCircle(550, 1000,radius, paint);
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
                System.out.println("Есть касание");
                break;
        }
        return true;
    }
}
