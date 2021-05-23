package com.example.guess;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("games/")
    Call<PlayerResponse> saveGame(@Body PlayerRequest playerRequest);
}
