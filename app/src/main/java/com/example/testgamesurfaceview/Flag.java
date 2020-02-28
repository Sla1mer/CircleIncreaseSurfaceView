package com.example.testgamesurfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Flag extends SurfaceView implements Runnable {
    private Thread thread;
    private float y;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private boolean flagRun;
    private String lineColor_1="#FFFFFF";
    private String lineColor_2="#000080";
    private String lineColor_3="#FF0000";
    private String changeColor;
    private float rectY;
    private float rectX;

    public Flag (Context context,int screenX, int screenY) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        rectY = screenY/3;
        rectX = screenX;
    }

    @Override
    public void run() {

        while (flagRun) {
            Update();
            Draw();
        }
    }

    private void Update() {


    }

    private void Draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            paint.setColor(Color.parseColor(lineColor_1));
            canvas.drawRect(0, 0, rectX,rectY, paint);
            paint.setColor(Color.parseColor(lineColor_2));
            canvas.drawRect(0, rectY, rectX,rectY*2, paint);
            paint.setColor(Color.parseColor(lineColor_3));
            canvas.drawRect(0, rectY*2, rectX,rectY*3, paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_DOWN:
                y = event.getY();
                if (y>=0&&y<=rectY){
                    changeColor = lineColor_1;
                    lineColor_1 = lineColor_2;
                    lineColor_2 = changeColor;
                }
                if (y>rectY&&y<=rectY*2){
                    changeColor = lineColor_2;
                    lineColor_2 = lineColor_3;
                    lineColor_3 = changeColor;
                }
                if (y>rectY*2&&y<=rectY*3){
                    changeColor = lineColor_3;
                    lineColor_3 = lineColor_1;
                    lineColor_1 = changeColor;
                }
                break;
        }
        return true;
    }

    public void pause() {
        flagRun=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        flagRun=true;
        thread = new Thread(this);
        thread.start();
    }
}