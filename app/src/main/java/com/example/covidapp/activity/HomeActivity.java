package com.example.covidapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapp.R;
import com.example.covidapp.model.Sympton;
import com.example.covidapp.repository.RepositorySympton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class HomeActivity extends AppCompatActivity {

    private RepositorySympton dbSympton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void openSymptonScreen(View view) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String formattedDate = simpleDateFormat.format(date);

        dbSympton = new RepositorySympton(this);
        Optional<Sympton> sympton = dbSympton.fetchByUserAndDate(LoginActivity.LoggedinUser.get(), formattedDate);

        if(sympton.isPresent()) {
            Toast.makeText(getApplicationContext(), "Monitoração diária já realizada!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, RegisterSymptonActivity.class);
            startActivity(intent);
        }
    }

    public void openVaccineScreen(View view) {
        Intent intent = new Intent(this, ListVaccineActivity.class);
        startActivity(intent);
    }

    public void openHistoryScreen(View view) {
        Intent intent = new Intent(this, ListSymptonActivity.class);
        startActivity(intent);
    }

    public void openPreferenceScreen(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
}