package com.example.duska.axelerom;

/**
 * Created by Duska on 18.05.2017.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gameDb";
    public static final String TABLE_RESULTS = "results";

    //the first table
    public static final String KEY_ID = "_id";
    public static final String KEY_PLAYERNAME = "playername";
    public static final String KEY_TIME = "time";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_RESULTS + "(" + KEY_ID
                + " integer primary key," + KEY_PLAYERNAME + " text," + KEY_TIME + " integer" + ")"); //CREATE THE FIRST TABLE

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_RESULTS );

        onCreate(db);

    }
}

