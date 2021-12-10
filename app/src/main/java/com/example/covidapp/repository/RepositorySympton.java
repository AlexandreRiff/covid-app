package com.example.covidapp.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.example.covidapp.model.Sympton;
import com.example.covidapp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorySympton extends RepositoryHelper {

    protected static String TABLE_NAME = "sympton";

    public RepositorySympton(@Nullable Context context) {
        super(context);
    }

    public void insert(Sympton sympton) {
        ContentValues values = new ContentValues();
        values.put("answer", sympton.getAnswer());
        values.put("date", sympton.getDate());
        values.put("id_user", sympton.getIdUser());

        final long ret = getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    public List<Sympton> fetchAll() {
        List<Sympton> data = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC";

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            Sympton sympton = new Sympton();
            sympton.setId(cursor.getInt(0));
            sympton.setAnswer(cursor.getString(1));
            sympton.setDate(cursor.getString(2));
            sympton.setIdUser(cursor.getInt(3));
            data.add(sympton);
            cursor.moveToNext();
        }
        cursor.close();

        return data;
    }

    public void delete(Sympton sympton) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        getWritableDatabase().execSQL(sql, new String[]{String.valueOf(sympton.getId())});
    }

    public void update(Sympton sympton) {
        String sql = "UPDATE " + TABLE_NAME + " SET answer = ?, date = ? WHERE id = ?";
        getWritableDatabase().execSQL(sql, new String[] {sympton.getAnswer(), sympton.getDate(), String.valueOf(sympton.getId())});
    }

    public Optional<Sympton> fetchByUserAndDate(User user, String date) {
        Optional<Sympton> data = Optional.empty();

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id_user = ? AND date = ?";

        Cursor cursor = getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(user.getId()), date});
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            Sympton sympton = new Sympton();
            sympton.setId(cursor.getInt(0));
            sympton.setAnswer(cursor.getString(1));
            sympton.setDate(cursor.getString(2));
            sympton.setIdUser(cursor.getInt(3));

            cursor.moveToNext();

            data = Optional.of(sympton);
        }
        cursor.close();
        return data;
    }


}
