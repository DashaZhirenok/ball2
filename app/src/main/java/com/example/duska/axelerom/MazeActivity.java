package com.example.duska.axelerom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MazeActivity extends AppCompatActivity  {

    private Button btnReturn, btnMenu, btnOk;
    private TextView textView1;
    private EditText editText;
    public ArrayList<String> arrayNames;

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
            btnOk = (Button) this.findViewById(R.id.btnOk);
            textView1 = (TextView) this.findViewById(R.id.textView1);
            editText = (EditText) this.findViewById(R.id.editText);

            textView1.setText("Your time: "+GAME_TIME);

            btnReturn.setOnClickListener(clickOnButton);
            btnMenu.setOnClickListener(clickOnButton);
            btnOk.setOnClickListener(clickOnButton);
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
                case (R.id.btnOk): {
                    if(TextUtils.isEmpty(editText.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Введите, пожалуйста, имя, затем нажмите 'OK'", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        arrayNames = new ArrayList<String>();
                        String tmp;
                        tmp=editText.getText().toString();
                        arrayNames.add(tmp);
                        Toast.makeText(getApplicationContext(), "Ваш результат записан!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }
    };


}
