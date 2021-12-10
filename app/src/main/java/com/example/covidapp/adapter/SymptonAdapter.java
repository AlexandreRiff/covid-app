package com.example.covidapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.covidapp.R;
import com.example.covidapp.model.Sympton;

import java.util.List;

public class SymptonAdapter extends BaseAdapter {

    private Context context;
    private List<Sympton> data;
    private Sympton sympton;

    public SymptonAdapter(Context context, List<Sympton> data) {
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

    public void removeItem(int position) {
        this.data.remove(position);

        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        sympton = data.get(i);

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.sympton_list_row, null);
        }

        TextView date = (TextView) view.findViewById(R.id.ListSymptomDate);
        TextView symptom = (TextView) view.findViewById(R.id.ListSymptomSymptons);

        date.setText(sympton.getDate());
        symptom.setText("Sintomas: " + sympton.getAnswer());

        return view;
    }

    public void update(List<Sympton> sympton) {
        this.data.clear();
        this.data = sympton;

        notifyDataSetChanged();
    }

}
