package com.example.revisionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Connection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        setTitle("Connection");
        MyDBHelper myDB = new MyDBHelper(Connection.this);
        Button connecterBtn = (Button) findViewById(R.id.connecter);
        Button annulerBtn = (Button) findViewById(R.id.annuler);

        connecterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = (EditText)findViewById(R.id.email);
                EditText password = (EditText)findViewById(R.id.password);
                boolean res = false;
                if(email.getText().toString().length() > 0 && password.getText().toString().length() > 0){
                    //boolean res = myDB.login(email.getText().toString(), password.getText().toString());
                    ArrayList<Etudiant> list = myDB.getAll();
                    for(int i = 0; i < list.size(); i++){
                        if(list.get(i).getEmail().equals(email.getText().toString()) && list.get(i).getPassword().equals(password.getText().toString())){
                            res = true;
                        }
                    }
                    if(res==true){
                        setResult(RESULT_OK);
                        finish();
                    }else
                        Toast.makeText(Connection.this, password.getText(), Toast.LENGTH_SHORT).show();
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