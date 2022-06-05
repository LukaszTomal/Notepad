package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.NotActiveException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private Button addNote;
    private Button deleteNote;
    private Button updateNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        addNote = (Button) findViewById(R.id.buttonAdd);
        deleteNote = (Button) findViewById(R.id.buttonDelete);
        updateNote = (Button) findViewById(R.id.buttonUpdate);

        MyDataBase zb = new MyDataBase(this);


        addNote.setOnClickListener(new View.OnClickListener() {
                    @Override
            public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Dodano", Toast.LENGTH_SHORT).show();
                zb.addNote(new Notepad("28.05.2022", "To jest notatka numer 1"));
                zb.addNote(new Notepad("28.05.2022", "To jest notatka numer 2"));
                zb.addNote(new Notepad("28.05.2022", "To jest notatka numer 3"));
                zb.addNote(new Notepad("28.05.2022", "To jest notatka numer 4"));
                zb.addNote(new Notepad("28.05.2022", "To jest notatka numer 5"));
                for (Notepad notepad : zb.showsAll()) {
                    Log.d("DB", notepad.getNr() + " " + notepad.getData() + " " + notepad.getNote());

                }
            }
        });

        deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Usunięto", Toast.LENGTH_SHORT).show();
                zb.deleteNote(68);
                zb.deleteNote(69);
                zb.deleteNote(70);
                zb.deleteNote(71);
                zb.deleteNote(72);

                for(Notepad notepad:zb.showsAll()){
                    Log.d("DB", notepad.toString());
                }


            }
        });

        updateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Zaktualizowano", Toast.LENGTH_SHORT).show();
                Notepad toChange = new Notepad("23.05.2022", "To jest ponownie zaktualizowana notatka numer 4");
                toChange.setNr(71L);
                zb.updateNote(toChange);
                for(Notepad notepad:zb.showsAll()) {
                    Log.d("DB", notepad.toString());
                }
            }
        });

        List<Notepad> values = zb.showsAll();
        ArrayAdapter<Notepad> adapter = new ArrayAdapter<Notepad>(this,
                android.R.layout.simple_list_item_1,values);
        list.setAdapter(adapter);

    }
}