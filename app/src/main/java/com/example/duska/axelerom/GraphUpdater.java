package com.example.duska.axelerom;

import android.graphics.Canvas;

import java.util.TimerTask;

/**
 * Created by Duska on 19.04.2017.
 */
public class GraphUpdater extends TimerTask{
    MazeView mazeView;

    GraphUpdater(MazeView mazeView){
        this.mazeView=mazeView;
    }

    @Override
    public void run() {
        Canvas c = mazeView.getHolder().lockCanvas();
        if (c!=null){
            mazeView.drawBackGround(c);
            mazeView.drawMaze(c);
          //  mazeView.drawOwl(c);
            mazeView.getHolder().unlockCanvasAndPost(c);
        }
    }
}
