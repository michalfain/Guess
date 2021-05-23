package com.example.guess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class History extends AppCompatActivity {
    Button btnBack;
    Context context;
    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        context = this;
        recyclerView = findViewById(R.id.historyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        historyAdapter = new HistoryAdapter();
        recyclerView.setAdapter(historyAdapter);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
        });
    }
}