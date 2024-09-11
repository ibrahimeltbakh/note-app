package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import java.util.Calendar;

public class Edit extends AppCompatActivity {
    Toolbar tool;
    EditText title,details;
    Calendar calender;
    String today_date,current_time;
    notedatabase dp;
    Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent=getIntent();
        long id=intent.getLongExtra("ID",0);
         dp=new notedatabase(this);
         note=dp.getnote(id);

        tool=findViewById(R.id.toolbar_bar);
        setSupportActionBar(tool);
        tool.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setTitle(note.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title=findViewById(R.id.edt_title);
        details=findViewById(R.id.edt_details);
        title.setText(note.getTitle());
        details.setText(note.getContent());
        getSupportActionBar().setTitle(note.getTitle());
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
        else if(item.getItemId()==R.id.save_menu)
        {
           note.setTitle(title.getText().toString());
           note.setContent(details.getText().toString());
          // Intent intent=new Intent(getApplicationContext(),MainActivity.class);
           int id=dp.editNote(note);
           if(id==note.getId()){
               Toast.makeText(this, "Note Update", Toast.LENGTH_LONG).show();
           }
           else {
               Toast.makeText(this, "Error Updating Note", Toast.LENGTH_LONG).show();
           }

            //Toast.makeText(this, note.getTitle(),Toast.LENGTH_SHORT).show();
            //onBackPressed();
            Intent intent=new Intent(getApplicationContext(),Details.class);
            intent.putExtra("ID",note.getId());
            startActivity(intent);
        }
        else{
            title.setError("Title can not be blank,");
        }

        return true;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
