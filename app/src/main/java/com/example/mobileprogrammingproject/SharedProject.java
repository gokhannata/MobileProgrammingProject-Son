package com.example.mobileprogrammingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SharedProject extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String User = "user";
    public static final String Age = "age";
    public static final String Height = "height";
    public static final String Weight="weight";
    public static final String Check="check";
    public static final String Check2="check2";
    public static final String Check3="switch";
    EditText editTextUser,editTextAge,editTextHeight,editTextWeight;
    RadioGroup radioSexGroup;
    RadioButton radioSexButton,radioMale,radioFemale;
    Switch switch1;
    Button btnKaydet;
    SharedPreferences sharedPreferences;
    private String adGoster, yasGoster,boyGoster,kiloGoster;
    private boolean checkOnOffMale,checkOnOffFemale,switchOnOff;
    private int selectedId;
    Kullanicilar kullanicilar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_project);
        editTextUser=(EditText)findViewById(R.id.editTextUser);
        editTextAge=(EditText)findViewById(R.id.editTextAge);
        editTextHeight=(EditText)findViewById(R.id.editTextHeight);
        editTextWeight=(EditText)findViewById(R.id.editTextWeight);
        btnKaydet=(Button)findViewById(R.id.btnKaydet);
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        radioMale=(RadioButton)findViewById(R.id.radioMale);
        radioFemale=(RadioButton)findViewById(R.id.radioFemale);
        selectedId = radioSexGroup.getCheckedRadioButtonId();
        radioSexButton = (RadioButton) findViewById(selectedId);
        switch1=(Switch) findViewById(R.id.switch1);
        sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerileriKaydet();
            }
        });
        VerileriGoster();
        VerileriDegistir();
    }
    public void VerileriKaydet(){
        SharedPreferences sharedPreferences=getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(User,editTextUser.getText().toString());
        editor.putString(Age,editTextAge.getText().toString());
        editor.putString(Height,editTextHeight.getText().toString());
        editor.putString(Weight,editTextWeight.getText().toString());
        editor.putBoolean(Check,radioMale.isChecked());
        editor.putBoolean(Check2,radioFemale.isChecked());
        editor.putBoolean(Check3,switch1.isChecked());
        editor.apply();
        Toast.makeText(SharedProject.this,"Thanks",Toast.LENGTH_LONG).show();
    }
    public void VerileriGoster(){
        SharedPreferences sharedPreferences=getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        //int checkedRadioButtonId = sharedPreferences.getInt("checkedRadioButtonId", R.id.radioMale);

        adGoster=sharedPreferences.getString(User,"");
        yasGoster=sharedPreferences.getString(Age,"");
        kiloGoster=sharedPreferences.getString(Weight,"");
        boyGoster=sharedPreferences.getString(Height,"");
        checkOnOffMale=sharedPreferences.getBoolean(Check,false);
        checkOnOffFemale=sharedPreferences.getBoolean(Check2,false);
        switchOnOff=sharedPreferences.getBoolean(Check3,false);
    }
    public void VerileriDegistir(){
        editTextUser.setText(adGoster);
        editTextAge.setText(yasGoster);
        editTextHeight.setText(boyGoster);
        editTextWeight.setText(kiloGoster);
        radioMale.setChecked(checkOnOffMale);
        radioFemale.setChecked(checkOnOffFemale);
        switch1.setChecked(switchOnOff);
    }

}
