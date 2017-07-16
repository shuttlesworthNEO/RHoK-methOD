package com.siddhantchadha.rhok_method.data;

import java.util.List;

import com.google.gson.JsonObject;
import com.siddhantchadha.rhok_method.models.CreateResponse;
import com.siddhantchadha.rhok_method.models.FeedResponse;
import com.siddhantchadha.rhok_method.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.*;

public interface SOService {

    @Headers("Content-Type: application/json")
    @POST("/create/")
    Call<CreateResponse> getResponses(@Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST("/feed/")
    Call<FeedResponse> getFeedResponse(@Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST("/resolved/")
    Call<CreateResponse> getResolvedResponse(@Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST("/takeup/")
    Call<CreateResponse> getTakeupResponse(@Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST("/signup/")
    Call<CreateResponse> getSignupResponse(@Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST("/set/")
    Call<CreateResponse> getSetResponse(@Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST("/login/")
    Call<LoginResponse> getLoginResponse(@Body JsonObject body);
}