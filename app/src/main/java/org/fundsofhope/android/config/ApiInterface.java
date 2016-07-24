package org.fundsofhope.android.config;

import org.fundsofhope.android.model.SignupStatus;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by anip on 23/07/16.
 */
public interface ApiInterface {
    //SIGNUP
    @Headers("Content-Type: application/json")
    @POST("/signup/")
    void signup(
            @Query("name") String name,
            @Query("phoneNo") String phoneNo,
            @Query("email") String email,
            Callback<SignupStatus> callback);
}