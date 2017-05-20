package com.example.duska.axelerom;

import java.util.TimerTask;

/**
 * Created by Duska on 17.05.2017.
 */
public class StepUpdater extends TimerTask {

    MainActivity mainActivity;

    StepUpdater(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void run() {
        mainActivity.Step();
    }
}
