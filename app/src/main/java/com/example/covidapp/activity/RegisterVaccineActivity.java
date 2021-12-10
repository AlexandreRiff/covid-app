package com.example.covidapp.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapp.R;
import com.example.covidapp.model.Vaccine;
import com.example.covidapp.repository.RepositoryVaccine;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class RegisterVaccineActivity extends AppCompatActivity {

    private RadioButton dose1;
    private RadioButton dose2;
    private RadioButton dose3;
    private AutoCompleteTextView type;
    private TextInputEditText date;

    private RepositoryVaccine dbVaccine;
    private Vaccine vaccine;

    private ArrayAdapter arrayAdapter;

    private String[] typeOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vaccine);

        dose1 = findViewById(R.id.registerVaccineDose1);
        dose2 = findViewById(R.id.registerVaccineDose2);
        dose3 = findViewById(R.id.registerVaccineDose3);
        type = findViewById(R.id.registerVaccineType);
        date = findViewById(R.id.registerVaccineDate);

        typeOptions = new String[]{"Selecione o tipo", "Pfizer (BioNtech)", "CoronaVac (Instituto Butantã)", "Janssen (Johnson & Johnson)", "Oxford/FioCruz (AstraZeneca)"};

        arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_item, typeOptions);

        type.setText(arrayAdapter.getItem(0).toString(), false);
        type.setAdapter(arrayAdapter);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog picker = new DatePickerDialog(RegisterVaccineActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year,
                                          int month, int dayOfMonth) {
                        month = month + 1;
                        String formattedDate = (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth) + "/" + (month < 10 ? "0" + month : month) + "/" + year;
                        date.setText(formattedDate);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        Bundle bundle = getIntent().getExtras();
        vaccine = new Vaccine();

        if((bundle != null) && (bundle.containsKey("VACCINE_TO_EDIT"))) {
            vaccine = (Vaccine) bundle.getSerializable("VACCINE_TO_EDIT");

            type.setText(vaccine.getType(), false);
            date.setText(vaccine.getDate());

            if(vaccine.getDose() == 1) {
                dose1.setChecked(true);
            } else if(vaccine.getDose() == 2) {
                dose2.setChecked(true);
            } else if(vaccine.getDose() == 3) {
                dose3.setChecked(true);
            }
        }
    }

    public void saveVaccine(View view) {

        dbVaccine = new RepositoryVaccine(this);

        Boolean inputsEmpty = false;
        int dose = 0;
        if(dose1.isChecked()) {
            dose = 1;
        } else if(dose2.isChecked()) {
            dose = 2;
        } else if(dose3.isChecked()) {
            dose = 3;
        } else {
            inputsEmpty = true;
        }

        if(type.getText().toString().isEmpty() || date.getText().toString().isEmpty()) {
            inputsEmpty = true;
        }

        if(inputsEmpty) {
            Toast.makeText(getApplicationContext(), "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();
        } else {
            vaccine.setDose(dose);
            vaccine.setType(type.getText().toString());
            vaccine.setDate(date.getText().toString());
            vaccine.setIdUser(LoginActivity.LoggedinUser.get().getId());

            if(vaccine.getId() != 0) {
                dbVaccine.update(vaccine);

                Toast.makeText(getApplicationContext(), "Vacina Atualizada!", Toast.LENGTH_SHORT).show();
            } else {
                dbVaccine.insert(vaccine);

                Toast.makeText(getApplicationContext(), "Vacina Adicionada!", Toast.LENGTH_SHORT).show();
            }

            setResult(RESULT_OK);
            finish();

        }
    }
}