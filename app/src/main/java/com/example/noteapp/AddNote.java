package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


import com.example.noteapp.Retrofit.Main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNote extends AppCompatActivity {
    Toolbar tool;
    EditText title,details;
    Calendar calender;
    String today_date,current_time;
    SimpleDateFormat s;
    Date d;
    String date;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        tool=findViewById(R.id.toolbar_bar);
        setSupportActionBar(tool);
        tool.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setTitle("New Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title=findViewById(R.id.edt_title);
        details=findViewById(R.id.edt_details);

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 if(charSequence.length()!=0){
                     getSupportActionBar().setTitle(charSequence);
                 }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        calender=Calendar.getInstance();
        today_date=calender.get(Calendar.YEAR)+"/"+(calender.get(Calendar.MONTH)+1)+"/"+calender.get(Calendar.DAY_OF_MONTH);
        current_time=pad(calender.get(Calendar.HOUR))+":"+pad(calender.get(Calendar.MINUTE));
        Log.d("Calender", "Date and Time "+today_date +"and"+current_time);
    }

    private String pad(int i) {
        if(i<10)
            return "0"+i;
        return String.valueOf(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_menu)
        {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        if(item.getItemId()==R.id.save_menu)
        {
            Note note1=new Note(title.getText().toString(),details.getText().toString(),today_date,current_time);
            notedatabase dp=new notedatabase(this);
            long id=dp.addnote(note1);

            Toast.makeText(this, note1.getTitle(),Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
            goToMain();


        }

        return true;

    }

    private void goToMain() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}