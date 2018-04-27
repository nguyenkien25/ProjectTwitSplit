package com.example.kien.projecttwitsplit.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kien.projecttwitsplit.utils.AppController;
import com.example.kien.projecttwitsplit.utils.Constants;
import com.example.kien.projecttwitsplit.R;


public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        setupListeners();
    }

    private void setupListeners() {
        findViewById(R.id.btnLogin).setOnClickListener(v -> showLoginActivity(Constants.LOGIN));
        findViewById(R.id.btnRegister).setOnClickListener(v -> showLoginActivity(Constants.REGISTER));

        if (!AppController.getInstance().getPrefManager().getUsername().trim().equals("")) {
            showLoginActivity(Constants.LOGIN);
        }
    }

    private void showLoginActivity(String flag) {
        Intent intent = new Intent(this, LoginAndRegisterActivity.class);
        intent.putExtra(Constants.FLAG_LOGIN, flag);
        startActivity(intent);
    }
}
