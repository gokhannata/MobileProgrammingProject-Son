package com.example.mobileprogrammingproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class SendMail extends AppCompatActivity {
    private static final int PICK_FROM_GALLERY = 101;
    Uri URI;
    EditText etMailTo, etTopic, etDescription;
    Button btnSend,btnFile;
    String adres ;
    String konu;
    String icerik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        btnFile=(Button) findViewById(R.id.btnFile);
        btnSend = (Button) findViewById(R.id.btnSend);
        etMailTo=(EditText)findViewById(R.id.etMailTo);
        etDescription=(EditText)findViewById(R.id.etDescription);
        etTopic=(EditText)findViewById(R.id.etTopic);
        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
            //tvAttachment.setText(URI.getLastPathSegment());
            //tvAttachment.setVisibility(View.VISIBLE);
        }
    }
    public  void openFolder(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
    }
    public void sendMail(){

        try {
            adres = etMailTo.getText().toString();
            konu = etTopic.getText().toString();
            icerik = etDescription.getText().toString();
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{adres});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, konu);
            if (URI != null) {
                emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
            }
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, icerik);
            this.startActivity(Intent.createChooser(emailIntent, "Sending email..."));
        } catch (Throwable t) {
            Toast.makeText(this, "Request failed try again: "+ t.toString(), Toast.LENGTH_LONG).show();
        }
    }

}
