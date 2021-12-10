package com.example.covidapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapp.R;
import com.example.covidapp.model.Sympton;
import com.example.covidapp.repository.RepositorySympton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterSymptonActivity extends AppCompatActivity {

    CheckBox fever;
    CheckBox cough;
    CheckBox fatigue;
    CheckBox difficultyBreathing;
    CheckBox soreThroat;
    CheckBox headache;
    CheckBox diarrhea;
    CheckBox lossOfSmell;
    CheckBox lossOfTaste;
    CheckBox noSymptoms;

    CheckBox[] inputs;

    RepositorySympton dbSympton;
    Sympton sympton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sympton);

        fever = findViewById(R.id.registerSymptonFever);
        cough = findViewById(R.id.registerSymptonCough);
        fatigue = findViewById(R.id.registerSymptonFatigue);
        difficultyBreathing = findViewById(R.id.registerSymptonDifficultyBreathing);
        soreThroat = findViewById(R.id.registerSymptonSoreThroat);
        headache = findViewById(R.id.registerSymptonHeadache);
        diarrhea = findViewById(R.id.registerSymptonDiarrhea);
        lossOfSmell = findViewById(R.id.registerSymptonLossOfSmell);
        lossOfTaste = findViewById(R.id.registerSymptonLossOfTaste);
        noSymptoms = findViewById(R.id.registerSymptonNoSymptoms);

        inputs = new CheckBox[]{fever, cough, fatigue, difficultyBreathing, soreThroat, headache, diarrhea, lossOfSmell, lossOfTaste, noSymptoms};

    }

    public void saveSympton(View view) {
        dbSympton = new RepositorySympton(this);
        sympton = new Sympton();

        String answer = "";
        for (int i = 0; i < inputs.length; i++) {
            if(inputs[i].isChecked()) {
                answer += inputs[i].getText().toString() + ", ";
            }
        }
        answer = answer.substring(0, answer.length() - 2);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String formattedDate = simpleDateFormat.format(date);

        sympton.setAnswer(answer);
        sympton.setDate(formattedDate);
        sympton.setIdUser(LoginActivity.LoggedinUser.get().getId());

        dbSympton.insert(sympton);

        Toast.makeText(getApplicationContext(), "Enviado com sucesso!", Toast.LENGTH_SHORT).show();

        finish();
    }
}