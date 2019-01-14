package com.hello.assignment.auth.common.ui.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.hello.assignment.R;

public class BaseParentActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = BaseParentActivity.this;
    }

    public void showLoader(String message) {

        progressDialog = new ProgressDialog(context, R.style.AppCompatProgressDialogStyle);
        progressDialog.setCancelable(false);

        if (!TextUtils.isEmpty(message)) {
            progressDialog.setMessage(message);
        } else {
            progressDialog.setMessage(getString(R.string.please_wait));
        }

        if (!isFinishing()) {
            progressDialog.show();
        }
    }

    public void hideLoader() {

        if (progressDialog != null && progressDialog.isShowing() && !isFinishing()) {

            progressDialog.dismiss();

        }
    }

}
