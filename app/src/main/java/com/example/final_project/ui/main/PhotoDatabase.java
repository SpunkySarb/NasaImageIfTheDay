package com.example.final_project.ui.main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PhotoDatabase extends SQLiteOpenHelper {
    public static final String NasaImage = "NasaImageofDay";
    public static final int VersionInfo = 2;
    public static final String TableName = "DateofImage";
    public static final String Col_id = "id";

    public static final String COLUMN_DATE = "Date";

    public PhotoDatabase(Context ctx) {
        super(ctx, NasaImage, null, VersionInfo);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        db.execSQL("CREATE TABLE " + TableName + "( " + Col_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DATE + " INTEGER )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);


    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Log.i("Database upgrade","Old verion: "+oldVersion + "New Version : "+ newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

}
