package com.example.covidapp.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.example.covidapp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositoryUser extends RepositoryHelper {

    protected static String TABLE_NAME = "user";

    public RepositoryUser(@Nullable Context context) {
        super(context);
    }

    public void insert(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());

        final long ret = getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    public List<User> fetchAll() {
        List<User> data = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            User user = new User();
            user.setName(cursor.getString(0));
            user.setEmail(cursor.getString(1));
            user.setPassword(cursor.getString(2));

            data.add(user);

            cursor.moveToNext();
        }
        cursor.close();

        return data;
    }

    public Optional<User> fetchByEmail(String email) {
        Optional<User> data = Optional.empty();

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE email = ?";

        Cursor cursor = getReadableDatabase().rawQuery(sql, new String[]{email});
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));

            cursor.moveToNext();

            data = Optional.of(user);
        }
        cursor.close();
        return data;
    }

}
