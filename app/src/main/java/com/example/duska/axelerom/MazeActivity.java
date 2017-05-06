package com.example.duska.axelerom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MazeActivity extends AppCompatActivity implements View.OnClickListener {

    Button firstButton, secondButton;
    TextView textView1;

    // режим запуска активити - 0 первый запуск
    // 1 - запуск активити после проигрыша
    public static int GAME_MODE=0;

    public static int GAME_SCORE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onStart(){
        super.onStart();
        // загрузка разных разметок
        if (GAME_MODE==0){
            setContentView(R.layout.activity_maze);
            firstButton = (Button) this.findViewById(R.id.firstButton);
            firstButton.setOnClickListener(this);
        }
        else
        {
            setContentView(R.layout.activity_maze2);
            secondButton = (Button) this.findViewById(R.id.secondButton);
            textView1 = (TextView) this.findViewById(R.id.textView1);
            textView1.setText("Your score: "+GAME_SCORE);
            secondButton.setOnClickListener(this);
        }
    }

    public void onClick(View v) {
        // Для любой разметки если мы нажимем на кнопку, то игра запускается
        Intent gotoGame = new Intent(this, MainActivity.class);
        GAME_MODE=0;
        GAME_SCORE=0;
        this.startActivity(gotoGame);
    }

}
