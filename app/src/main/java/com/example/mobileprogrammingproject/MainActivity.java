package com.example.mobileprogrammingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etUsername,etPassword;
    TextView tvUsername,tvPassword;
    Button btnLogin,btnSave,btnMenuListele;
    Context context=this;
    VeriTabani db=new VeriTabani(context);
    List<Kullanicilar> list;
    ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUsername=(TextView)findViewById(R.id.tvUsername);
        tvPassword=(TextView)findViewById(R.id.tvPassword);
        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnMenuListele=(Button)findViewById(R.id.btnMenuListele);
        btnSave=(Button)findViewById(R.id.btnSave);
        //db.onUpgrade(db.getWritableDatabase(),1,2);
        db.getWritableDatabase();
        /*
        db.kullaniciEkle(new Kullanicilar("Gokhan","GokhanAta123",R.mipmap.man));
        db.kullaniciEkle(new Kullanicilar("Berk","Toprak",R.mipmap.man));
        db.kullaniciEkle(new Kullanicilar("Levent","Levent12",R.mipmap.man));
        db.kullaniciEkle(new Kullanicilar("Busra","Gunduz",R.mipmap.woman));
        db.kullaniciEkle(new Kullanicilar("Muhammed","Topal",R.mipmap.man));
        db.kullaniciEkle(new Kullanicilar("Burak","Burak112",R.mipmap.man));
        db.kullaniciEkle(new Kullanicilar("Zeynep","Kilic",R.mipmap.woman));
        db.kullaniciEkle(new Kullanicilar("Merve","Duman",R.mipmap.woman));
        db.kullaniciEkle(new Kullanicilar("Yucel","Aydin",R.mipmap.man));
         */
         /*
        Listview yapısında hazırlanıp daha sonra recycler view istendiği için değiştirildi.
        btnListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list=db.kullanicilariGetir();
                List<String> listBaslik=new ArrayList<>();
                for(int i=0;i<list.size();i++){
                    listBaslik.add(i,list.get(i).getUsername());
                }
                mAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,android.R.id.text1,listBaslik);
                listView.setAdapter(mAdapter);
            }
        });

         */

    }
    public void saveClick(View view){
        String kullaniciAdi=etUsername.getText().toString();
        String sifre=etPassword.getText().toString();
        if(kullaniciAdi.matches("")&&sifre.matches("")) {
            Toast.makeText(context,"Kullanıcı adı ve Şifre boş geçilemez.",Toast.LENGTH_LONG).show();
            finish();
        }
        db.kullaniciEkle(new Kullanicilar(kullaniciAdi,sifre,R.mipmap.man));
    }
    public void loginClick(View view){
        String getKullanici=etUsername.getText().toString();
        String getSifre=etPassword.getText().toString();
        Boolean checkLogin=db.login(getKullanici,getSifre);
        if(checkLogin){
            Intent myIntent=new Intent(context,Menu.class);
            myIntent.putExtra("etUsername",getKullanici);
            MainActivity.this.startActivity(myIntent);
            Toast.makeText(context,"Giriş başarılı",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context,"Kullanıcı adı ya da Şifre yanlış",Toast.LENGTH_LONG).show();
        }
    }
    public void menuClick(View view){
        String getKullanici=etUsername.getText().toString();
        String getSifre=etPassword.getText().toString();
        Boolean checkLogin=db.login(getKullanici,getSifre);
        if(getKullanici.matches("")&&getSifre.matches("")){
            Toast.makeText(context,"Kullanıcı adı ve şifre boş geçilemez",Toast.LENGTH_LONG).show();
        }
        if(checkLogin){
            Intent myIntent=new Intent(context,IslemMenusu.class);
            //myIntent.putExtra("etUsername",getKullanici);
            MainActivity.this.startActivity(myIntent);
            Toast.makeText(context,"İşlemler Menüsüne yönlendiriliyorsunuz.",Toast.LENGTH_LONG);
        }
        else{
            Toast.makeText(context,"Kullanıcı adı ya da Şifre yanlış",Toast.LENGTH_LONG).show();
        }
    }
}
