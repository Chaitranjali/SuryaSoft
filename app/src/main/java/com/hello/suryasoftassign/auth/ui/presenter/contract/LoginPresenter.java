package com.hello.suryasoftassign.auth.ui.presenter.contract;

import com.hello.suryasoftassign.auth.common.ui.presenter.contract.Presenter;

public interface LoginPresenter extends Presenter<LoginMvpView> {
    void clear();
    void doLogin(String email);
}
