package com.example.revisionapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDBHelper myDB;
    ArrayList<Etudiant> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Gestion");
        myDB = new MyDBHelper(MainActivity.this);

        Button inscrireBtn = (Button)findViewById(R.id.inscrire);
        Button connecterBtn = (Button)findViewById(R.id.connecter);
        Button contacterBtn = (Button)findViewById(R.id.contacter);
        Button listeEtudiantBtn = (Button)findViewById(R.id.listEtudiant);
        inscrireBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, Inscription.class);
                startActivityForResult(intent, 1);
            }
        });

        connecterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent.putExtra("nom", nom.getText().toString());
                Intent intent = new Intent(MainActivity.this, Connection.class);
                startActivityForResult(intent, 2);
            }
        });

        contacterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Contact.class);
                startActivity(intent);
            }
        });
        listeEtudiantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListeEtudiant.class);
                startActivityForResult(intent, 3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:{
                switch (resultCode){
                    case RESULT_OK:{
                        String nom = "";
                        Bundle infoNom = data.getExtras();
                        if(infoNom != null){
                            nom=infoNom.getString("nom");
                        }
                        Toast.makeText(MainActivity.this, "Bonjours "+nom, Toast.LENGTH_SHORT).show();

                        break;
                    }
                    case RESULT_CANCELED:{
                        Toast.makeText(MainActivity.this, "inscription annuler", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                break;
            }
            case 2:{
                switch (resultCode){
                    case RESULT_OK:{
                        Toast.makeText(MainActivity.this, "connection avec succés", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case RESULT_CANCELED:{
                        Toast.makeText(MainActivity.this, "connection annuler", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                break;
            }
        }
    }
}