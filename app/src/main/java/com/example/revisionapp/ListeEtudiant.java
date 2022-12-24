package com.example.revisionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListeEtudiant extends AppCompatActivity {
    MyDBHelper myDB;
    ArrayList<Etudiant> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_etudiant);
        setTitle("Liste des Etudiants");
        Button annulerBtn = (Button)findViewById(R.id.annuler);
        annulerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        myDB = new MyDBHelper(ListeEtudiant.this);
        list = myDB.getAll();
        ArrayList<String> etudiants = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            //Toast.makeText(ListeEtudiant.this, "Bonjour "+list.get(i).getEmail(), Toast.LENGTH_SHORT).show();
            etudiants.add(list.get(i).getEmail());
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.textView,etudiants);
        ListView listView = (ListView) findViewById(R.id.liste);
        listView.setAdapter(adapter);
    }
}