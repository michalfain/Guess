package com.example.guess;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {
   public static List<Player> playersList;
    EditText etName, etEmail;
    String name, email;
    Button btnSubmit;
    Context context;
    SharedPreferences sharedPreferences;
    static final String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+↵\n" +
            ")*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        btnSubmit = findViewById(R.id.btnSubmit);
        sharedPreferences = getSharedPreferences("NAME_EMAIL", MODE_PRIVATE);
        context = this;
            playersList = new ArrayList<>();
        btnSubmit.setOnClickListener(v -> {
            if(checkName() && checkEmail()){
                name = etName.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                Player player = new Player(name, email);
                playersList.add(player);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", name);
                editor.putString("email", email);
                editor.commit();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(context, "Please fill in your name and email", Toast.LENGTH_LONG).show();
            }
        });
    }
    public boolean checkName(){
        boolean isChecked;
        if (etName.getText().toString().length() < 2){
            isChecked = false;
        }else {
            isChecked = true;
        }
        return isChecked;
    }
    public boolean checkEmail(){
        boolean isChecked;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(etEmail.getText().toString());
        if (matcher.matches()){
            isChecked = false;
        }else {
            isChecked = true;
        }
        return isChecked;
    }
}