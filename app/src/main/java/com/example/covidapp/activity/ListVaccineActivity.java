package com.example.covidapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapp.R;
import com.example.covidapp.adapter.VaccineAdapter;
import com.example.covidapp.model.Vaccine;
import com.example.covidapp.repository.RepositoryVaccine;

import java.util.List;

public class ListVaccineActivity extends AppCompatActivity {

    private ListView listView;
    private LinearLayout noResults;

    private RepositoryVaccine dbVaccine;
    private VaccineAdapter adapter;
    private List<Vaccine> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vaccine);

        listView = findViewById(R.id.listVaccine);
        noResults = findViewById(R.id.layoutNoResults);

        dbVaccine = new RepositoryVaccine(this);
        data = dbVaccine.fetchAll();
        adapter = new VaccineAdapter(this, data);

        listView.setEmptyView(noResults);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListVaccineActivity.this);
                builder.setTitle("Editar / Excluir");
                builder.setMessage("O que deseja fazer ?")
                        .setCancelable(true)
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dbVaccine.delete(data.get(position));
                                adapter.removeItem(position);

                                Toast.makeText(getApplicationContext(), "Excluído com sucesso!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Editar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                data = dbVaccine.fetchAll();

                                Intent intent = new Intent(ListVaccineActivity.this, RegisterVaccineActivity.class);
                                intent.putExtra("VACCINE_TO_EDIT", data.get(position));
                                startActivityForResult(intent, 0);
                            }
                        }).setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0) {
            if(resultCode == RESULT_OK) {
                this.data = dbVaccine.fetchAll();
                adapter.update(this.data);
            }
        }
    }

    public void openRegisterVaccineScreen(View view) {
        Intent intent = new Intent(this, RegisterVaccineActivity.class);
        startActivityForResult(intent, 0);
    }

}