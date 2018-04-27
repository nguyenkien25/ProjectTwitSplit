package com.example.kien.projecttwitsplit.ui.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.kien.projecttwitsplit.utils.Constants;
import com.example.kien.projecttwitsplit.R;
import com.example.kien.projecttwitsplit.ui.fragments.LoginFragment;
import com.example.kien.projecttwitsplit.ui.fragments.RegisterFragment;

public class LoginAndRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupContentUI();
    }

    private void setupContentUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        Intent intent = getIntent();
        if (intent != null) {
            String flag = intent.getStringExtra(Constants.FLAG_LOGIN);
            loadFragment(flag);
        }
    }

    public void loadFragment(String flag) {
        if (flag != null && !flag.isEmpty()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, getFragment(flag))
                    .addToBackStack(null)
                    .commit();
        }
    }

    private Fragment getFragment(String flag) {
        return flag.equals(Constants.LOGIN) ? new LoginFragment() :
                new RegisterFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
