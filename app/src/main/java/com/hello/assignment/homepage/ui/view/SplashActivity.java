package com.hello.assignment.homepage.ui.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;

import com.hello.assignment.R;
import com.hello.assignment.auth.common.ui.view.BaseParentActivity;
import com.hello.assignment.auth.ui.view.activity.LoginActivity;
import com.hello.assignment.databinding.ActivitySplashBinding;
import com.hello.assignment.uitility.Constants;

public class SplashActivity extends BaseParentActivity {

    private ActivitySplashBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = SplashActivity.this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                startActivity(new Intent(context, LoginActivity.class));

                finish();

            }
        }, Constants.SPLASH_TIME_OUT);

    }
}
