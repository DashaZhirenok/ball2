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

    //0 - пустое пространство
    //1 - стены
    //2 - подставные стены
    //3,4,5 - черные дыры
    //6 - ключик
    float dx=0, dy=0;
    float myX=50, myY=1150;
    float newX=0,newY=0;
    float speed = 0.8f;
    Bitmap bitmapOwl, bitmapTileOfMaze, bitmapBackGround, bitmapHole, bitmapTileOfMaze2, bitmapKey;
    Paint paint = new Paint();
    Boolean collisionWithKey=false;
    double startTime=0;
    double lastTime=0;

    public static int mFieldX = 56; //73
    public static int mFieldY = 36; //59
    // Матрица - игровое поле
    private int mField[][] = {

            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,1,0,3,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1},
            {1,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };

    //загрузим из ресурсов bitmap
    public MazeView(Context context) {
        super(context);

        bitmapOwl = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.owl);
        bitmapTileOfMaze = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.beem2);
        bitmapTileOfMaze2 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.beem23);
        bitmapBackGround = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.grass);
        bitmapHole = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.hole);
        bitmapKey = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.key);

    }

    // Установка новых кординат телефона в пространстве
    // для того чтобы правильно нарисовать сову
    public void setXY(float dx, float dy) {
        this.dx += -speed * dx;
        this.dy += speed * dy;

    }


    void Update()
    {
        newX=myX+dx;
        newY=myY+dy;

        boolean collisionResultX = false;
        boolean collisionResultY = false;
        for (int i=0; i<mFieldY; i++)
            for(int j=0; j<mFieldX; j++)
            {
                if(mField[i][j]==1) {
                    if(isCollisionX(newX, i))
                    if(isCollisionY(newY, j)) {
                        collisionResultY = true;
                        collisionResultX = true;
                    }
                }
                else if(mField[i][j]==2)
                {
                    if(isCollisionX(newX, i))
                        if(isCollisionY(newY, j)) {
                            mField[i][j]=1;
                        }
                }
                else if(mField[i][j]==6)
                {
                    if(isCollisionX(newX, i))
                        if(isCollisionY(newY, j)) {
                            myX=500;
                            myY=1150;
                            mField[i][j]=0;
                        }
                }
                else if(mField[i][j]==5)
                {
                    if(isCollisionX(newX, i))
                        if(isCollisionY(newY, j)) {
                            myX=400;
                            myY=400;
                            mField[i][j]=0;
                        }
                }
                else if(mField[i][j]==4)
                {
                    if(isCollisionX(newX, i))
                        if(isCollisionY(newY, j)) {
                            myX=100;
                            myY=800;
                            mField[i][j]=0;
                        }
                }

                else if(mField[i][j]==3)
                {
                    if(isCollisionX(newX, i))
                        if(isCollisionY(newY, j)) {
                            mField[i][j]=0;
                            collisionWithKey=true;
                        }
                }
            }
           float coeff = 0.8f;//
           float coeff2 = 0.6f;//
            dx *= (collisionResultX ? -coeff2 : coeff);
            dy *= (collisionResultY ? -coeff2 : coeff);
            myX = myX + dx;
            myY = myY + dy;

    }

    boolean isCollisionX(float newX, int i)
    {
        float tileSize = width / mFieldY;
        float owlSize = width / 12;
        float owlA = newX;
        float owlB = newX + owlSize;
        float tileA = i * tileSize;
        float tileB = (i + 1) * tileSize;
        return ((owlA <= tileA && tileA <= owlB) || (owlA <= tileB && tileB <= owlB));
    }

    boolean isCollisionY(float newY, int j)
    {
        float tileSize = height / 54;
        float owlSize = height / 18;
        float owlA = newY;
        float owlB = newY + owlSize;
        float tileA = j * tileSize;
        float tileB = (j + 1) * tileSize;
        return ((owlA <= tileA && tileA <= owlB) || (owlA <= tileB && tileB <= owlB));

    }

    int width;
    int height;
    void drawMaze(Canvas c)
    {

        paint.setColor(Color.BLUE);
        width = c.getWidth();
        height = c.getHeight();
        int mX, mY;
        mX=width/36;
        mY=height/54;
        Bitmap bitmapTileOfMaze_ = Bitmap.createScaledBitmap(bitmapTileOfMaze,mX,mY,true);
        Bitmap bitmapTileOfMaze2_ = Bitmap.createScaledBitmap(bitmapTileOfMaze2,mX,mY,true);
        Bitmap bitmapOwl_ = Bitmap.createScaledBitmap(bitmapOwl, width/12, height/18, true);
        Bitmap bitmapKey_ = Bitmap.createScaledBitmap(bitmapKey, width/8, height/13, true);
        Bitmap bitmapHole_ = Bitmap.createScaledBitmap(bitmapHole, width/12, height/17, true);

        paint.setColor(Color.BLUE);
        for (int i=0; i<mFieldY; i++)
            for(int j=0; j<mFieldX; j++)
            {
                if(mField[i][j]==1) {
                    c.drawBitmap(bitmapTileOfMaze_, mX * i, mY * j, paint);
                }
                if(mField[i][j]==3 || mField[i][j]==4 || mField[i][j]==5)
                {
                    c.drawBitmap(bitmapHole_, mX * i, mY * j, paint);
                }
                else if(mField[i][j]==2 )
                {
                    c.drawBitmap(bitmapTileOfMaze2_, mX * i, mY * j, paint);
                }
                if(mField[i][j]==6 )
                {
                    c.drawBitmap(bitmapKey_, mX * i, mY * j, paint);
                }

            }

        c.drawBitmap(bitmapOwl_, myX, myY, paint);

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
