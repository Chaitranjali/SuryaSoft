package com.hello.assignment.networkcalls;


import com.hello.assignment.beans.dto.UserLoginResponse;
import com.hello.assignment.beans.model.UserLoginRequest;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HttpImpl {

    private final static String TAG = "HttpImpl";
    private static volatile HttpImpl sInstance;
    private static volatile ApiEndPoints sApiClient;


    public static HttpImpl getInstance() {
        if (sInstance == null) {
            synchronized (HttpImpl.class) {
                sInstance = new HttpImpl();
            }
        }
        return sInstance;
    }

    private ApiEndPoints getApiClient() {
        if (sApiClient == null) {
            synchronized (this) {
                sApiClient = ServiceFactory.createRetrofit2RxJavaService(ApiEndPoints.class);
            }
        }
        return sApiClient;
    }

    // User Sign In with email
    public void userSignInWithEmail(final Repository repository, final UserLoginRequest userLoginRequest) {
        getApiClient().getSignInWithEmailResponse("application/json", userLoginRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new rx.Observer<Response<UserLoginResponse>>() {
                    @Override
                    public void onCompleted() {
                        repository.onCompleted();
                    }

                    @Override
                    public void onError(Throwable throwable) {

                        repository.onError(throwable);
                    }

                    @Override
                    public void onNext(Response<UserLoginResponse> signInResponse) {
                        repository.onNext(signInResponse);
                    }
                });
    }


}
