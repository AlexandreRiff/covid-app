package com.example.covidapp.fragment;

import static java.lang.String.format;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.covidapp.R;

import java.util.Locale;

public class SettingFragment extends PreferenceFragmentCompat implements TimePickerDialog.OnTimeSetListener {

    private Preference setTime;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        getPreference();
        configListener();
    }

    private void getPreference() {
        setTime = findPreference("set_time");
    }

    private void configListener() {
        if(setTime != null) {
            setTime.setOnPreferenceClickListener(preference -> {
                showTimeDialog(preference);
                return true;
            });
        }
    }

    private void showTimeDialog(Preference preference) {
        String value = preference.getSharedPreferences().getString("set_time", "08:00");
        String[] time = value.split(":");
        int hours = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);
        if(getFragmentManager() != null) {
            new TimePickerFragment(this, hours, minutes)
                    .show(getFragmentManager(), getString(R.string.tag_time_picker));
        }
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int h, int m) {
        String time = format(Locale.getDefault(), "%02d", h) + ":" + format(Locale.getDefault(), "%02d", m);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences.edit().putString("set_time", time).apply();
        setTime.setSummary(time);
    }

}
