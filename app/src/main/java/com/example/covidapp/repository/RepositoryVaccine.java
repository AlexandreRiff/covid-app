package com.example.covidapp.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.example.covidapp.model.Vaccine;

import java.util.ArrayList;
import java.util.List;

public class RepositoryVaccine extends RepositoryHelper {

    protected static String TABLE_NAME = "vaccine";

    public RepositoryVaccine(@Nullable Context context) {
        super(context);
    }

    public void insert(Vaccine vaccine) {
        ContentValues values = new ContentValues();
        values.put("dose", vaccine.getDose());
        values.put("type", vaccine.getType());
        values.put("date", vaccine.getDate());
        values.put("id_user", vaccine.getIdUser());

        final long ret = getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    public List<Vaccine> fetchAll() {
        List<Vaccine> data = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            Vaccine vaccine = new Vaccine();
            vaccine.setId(cursor.getInt(0));
            vaccine.setDose(cursor.getInt(1));
            vaccine.setType(cursor.getString(2));
            vaccine.setDate(cursor.getString(3));
            vaccine.setIdUser(cursor.getInt(4));

            data.add(vaccine);
            cursor.moveToNext();
        }
        cursor.close();

        return data;
    }

    public void update(Vaccine vaccine) {
        String sql = "UPDATE " + TABLE_NAME + " SET dose = ?, type = ?, date = ? WHERE id = ?";
        getWritableDatabase().execSQL(sql, new String[] {String.valueOf(vaccine.getDose()), vaccine.getType(), vaccine.getDate(), String.valueOf(vaccine.getId())});
    }

    public void delete(Vaccine vaccine) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        getWritableDatabase().execSQL(sql, new String[]{String.valueOf(vaccine.getId())});
    }

}
