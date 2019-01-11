package com.hello.suryasoftassign.homepage.ui.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.hello.suryasoftassign.R;
import com.hello.suryasoftassign.auth.common.ui.view.BaseParentActivity;
import com.hello.suryasoftassign.auth.ui.view.activity.LoginActivity;
import com.hello.suryasoftassign.databinding.ActivitySplashBinding;
import com.hello.suryasoftassign.uitility.AppPreferences;
import com.hello.suryasoftassign.uitility.Constants;

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
