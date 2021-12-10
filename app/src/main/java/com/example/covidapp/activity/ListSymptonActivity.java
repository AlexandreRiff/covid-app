package com.example.covidapp.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapp.R;
import com.example.covidapp.adapter.SymptonAdapter;
import com.example.covidapp.model.Sympton;
import com.example.covidapp.repository.RepositorySympton;

import java.util.List;

public class ListSymptonActivity extends AppCompatActivity {

    private ListView listView;
    private LinearLayout noResults;

    private RepositorySympton dbSympton;
    private SymptonAdapter adapter;
    private List<Sympton> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sympton);

        listView = findViewById(R.id.listSymptom);
        noResults = findViewById(R.id.layoutNoResults);

        dbSympton = new RepositorySympton(this);
        data = dbSympton.fetchAll();
        adapter = new SymptonAdapter(this, data);

        listView.setEmptyView(noResults);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListSymptonActivity.this);
                builder.setTitle("Excluir");
                builder.setMessage("Deseja excluir?")
                        .setCancelable(true)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dbSympton.delete(data.get(position));
                                adapter.removeItem(position);

                                Toast.makeText(getApplicationContext(), "Excluído com sucesso!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}