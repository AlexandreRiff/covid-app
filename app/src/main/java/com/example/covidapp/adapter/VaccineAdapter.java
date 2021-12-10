package com.example.covidapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.covidapp.R;
import com.example.covidapp.model.Vaccine;

import java.util.List;

public class VaccineAdapter extends BaseAdapter {

    private Vaccine vaccine;
    private List<Vaccine> data;

    private Context context;

    public VaccineAdapter(Context context, List<Vaccine> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        vaccine = data.get(i);

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.vaccine_list_row, null);
        }

        TextView dose = (TextView) view.findViewById(R.id.ListVaccineDose);
        TextView type = (TextView) view.findViewById(R.id.ListVaccineType);
        TextView date = (TextView) view.findViewById(R.id.ListVaccineDate);

        dose.setText("Dose" + Integer.toString(vaccine.getDose()));
        type.setText(vaccine.getType());
        date.setText(vaccine.getDate());

        return view;
    }

    public void removeItem(int position) {
        this.data.remove(position);

        notifyDataSetChanged();
    }

    public void update(List<Vaccine> vaccine) {
        this.data.clear();
        this.data = vaccine;

        notifyDataSetChanged();
    }

}
