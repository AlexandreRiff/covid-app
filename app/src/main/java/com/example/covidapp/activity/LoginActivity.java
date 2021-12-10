package com.example.covidapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.covidapp.R;
import com.example.covidapp.model.User;
import com.example.covidapp.repository.RepositoryUser;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    public static Optional<User> LoggedinUser;
    private TextInputEditText email;
    private TextInputEditText password;
    private RepositoryUser dbUser;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);

        isLoggedIn();

    }

    public void signIn(View view) {
        dbUser = new RepositoryUser(this);
        Optional<User> user = dbUser.fetchByEmail(email.getText().toString());

        if(user.isPresent() && user.get().getPassword().equals(password.getText().toString())) {
            LoggedinUser = user;

            Set<String> set = new HashSet<String>();

            set.add(Integer.toString(LoggedinUser.get().getId()));
            set.add(LoggedinUser.get().getName());
            set.add(LoggedinUser.get().getEmail());

            sharedPreferences.edit()
                    .putStringSet("LoggedinUser", set)
                    .apply();
            sharedPreferences.edit()
                    .putBoolean("isLoggedIn", true)
                    .apply();

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

        } else {
            email.setText("");
            password.setText("");

            Toast.makeText(getApplicationContext(), "Usuário inválido!", Toast.LENGTH_SHORT).show();
        }
    }

    public void openRegisterUserScreen(View view) {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }

    public void isLoggedIn() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if(isLoggedIn) {
            Set<String> set = sharedPreferences.getStringSet("LoggedinUser", null);

            String[] array = set.toArray(new String[set.size()]);

            User user = new User();
            user.setId(Integer.parseInt(array[1]));
            user.setName(array[0]);
            user.setEmail(array[2]);

            LoggedinUser = Optional.of(user);
            
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

}