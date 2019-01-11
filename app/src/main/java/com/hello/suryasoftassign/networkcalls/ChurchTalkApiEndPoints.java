package com.hello.suryasoftassign.networkcalls;


import com.hello.suryasoftassign.beans.dto.UserLoginResponse;
import com.hello.suryasoftassign.beans.model.UserLoginRequest;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

public interface ChurchTalkApiEndPoints {

    @POST("list")
    Observable<Response<UserLoginResponse>> getSignInWithEmailResponse(
            @Header("Content-Type") String contentType,
            @Body UserLoginRequest body);
}
