package com.hello.assignment.auth.ui.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.hello.assignment.R;
import com.hello.assignment.auth.common.ui.view.BaseParentActivity;
import com.hello.assignment.auth.ui.presenter.LoginPresenterImpl;
import com.hello.assignment.auth.ui.presenter.contract.LoginMvpView;
import com.hello.assignment.auth.ui.presenter.contract.LoginPresenter;
import com.hello.assignment.beans.dto.UserLoginResponse;
import com.hello.assignment.beans.model.UserLoginRequest;
import com.hello.assignment.databinding.ActivityLogiinBinding;
import com.hello.assignment.homepage.ui.view.SSHomeActivity;
import com.hello.assignment.networkcalls.HttpImpl;
import com.hello.assignment.networkcalls.Repository;
import com.hello.assignment.uitility.CommonMethods;
import com.hello.assignment.uitility.Constants;

import retrofit2.Response;

public class LoginActivity extends BaseParentActivity implements LoginMvpView, View.OnClickListener {

    private ActivityLogiinBinding binding;
    private LoginPresenter loginPresenter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = LoginActivity.this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_logiin);

        // add text watcher
        binding.inputEmail.addTextChangedListener(new MyTextWatcher(binding.inputEmail));

        //set listener
        binding.btnSignIn.setOnClickListener(this);

        //init
        loginPresenter = new LoginPresenterImpl(this);

    }

    @Override
    public void onClearText() {
        binding.inputEmail.setText("");
    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        hideLoader();
        binding.btnSignIn.setEnabled(true);
        if (result){
            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"Login Fail, code = " + code,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignIn:
                if (CommonMethods.isValidEmail(binding.inputEmail.getText().toString())) {

                   String emailId = binding.inputEmail.getText().toString().trim().toLowerCase();

                    binding.inputLayoutEmail.setErrorEnabled(false);

                    if (CommonMethods.isNetworkAvailable(context)) {

                        CommonMethods.hideKeyboard(LoginActivity.this);
                        showLoader("");
                      //  binding.btnSignIn.setEnabled(false);
                      //  loginPresenter.doLogin(emailId);
                        signInUser(emailId,binding.inputPassword.getText().toString());
                    } else {
                        CommonMethods.showSnackbar(binding.scrollView, getString(R.string.no_internet_msg));
                    }

                } else {

                    binding.inputEmail.requestFocus();
                    binding.inputLayoutEmail.setError(getString(R.string.invalid_email_msg));

                }

                break;
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
          if (view.getId() == R.id.inputEmail) {
                if (binding.inputEmail.getText().toString().startsWith(" ")) {
                    binding.inputEmail.setText(binding.inputEmail.getText().toString().trim());
                }
            }
        }

        public void afterTextChanged(Editable editable) {

            switch (view.getId()) {

                case R.id.inputEmail:
                    break;
            }
        }

    }



    private void signInUser(String emailId,String pass) {

        final UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmailId(emailId);
       // userLoginRequest.setPassword(pass);
       // userLoginRequest.setType("email");


        HttpImpl.getInstance().userSignInWithEmail(new Repository<Response<UserLoginResponse>>() {
            @Override
            public void onCompleted() {
                hideLoader();
            }

            @Override
            public void onError(Throwable e) {
                hideLoader();
                CommonMethods.showSnackbar(binding.scrollView, getString(R.string.error_message));
            }

            @Override
            public void onNext(Response<UserLoginResponse> userLoginResponse) {
                hideLoader();
                try {
                    if (!userLoginResponse.equals("")) {
                        switch (userLoginResponse.code()) {
                            case Constants.OK:
                                UserLoginResponse apiResponse = userLoginResponse.body();
                                Intent intent = new Intent(context, SSHomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                               /* if (apiResponse.getStatus()) {
                                    UserLoginData userLoginData = apiResponse.getData();


                                        AppPreferences.putLoginObject(AppPreferences.Key.LOGIN_DATA, userLoginData);

                                        Intent intent = new Intent(context, SSHomeActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();



                                } else {

                                    ErrorResponse errorResponse = apiResponse.getError();
                                    if (errorResponse != null && !TextUtils.isEmpty(errorResponse.getMessage())) {
                                        CommonMethods.showSnackbar(binding.scrollView, errorResponse.getMessage());

                                    } else {
                                    }
                                }*/
                                break;

                            case Constants.INTERNAL_SERVER_ERROR:
                                hideLoader();
                                CommonMethods.showToast(context, getString(R.string.error_message));
                                break;

                            default:
                                hideLoader();
                                break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, userLoginRequest);

    }
}
