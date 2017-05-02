package com.example.duska.axelerom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

/**
 * Created by Duska on 18.04.2017.
 */
public class MazeView extends SurfaceView {

    float x=300, y=500;
    Bitmap bitmapOwl, bitmapTileOfMaze, bitmapBackGround;
    Paint paint = new Paint();

    public static int mFieldX = 56; //73
    public static int mFieldY = 36; //59

    public static final int TOP=1;
    public static final int RIGHT=2;
    public static final int BOTTOM=3;
    public static final int LEFT=4;


    int mDirection = RIGHT;

    // Матрица - игровое поле
    private int mField[][] = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };

    //загрузим из ресурсов bitmap
    public MazeView(Context context) {
        super(context);

        bitmapOwl = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.owl);
        bitmapTileOfMaze = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.tree);
        bitmapBackGround = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.grass);

    }
    // Установка новых кординат телефона в пространстве
    // для того чтобы правильно нарисовать сову
    public void setXY(float x, float y) {
        this.x = x;
        this.y = y;
        moveOwl(x,y);
    }

    public int getDirection() {
        return mDirection;
    }

    public void setDirection(int direction) {
        this.mDirection = direction;
    }

    public void nextMove(float x, float y){


        switch (this.mDirection)
        {
            case TOP:
                moveOwl(x,y);
            case BOTTOM:
                moveOwl(x,y);
            case LEFT:
                moveOwl(x,y);
            case RIGHT:
                moveOwl(x,y);

        }
    }

    void moveOwl(float x, float y)
    {

            this.x=((-1) * x * 90 + 300);
            this.y=y * 90 + 500;

    }

    //отрисуем сову
   /* void drawOwl(Canvas c)
    {
      //  int width = c.getWidth();
      //  int height = c.getHeight();
       // Bitmap bitmapOwl_ = Bitmap.createScaledBitmap(bitmapOwl, width/6, height/9, true);
      //  paint.setColor(Color.BLUE);
        c.drawBitmap(bitmapOwl_, x, y, paint);
        paint.setAlpha(255);
        paint.setTextSize(50);
        String newX=Float.toString(x);
        String newY=Float.toString(y);
        c.drawText(newX, 200, 200, paint);
        c.drawText(newY, 300, 300, paint);
    }*/

    void drawMaze(Canvas c)
    {


        paint.setColor(Color.BLUE);
        int width = c.getWidth();
        int height = c.getHeight();
        int mX, mY;
        mX=width/36;
        mY=height/54;
        Bitmap bitmapTileOfMaze_ = Bitmap.createScaledBitmap(bitmapTileOfMaze,mX,mY,true);
        Bitmap bitmapOwl_ = Bitmap.createScaledBitmap(bitmapOwl, width/6, height/9, true);

        paint.setColor(Color.BLUE);
        for (int i=0; i<mFieldY; i++)
            for(int j=0; j<mFieldX; j++)
            {
                if(mField[i][j]==1) {
                    c.drawBitmap(bitmapTileOfMaze_, mX * i, mY * j, paint);
                }
            }

        //отрисовка движения совы по контуру
        if(y>1100 || (y>1100 && x<30)||(y>1100 && x>570))
        {
                float newY;
                float newX;
             if(y>1100 && x<30) {
                newY = 1100;
                newX = 30;
                c.drawBitmap(bitmapOwl_, newX, newY, paint);
            }
            else if(y>1100 && x>570) {
                newY = 1100;
                newX = 570;
                c.drawBitmap(bitmapOwl_, newX, newY, paint);
            }
            else if(y>1100 && (x<570&&x>30)) {
                newY = 1100;
                c.drawBitmap(bitmapOwl_, x, newY, paint);
            }

        }
        else if((y<30 && (x>30 && x<570)) || (y<30 && x<30) || (y<30 && x>570))
        {
            float newY;
            float newX;
           if (y<30 && (x>30 && x<570)) {
               newY = 30;
               c.drawBitmap(bitmapOwl_, x, newY, paint);
           }
            else if(y<30 && x<30) {
               newY = 30;
               newX = 30;
                c.drawBitmap(bitmapOwl_, newX, newY, paint);
            }
           else if(y<30 && x>570) {
               newY = 30;
               newX = 570;
               c.drawBitmap(bitmapOwl_, newX, newY, paint);
           }

        }
        else if((x<30 && (y>30 && y<1100)) || (x<30 && y>1100) || (x<30 && y<30))
        {
            float newX;
            float newY;

            if (x<30 && (y>30 && y<1100)) {
                newX = 30;
                c.drawBitmap(bitmapOwl_, newX, y, paint);
            }
            else if (x<30 && y>1100) {
                newX = 30;
                newY = 1100;
                c.drawBitmap(bitmapOwl_, newX, newY, paint);
            }
            else if (x<30 && y<30) {
                newX = 30;
                newY = 30;
                c.drawBitmap(bitmapOwl_, newX, newY, paint);
            }
        }
        else if((x>570 && (y>30 && y<1100))|| (x>570 && y>1100) || (x>570 && y<30))
        {
            float newX;
            float newY;
               if(x>570 && (y>30 && y<1100)) {
                   newX = 570;
                   c.drawBitmap(bitmapOwl_, newX, y, paint);
               }
            else if(x>570 && y>1100) {
                   newX = 570;
                   newY = 1100;
                c.drawBitmap(bitmapOwl_, newX, newY, paint);
            }
               else if(x>570 && y<30) {
                   newX = 570;
                   newY = 30;
                   c.drawBitmap(bitmapOwl_, newX, newY, paint);
               }

        }


        else
        {
            float newX, newY;
            if(y<160 && x>140 && x<250)
            {
                    newX = 200;
                    c.drawBitmap(bitmapOwl_, newX, y, paint);
            }
            else
                c.drawBitmap(bitmapOwl_,x,y,paint);
        }


        paint.setAlpha(255);
        paint.setTextSize(50);
        String newX=Float.toString(x);
        String newY=Float.toString(y);
        c.drawText(newX, 200, 200, paint);
        c.drawText(newY, 300, 300, paint);
    }

    void drawBackGround(Canvas c)
    {
        int width = c.getWidth();
        int height = c.getHeight();
        int mX, mY;
        mX=width/2;
        mY=height/2;
        Bitmap bitmapBackGround_ = Bitmap.createScaledBitmap(bitmapBackGround, mX,mY,true);
        paint.setColor(Color.BLUE);
        for (int i=0; i<mFieldX; i++)
            for(int j=0; j<mFieldY; j++)
            {
                c.drawBitmap(bitmapBackGround_, mX*i,mY*j,paint);
            }


    }
}
