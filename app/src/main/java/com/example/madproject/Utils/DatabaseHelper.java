package com.example.madproject.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.madproject.AddFragment;
import com.example.madproject.ListFragment;
import com.example.madproject.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public static final String DATABASE_NAME = "TODO_DATABASE";
    private static final String TABLE_NAME = "TODO_TABLE";
    private static final String col_1 = "ID";
    private static final String col_2 = "TASK";
    private static final String col_3 = "STATUS";


    public DatabaseHelper(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, TASK TEXT, STATUS INTEGER )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }



    public void insertTask(ToDoModel model)
    {
        db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_2, model.getTask());

        values.put(col_3,0);
        db.insert(TABLE_NAME,null,values);
    }

    public void updateTask(int id, String task)
    {
        db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_2,task);
        db.update(TABLE_NAME,values,"ID=?",new String[]{String.valueOf(id)});

    }


    public void updateStatus(int id, int status)
    {
        db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_3,status);
        db.update(TABLE_NAME,values,"ID=?",new String[]{String.valueOf(id)});

    }

    public void deleteTask(int id)
    {
        db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"ID=?",new String[]{String.valueOf(id)});


    }

    public List<ToDoModel> getAllTask()
    {
        db=this.getWritableDatabase();
        Cursor cursor = null;

        List<ToDoModel> modelList = new ArrayList<>();

        db.beginTransaction();
        try {
            cursor =db.query(TABLE_NAME,null,null,null,null,null,null);

            if (cursor != null)
            {
                if (cursor.moveToFirst())
                {
                    do {
                        ToDoModel task = new ToDoModel();
                        task.setId(cursor.getInt(cursor.getColumnIndexOrThrow(col_1)));
                        task.setTask(cursor.getString(cursor.getColumnIndexOrThrow(col_2)));
                        task.setStatus(cursor.getInt(cursor.getColumnIndexOrThrow(col_3)));
                        modelList.add(task);

                    }
                    while (cursor.moveToFirst());
                }
            }

        }
        finally {
            db.endTransaction();
            cursor.close();
        }
        return modelList;


    }





}
