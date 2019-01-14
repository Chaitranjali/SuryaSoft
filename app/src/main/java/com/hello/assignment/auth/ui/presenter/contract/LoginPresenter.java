package com.hello.assignment.auth.ui.presenter.contract;

import com.hello.assignment.auth.common.ui.presenter.contract.Presenter;

public interface LoginPresenter extends Presenter<LoginMvpView> {
    void clear();
    void doLogin(String email);
}
