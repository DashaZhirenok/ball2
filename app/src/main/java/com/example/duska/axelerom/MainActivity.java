package com.example.duska.axelerom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    boolean firstTime;
    private float xy_angle=0;
    private float xz_angle=0;
    MazeView mazeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mazeView=new MazeView(this);
        setContentView(mazeView);

        //запустим таймер
        timer = new Timer();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); // Получаем менеджер сенсоров
        mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // Получаем датчик положения
        mSensorManager.registerListener(this, mOrientation, SensorManager.SENSOR_DELAY_NORMAL);//частота измерений

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
        // Запускаем таймер обновления положения змейки
        timer.scheduleAtFixedRate(new StepUpdater(this), 0, 500);

        // регистрируем нашу форму как объект слушающий
        // изменения датчика - акселерометра
        mSensorManager.registerListener(this, mOrientation,
                SensorManager.SENSOR_DELAY_GAME);
        this.firstTime = true;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    // Обрабатываем остановку активити
    @Override
    public void onStop() {
        super.onStop();
        // Останавливаем таймеры
        timer.cancel();
        timer.purge();
        // Отписываемся от получения сообщений об изменении
        // от датчика
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    // Этот метод вызывается из потока одного из таймеров

    public void Step() {
        // Если мы взяли ключик, то открываем другую активи
        if (mazeView.collisionWithKey) {
            MazeActivity.GAME_MODE=1;
            Intent gotoMazeActivity = new Intent(this, MazeActivity.class);
            startActivity(gotoMazeActivity);
            this.finish();

        }
        // Если все впорядке то оставляем стартовую активи
        else{
            MazeActivity.GAME_MODE=0;
        }
    }


}
