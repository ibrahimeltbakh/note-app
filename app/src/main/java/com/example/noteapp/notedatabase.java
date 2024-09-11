package com.example.noteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

public class notedatabase extends SQLiteOpenHelper {

    public static final String DB_NAME="Notes";
    public static final int DB_VERSION=3;
    public static final String TABLE_NAME="AddNote";


    //coloum database name
    public static final String Key_id="_id";
    public static final String Key_content="content";
    public static final String key_date="date";
    public static final String Key_time ="time";
    public static final String key_title="title";


    public notedatabase(Context context){
        super(context,DB_NAME,null,DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE AddNote(_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT , content TEXT, date TEXT , time TEXT   )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS AddNote");
        onCreate(sqLiteDatabase);

    }
    public long addnote(Note note)
    {
        SQLiteDatabase dp=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("title",note.getTitle());
        values.put("content",note.getContent());
        values.put("date",note.getDate());
        values.put("time",note.getTime());

        long ID=dp.insert(TABLE_NAME,null,values);
        Log.d("Inserted", "ID -> "+ID);
        return ID;
    }
    public Note getnote(long id)
    {
        //select *from database where id=1
        SQLiteDatabase dp=getReadableDatabase();
        //pointer
        Cursor cursor=dp.query(TABLE_NAME,new String[]{"_id","title","content","date","time"},"_id"+"=?",
                new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null)
                cursor.moveToFirst();

        Note note3=new Note(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        return note3;

    }
    public List<Note>getnotes(){
        SQLiteDatabase dp=getReadableDatabase();
        List<Note> allnote=new ArrayList<>();
        //String query="SELECT * from "+TABLE_NAME+"ORDER BY "+Key_id+"DESC";
        //select * from databasename
        Cursor c=dp.rawQuery("SELECT * from  AddNote",null );
        if(c.moveToFirst()) {
            do{
                Note note=new Note();
                note.setId(c.getLong(0));
                note.setTitle(c.getString(1));
                note.setContent(c.getString(2));
                note.setDate(c.getString(3));
                note.setTime(c.getString(4));
                allnote.add(note);


            }while (c.moveToNext());
        }
        return allnote;
    }
    public int editNote(Note note){
     SQLiteDatabase dp=getWritableDatabase();
     ContentValues c=new ContentValues();
        Log.d("Edited", "Edited Title: ->"+note.getTitle()+"\n  ID ->"+note.getId());
        c.put("title",note.getTitle());
        c.put("content",note.getContent());
        c.put("date",note.getDate());
        c.put("time",note.getTime());

        return dp.update(TABLE_NAME,c,Key_id+"=?",new String[]{String.valueOf(note.getId())});

    }

    public void deleteNote(long id)
    {
        SQLiteDatabase dp=getWritableDatabase();
        dp.delete(TABLE_NAME,Key_id+"=?",new String[]{String.valueOf(id)});
        dp.close();
    }
}
