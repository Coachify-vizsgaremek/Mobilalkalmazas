package com.example.myapplication.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import com.example.myapplication.network.models.LoginRequest;
import com.example.myapplication.network.models.LoginResponse;
public interface ApiService {
    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest request);
}
