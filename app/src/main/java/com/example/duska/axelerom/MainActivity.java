package com.example.duska.axelerom;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.Timer;

public class MainActivity extends Activity implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor mOrientation;

    Timer timer;
    private float xy_angle=0;
    private float xz_angle=0;
    MazeView mazeView;
    boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mazeView=new MazeView(this);
        setContentView(mazeView);

        //запустим таймер, узнаем высоту\ширину экрана
        timer = new Timer();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); // Получаем менеджер сенсоров
        mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // Получаем датчик положения
        mSensorManager.registerListener(this, mOrientation, SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        xy_angle = event.values[0]; //Плоскость XY
        xz_angle = event.values[1]; //Плоскость XZ


        // передаем в нашу повержность координаты телефона в пространстве
        mazeView.setXY(xy_angle, xz_angle);

    }

    // Запуск активити
    @Override
    public void onStart() {
        super.onStart();
        // Запускаем таймер обновления картинки на экране
        timer.scheduleAtFixedRate(new GraphUpdater(mazeView), 0, 100);

        // регистрируем нашу форму как объект слушающий
        // изменения датчика - акселерометра
        mSensorManager.registerListener(this, mOrientation,
                SensorManager.SENSOR_DELAY_GAME);
        this.firstTime = true;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
