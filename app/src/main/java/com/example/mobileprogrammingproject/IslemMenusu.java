package com.example.mobileprogrammingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class IslemMenusu extends AppCompatActivity {
    ImageButton imgBtnEmail,imgBtnShared,imgBtnUserList,imgBtnSensor;
    TextView tvMail,tvPreferences,tvUserList,tvSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_islem_menusu);
        imgBtnEmail=(ImageButton)findViewById(R.id.imgBtnEmail);
        imgBtnShared=(ImageButton)findViewById(R.id.imgBtnShared);
        imgBtnUserList=(ImageButton)findViewById(R.id.imgBtnUserList);
        imgBtnSensor=(ImageButton)findViewById(R.id.imgBtnSensor);
        tvMail=(TextView)findViewById(R.id.tvMail);
        tvPreferences=(TextView)findViewById(R.id.tvPreferences);
        tvUserList=(TextView)findViewById(R.id.tvUserList);
        tvSensor=(TextView)findViewById(R.id.tvSensor);

        imgBtnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(IslemMenusu.this,SendMail.class);
                //myIntent.putExtra("ali",getUserName);
                IslemMenusu.this.startActivity(myIntent);

            }
        });
        imgBtnShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(IslemMenusu.this,SharedProject.class);
                IslemMenusu.this.startActivity(myIntent);
            }
        });
        imgBtnUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(IslemMenusu.this,Menu.class);
                IslemMenusu.this.startActivity(myIntent);
            }
        });
        imgBtnSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(IslemMenusu.this,SensorProject.class);
                IslemMenusu.this.startActivity(myIntent);
            }
        });


    }
}
