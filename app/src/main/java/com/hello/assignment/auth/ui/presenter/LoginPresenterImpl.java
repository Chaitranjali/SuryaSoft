package com.hello.assignment.auth.ui.presenter;

import android.os.Handler;
import android.os.Looper;

import com.hello.assignment.auth.common.ui.presenter.BasePresenter;
import com.hello.assignment.auth.ui.presenter.contract.LoginMvpView;
import com.hello.assignment.auth.ui.presenter.contract.LoginPresenter;
import com.hello.assignment.auth.ui.view.model.IUser;
import com.hello.assignment.auth.ui.view.model.UserModel;

public class LoginPresenterImpl extends BasePresenter<LoginMvpView> implements LoginPresenter {

    LoginMvpView iLoginView;
    IUser user;
    Handler handler;

    public LoginPresenterImpl(LoginMvpView iLoginView) {
        this.iLoginView = iLoginView;
        initUser();
        handler = new Handler(Looper.getMainLooper());
    }

    private void initUser() {
        user = new UserModel("mvp");

    }

    @Override
    public void clear() {
        iLoginView.onClearText();

    }

    @Override
    public void doLogin(String email) {
        Boolean isLoginSuccess = true;
        final int code = user.checkUserValidity(email);
        if (code!=0) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iLoginView.onLoginResult(result, code);
            }
        }, 5000);
    }



}
