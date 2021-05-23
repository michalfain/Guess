package com.example.guess;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.guess.Registration.playersList;

public class MainActivity extends AppCompatActivity implements TextWatcher{
    final static String TAG = "LOG TAG";
    public static List <Player> playersListAttempts;
    List<Attempt> attemptList = new ArrayList<>();
    RecyclerView recyclerView;
    GuessAdapter guessAdapter;
    String higher = "higher";
    String lower = "lower";
    String correct = "correct!";
    Random random;
    int randomGuess, convertGuess;
    int guessCounts = 1;
    EditText etGuess;
    String score;
    TextView attemptNo, attemptScore;
    Button btnHistory, btnLogout;
    Context context;
    SharedPreferences sharedPreferences;
    boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("NAME_EMAIL", MODE_PRIVATE);
        if(sharedPreferences.getString("name", "") == null){
            Intent intent = new Intent(context, Registration.class);
            startActivity(intent);
        }
        context = this;
        random = new Random();
        randomGuess = random.nextInt(10) +1;
        recyclerView = findViewById(R.id.attemptList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(guessAdapter);
        etGuess = findViewById(R.id.etGuessGuess);
        attemptNo = findViewById(R.id.guessAttemptNo);
        attemptNo.setText(String.valueOf(guessCounts));
        attemptScore = findViewById(R.id.tvGuessScore);
        attemptScore.setVisibility(View.INVISIBLE);
        etGuess.addTextChangedListener(this);
        guessAdapter = new GuessAdapter(attemptList, context);
        playersListAttempts = PreferencesList.getPrefsList(context);
        if(playersListAttempts == null)
            playersListAttempts = new ArrayList<>();
        btnHistory = findViewById(R.id.btnHistory);
        btnLogout = findViewById(R.id.btnLogout);
        btnHistory.setOnClickListener(v -> {
            Intent intent = new Intent(context, History.class);
            startActivity(intent);
        });
        btnLogout.setOnClickListener(v -> {
            sharedPreferences.edit().clear().commit();
               Intent intent = new Intent(context, Registration.class);
               startActivity(intent);

        });
    }

    public String getScore(int guess, int random){
        if(guess < random){
            score = higher;
        }else  if(guess > random){
            score = lower;
        }else {
            score = correct;
            gameOver = true;
        }
        return score;
    }
    public void playGame(){
        Attempt attempt;
        String newScore = getScore(convertGuess, randomGuess);
        attempt = new Attempt(String.valueOf(guessCounts), etGuess.getText().toString(), newScore);
        attemptList.add(attempt);
        if(score == correct){
            gameOver = true;
            saveGame(createRequest());
           updateHistory();
        }
        guessCounts++;
    }
    public void updateHistory(){
        if(sharedPreferences.getString("name", "") == null){
            Player player = new Player(playersList.get(playersList.size() - 1).name, playersList.get(playersList.size() - 1).email, guessCounts);
            playersListAttempts.add(player);
        }else {
            Player player = new Player(sharedPreferences.getString("name", ""), sharedPreferences.getString("email", ""), guessCounts);
            playersListAttempts.add(player);
        }
            attemptNo.setVisibility(View.INVISIBLE);
            attemptScore.setVisibility(View.INVISIBLE);
   }
    public PlayerRequest createRequest(){
        int [] attemptsArray = {guessCounts};
        PlayerRequest playerRequest = new PlayerRequest();
        if(sharedPreferences.getString("name", "") == null){
            playerRequest.setName(playersList.get(playersList.size() - 1).name);
            playerRequest.setEmail(playersList.get(playersList.size() - 1).email);
        }else {
            playerRequest.setName(sharedPreferences.getString("name", ""));
            playerRequest.setEmail(sharedPreferences.getString("email", ""));
        }
        playerRequest.setAttempts(attemptsArray);
        playerRequest.getTestId();
        return playerRequest;
    }
   public void saveGame(PlayerRequest playerRequest){
        Call<PlayerResponse> playerResponseCall = ApiClient.getUserService().saveGame(playerRequest);
        playerResponseCall.enqueue(new Callback<PlayerResponse>() {
            @Override
            public void onResponse(Call<PlayerResponse> call, Response<PlayerResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "GAME SAVED!!", Toast.LENGTH_LONG).show();
                    PreferencesList.savePrefsList(getApplicationContext(), playersListAttempts);
                    Log.d(TAG, "SUCCESS");
                }else {
                    Toast.makeText(context, "FAILED TO SAVE GAME", Toast.LENGTH_LONG).show();
                    Log.d(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<PlayerResponse> call, Throwable t) {
                Toast.makeText(context, "ERROR!!!!!!!! " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
   }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        try {
            convertGuess = Integer.parseInt(s.toString());
            playGame();
            attemptNo.setText(String.valueOf(guessCounts));
        }
        catch (NumberFormatException e){
        }
    }
}