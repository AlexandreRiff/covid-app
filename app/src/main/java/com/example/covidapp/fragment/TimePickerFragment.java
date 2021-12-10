package com.example.covidapp.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.covidapp.R;

public class TimePickerFragment extends DialogFragment {

    private TimePickerDialog.OnTimeSetListener onTimeSetListener;
    private int hours;
    private int minutes;

    public TimePickerFragment(
            TimePickerDialog.OnTimeSetListener onTimeSetListener, int hours,
            int minutes) {
        this.onTimeSetListener = onTimeSetListener;
        this.hours = hours;
        this.minutes = minutes;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), R.style.dateTimePicker,
                onTimeSetListener, hours, minutes, DateFormat.is24HourFormat(getActivity()));
    }
}