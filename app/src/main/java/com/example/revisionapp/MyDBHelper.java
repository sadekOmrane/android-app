package com.example.revisionapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "MyDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Etudiant";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_FIRST_NAME = "nom";
    private static final String COLUMN_LAST_NAME = "prenom";


    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_EMAIL+ " TEXT, "+
                COLUMN_PASSWORD+ " TEXT, "+
                COLUMN_FIRST_NAME+ " TEXT, "+
                COLUMN_LAST_NAME+ " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void add(Etudiant e){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL, e.getEmail());
        cv.put(COLUMN_PASSWORD, e.getPassword());
        cv.put(COLUMN_FIRST_NAME, e.getPrenom());
        cv.put(COLUMN_LAST_NAME, e.getNom());
        db.insert(TABLE_NAME, null, cv);
    }

    public ArrayList<Etudiant> getAll(){
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursorToEtudiants(cursor);
    }
    public Etudiant get(int id){
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE ID="+id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursorToEtudiant(cursor);
    }
    public ArrayList<Etudiant> cursorToEtudiants(Cursor cursor){
        ArrayList<Etudiant> list;
        if(cursor.getCount() == 0)
            return list = new ArrayList<>(0);
        list = new ArrayList<>(cursor.getCount());
        cursor.moveToFirst();
        do{
            Etudiant et = new Etudiant();
            et.setId(cursor.getInt(0));
            et.setEmail(cursor.getString(1));
            et.setPassword(cursor.getString(2));
            et.setNom(cursor.getString(3));
            et.setPrenom(cursor.getString(4));
            list.add(et);
        }while (cursor.moveToNext());
        cursor.close();
        return list;
    }
    public Etudiant cursorToEtudiant(Cursor cursor){
        Etudiant e;
        if(cursor.getCount() == 0)
            return e = new Etudiant();
        e = new Etudiant();
        cursor.moveToFirst();
        e.setId(cursor.getInt(0));
        e.setEmail(cursor.getString(1));
        e.setPassword(cursor.getString(2));
        e.setNom(cursor.getString(3));
        e.setPrenom(cursor.getString(4));
        cursor.close();
        return e;
    }

    public boolean login(String email, String password){
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE EMAIL LIKE "+email+" AND PASSWORD LIKE "+password;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Etudiant e = cursorToEtudiant(cursor);
        if(e.getEmail()==email)
            return true;
        return false;
    }

}
