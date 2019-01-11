package com.hello.suryasoftassign.auth.ui.presenter.contract;

import com.hello.suryasoftassign.auth.common.ui.presenter.contract.MvpView;

public interface LoginMvpView extends MvpView {
    public void onClearText();
    public void onLoginResult(Boolean result, int code);
}
