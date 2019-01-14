package com.hello.assignment.auth.ui.presenter.contract;

import com.hello.assignment.auth.common.ui.presenter.contract.MvpView;

public interface LoginMvpView extends MvpView {
    public void onClearText();
    public void onLoginResult(Boolean result, int code);
}
