package com.example.mobileprogrammingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {
    ArrayList<Kullanicilar> kullanicilarList=new ArrayList<>();
    private ArrayList<Kullanicilar> allContacts=new ArrayList<>();
    private VeriTabani mDatabase;
    RecyclerView recyclerView;
    Context context=this;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //Intent intent=getIntent();
        //String username=intent.getStringExtra("etUsername");
        //Toast.makeText(this,"Hoşgeldin "+username,Toast.LENGTH_LONG).show();
        recyclerView=findViewById(R.id.recycler_view);
        mDatabase=new VeriTabani(context);
        allContacts=mDatabase.kullanicilariGetir();
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        CustomAdapter customAdapter=new CustomAdapter(allContacts,context);
        recyclerView.setAdapter(customAdapter);
    }
}
