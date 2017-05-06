package com.example.duska.axelerom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Menu extends AppCompatActivity {

    ImageView btnAbout, btnPlay, btnName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnAbout = (ImageView) findViewById(R.id.btnAbout);
        btnPlay = (ImageView) findViewById(R.id.btnPlay);
        btnName = (ImageView) findViewById(R.id.btnName);

        btnAbout.setOnClickListener(onClickMyButton);
        btnPlay.setOnClickListener(onClickMyButton);
        btnName.setOnClickListener(onClickMyButton);
    }

    View.OnClickListener onClickMyButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.btnAbout:
                    //to do
                    break;
                case R.id.btnName:
                    //to do
                    break;
                case R.id.btnPlay:
                    Intent gotoPlay = new Intent();
                    gotoPlay.setClass(Menu.this, MazeActivity.class);
                    startActivity(gotoPlay);
            }

        }
    };
}
