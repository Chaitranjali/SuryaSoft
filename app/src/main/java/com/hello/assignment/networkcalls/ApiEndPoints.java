package com.hello.assignment.networkcalls;


import com.hello.assignment.beans.dto.UserLoginResponse;
import com.hello.assignment.beans.model.UserLoginRequest;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiEndPoints {

   /* @POST("auth/login")
    Observable<Response<UserLoginResponse>> getSignInWithEmailResponse(
            @Header("Content-Type") String contentType,
            @Body UserLoginRequest body);*/

    @POST("list")
    Observable<Response<UserLoginResponse>> getSignInWithEmailResponse(
            @Header("Content-Type") String contentType,
            @Body UserLoginRequest body);
}
