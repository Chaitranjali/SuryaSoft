package com.hello.suryasoftassign.networkcalls;


import com.hello.suryasoftassign.beans.dto.UserLoginResponse;
import com.hello.suryasoftassign.beans.model.UserLoginRequest;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HttpImpl {

    private final static String TAG = "HttpImpl";
    private static volatile HttpImpl sInstance;
    private static volatile ChurchTalkApiEndPoints sApiClient;


    public static HttpImpl getInstance() {
        if (sInstance == null) {
            synchronized (HttpImpl.class) {
                sInstance = new HttpImpl();
            }
        }
        return sInstance;
    }

    private ChurchTalkApiEndPoints getApiClient() {
        if (sApiClient == null) {
            synchronized (this) {
                sApiClient = ChurchTalkServiceFactory.createRetrofit2RxJavaService(ChurchTalkApiEndPoints.class);
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
