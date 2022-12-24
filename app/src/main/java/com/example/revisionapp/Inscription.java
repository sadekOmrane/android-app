package com.example.revisionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Inscription extends AppCompatActivity {

    MyDBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Button validerBtn = (Button) findViewById(R.id.vlider);
        Button annulerBtn = (Button) findViewById(R.id.annuler);
        setTitle("Inscription");
        validerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB = new MyDBHelper(Inscription.this);
                EditText prenom = (EditText)findViewById(R.id.prenom);
                EditText nom = (EditText)findViewById(R.id.nom);
                EditText matricule = (EditText)findViewById(R.id.matricule);
                EditText classe = (EditText)findViewById(R.id.classe);
                EditText login = (EditText)findViewById(R.id.login);
                EditText password = (EditText)findViewById(R.id.password);
                System.out.println(prenom.getText().toString());
                if(prenom.getText().toString().length() > 0
                        && nom.getText().toString().length() > 0
                        && matricule.getText().toString().length() > 0
                        && classe.getText().toString().length() > 0
                        && login.getText().toString().length() > 0
                        && password.getText().toString().length() > 0
                        ){
                    Etudiant e = new Etudiant(nom.getText().toString(), login.getText().toString(), password.getText().toString(), prenom.getText().toString());
                    myDB.add(e);
                    Intent intent=new Intent();
                    intent.putExtra("nom", prenom.getText().toString()+" "+nom.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });


        annulerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}