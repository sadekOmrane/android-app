package com.example.revisionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Button webBtn = (Button) findViewById(R.id.web);
        Button telBtn = (Button) findViewById(R.id.tel);
        setTitle("Contacter");
        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri x=Uri.parse("https://fr.yahoo.com/?guccounter=1");
                startActivity(new Intent(Intent.ACTION_VIEW,x));
            }
        });


        telBtn.setOnClickListener (view -> {
            if (ActivityCompat.checkSelfPermission(Contact.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                ActivityCompat.requestPermissions(Contact.this, PERMISSIONS_STORAGE, 1);
            } else {
                Uri uri = Uri.parse("tel:22222222");
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                startActivity(intent);
            }
        });
    }
}






