package com.example.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;


public class MyDataBase extends SQLiteOpenHelper{

        public MyDataBase(Context context){
            super(context, "notepad.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    "CREATE TABLE NOTES(" +
                    "NR INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "DATA CHAR," +
                    " NOTATKI CHAR);" +
                    "");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public void addNote (Notepad notepad){
                SQLiteDatabase db = getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("data", notepad.getData());
                values.put("notatki", notepad.getNote());
                db.insertOrThrow("notes", null, values);
        }

        public void deleteNote (int id){
                SQLiteDatabase db = getWritableDatabase();
                String [] arguments = {"" + id};
                db.delete("notes", "nr=?", arguments);
        }

        public void updateNote (Notepad notepad){
                SQLiteDatabase db = getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("data", notepad.getData());
                values.put("notatki", notepad.getNote());
                String args[] = {String.valueOf(notepad.getNr())};
                db.update("notes", values, "nr=?", args);
        }

        public List<Notepad> showsAll(){
            List<Notepad> notes = new LinkedList<Notepad>();
            String[] columns = {"nr", "data", "notatki"};
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query("notes", columns, null, null,
                    null, null, null);
            while(cursor.moveToNext()){
                    Notepad notepad = new Notepad();
                    notepad.setNr(cursor.getLong(0));
                    notepad.setData(cursor.getString(1));
                    notepad.setNote(cursor.getString(2));
                    notes.add(notepad);
            }
            return notes;
        }

        public Notepad addNotepad(int nr){
                Notepad notepad = new Notepad();
                SQLiteDatabase db  = getReadableDatabase();
                String[] columns = {"nr", "data", "notatki"};
                String args[] = {nr + ""};
                Cursor cursor = db.query("notatki", columns, "nr=?", args,
                        null,null,null,null);
                if(cursor != null){
                        cursor.moveToFirst();
                        notepad.setNr(cursor.getLong(0));
                        notepad.setData(cursor.getString(1));
                        notepad.setNote(cursor.getString(2));
                }
                return notepad;
        }



}

