package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {
     Toolbar tool ;
     RecyclerView items;
     RecycleAdapter adapter;
     List<Note> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tool = findViewById(R.id.toolbar_bar);
        setSupportActionBar(tool);

        notedatabase dp=new notedatabase(this);
        notes=dp.getnotes();

//        Toast.makeText(MainActivity.this,notes.get(0).getTitle()+"",Toast.LENGTH_LONG).show();

        items=findViewById(R.id.List0FNotes);
        items.setLayoutManager(new LinearLayoutManager(this));
        adapter=new RecycleAdapter(this,notes);
        //Toast.makeText(this," notes -> "+notes.get(0).getTitle(), Toast.LENGTH_SHORT).show();
        items.setAdapter(adapter);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_item)
        {
            Intent intent=new Intent(this,AddNote.class);
            startActivityForResult(intent,1);
            Toast.makeText(getApplicationContext(), "Add is clicked", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.Weather)
        {
            Intent intent=new Intent(this,weather_activity.class);
            startActivityForResult(intent,2);
        }

        return super.onOptionsItemSelected(item);
    }
}