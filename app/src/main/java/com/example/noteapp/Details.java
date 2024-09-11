package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {
    TextView details;
    notedatabase dp;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        details=findViewById(R.id.detailsOfNote);

        Intent intent=getIntent();
        long id=intent.getLongExtra("ID",0);


        //Restore data from Database
        dp=new notedatabase(this);
         note=dp.getnote(id);
        getSupportActionBar().setTitle(note.getTitle());
        details.setText(note.getContent());
        details.setMovementMethod(new ScrollingMovementMethod());
        Toast.makeText(this, "Title->"+note.getTitle(), Toast.LENGTH_SHORT).show();

        
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dp.deleteNote(note.getId());
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                Toast.makeText(Details.this, "Note is deleted", Toast.LENGTH_SHORT).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.edit_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.edit_menu) {
            //send user to edit activity
            Toast.makeText(this, "edit note", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,Edit.class);
            intent.putExtra("ID",note.getId());
            startActivity(intent);
            //onBackPressed();


        }

        return true;

    }
}