package com.hello.assignment.homepage.ui.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hello.assignment.R;
import com.hello.assignment.databinding.ActivityHomeBinding;

public class SSHomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = SSHomeActivity.this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
    }
}
