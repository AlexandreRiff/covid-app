package com.example.covidapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapp.R;
import com.example.covidapp.model.User;
import com.example.covidapp.repository.RepositoryUser;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Optional;

public class RegisterUserActivity extends AppCompatActivity {

    private TextInputEditText name;
    private TextInputEditText email;
    private TextInputEditText password;
    private TextInputEditText repeatPassword;

    private TextInputEditText[] inputs;

    private RepositoryUser dbUser;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        name = findViewById(R.id.registerUserName);
        email = findViewById(R.id.registerUserEmail);
        password = findViewById(R.id.registerUserPassword);
        repeatPassword = findViewById(R.id.registerUserRepeatPassword);

        inputs = new TextInputEditText[] {name, email, password, repeatPassword};
    }

    public void saveUser(View view) {
        dbUser = new RepositoryUser(this);
        user = new User();

        Boolean inputsEmpty = false;
        for(int i = 0; i < inputs.length; i++) {
            if(inputs[i].getText().toString().isEmpty()) {
                inputsEmpty = true;
            }
        }

        if(inputsEmpty) {
            Toast.makeText(getApplicationContext(), "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();
        } else {
            Optional existingUser = dbUser.fetchByEmail(email.getText().toString());
            if(existingUser.isPresent()) {
                email.setText("");

                Toast.makeText(getApplicationContext(), "Email já cadastrado", Toast.LENGTH_SHORT).show();
            } else {
                if(password.getText().toString().equals(repeatPassword.getText().toString())) {
                    user.setName(name.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setPassword(password.getText().toString());

                    dbUser.insert(user);

                    Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    password.setText("");
                    repeatPassword.setText("");

                    Toast.makeText(getApplicationContext(), "As senhas não conferem!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}