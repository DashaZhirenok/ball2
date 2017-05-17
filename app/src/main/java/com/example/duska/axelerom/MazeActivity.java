package com.example.duska.axelerom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MazeActivity extends AppCompatActivity  {

    private Button btnReturn, btnMenu;
    private TextView textView1;

    // режим запуска активити - 0 первый запуск
    // 1 - запуск активити, если проигрыш
    public static int GAME_MODE=0;

    public static int GAME_TIME=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onStart(){
        super.onStart();
        // загрузка разных разметок
        if (GAME_MODE==1)
        /*{
            setContentView(R.layout.activity_maze);
            firstButton = (Button) this.findViewById(R.id.firstButton);
            firstButton.setOnClickListener(this);
        }
        else*/
        {
            setContentView(R.layout.activity_maze);
            btnReturn = (Button) this.findViewById(R.id.btnReturn);
            btnMenu = (Button) this.findViewById(R.id.btnMenu);
            textView1 = (TextView) this.findViewById(R.id.textView1);
            textView1.setText("Your time: "+GAME_TIME);
            btnReturn.setOnClickListener(clickOnButton);
            btnMenu.setOnClickListener(clickOnButton);
        }
    }

    View.OnClickListener clickOnButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case (R.id.btnMenu):
                {
                    Intent gotoMenu = new Intent(MazeActivity.this, MenuActivity.class);
                    GAME_MODE = 0;
                    GAME_TIME = 0;
                    startActivity(gotoMenu);
                    MazeActivity.this.finish();
                    break;
                }
                case (R.id.btnReturn): {
                    Intent gotoGame = new Intent(MazeActivity.this, MainActivity.class);
                    GAME_MODE = 0;
                    GAME_TIME = 0;
                    startActivity(gotoGame);
                    MazeActivity.this.finish();
                    break;
                }
            }

        }
    };


}
