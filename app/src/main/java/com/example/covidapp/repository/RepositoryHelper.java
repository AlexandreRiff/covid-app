package com.example.covidapp.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RepositoryHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "covidApp";
    private static int VERSION = 1;

    public RepositoryHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlUser = "CREATE TABLE " + RepositoryUser.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT NOT NULL, email TEXT UNIQUE NOT NULL, password TEXT NOT NULL)";

        String sqlVaccine = "CREATE TABLE " + RepositoryVaccine.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, dose INTEGER NOT NULL, type TEXT NOT NULL, date TEXT NOT NULL, id_user INTEGER NOT NULL, FOREIGN KEY(id_user) REFERENCES user(id))";

        String sqlSympton = "CREATE TABLE " + RepositorySympton.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, answer TEXT NOT NULL, date TEXT NOT NULL, id_user INTEGER NOT NULL, FOREIGN KEY(id_user) REFERENCES user(id))";

        sqLiteDatabase.execSQL(sqlUser);
        sqLiteDatabase.execSQL(sqlVaccine);
        sqLiteDatabase.execSQL(sqlSympton);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
