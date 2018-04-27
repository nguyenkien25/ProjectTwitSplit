package com.example.kien.projecttwitsplit.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kien.projecttwitsplit.utils.AppController;
import com.example.kien.projecttwitsplit.utils.Constants;
import com.example.kien.projecttwitsplit.R;
import com.example.kien.projecttwitsplit.ui.activities.LoginAndRegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment {

    private static final String TAG = "login_fragment";


    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogin.setOnClickListener(v -> login());
        btnRegister.setOnClickListener(v -> ((LoginAndRegisterActivity) getActivity()).loadFragment(Constants.REGISTER));

        String email = AppController.getInstance().getPrefManager().getUsername().trim();
        String password = AppController.getInstance().getPrefManager().getPassword().trim();

        if (!email.equals("") || !email.isEmpty()) {
            if (!password.equals("") || !password.isEmpty()) {
                performFirebaseLogin(email, password);
            }
        }
    }

    private void login() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
            performFirebaseLogin(email, password);
        }
    }

    private void performFirebaseLogin(String email, String password) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    Log.d(TAG, "performFirebaseLogin:onComplete:" + task.isSuccessful());
                    if (task.isSuccessful()) {
                        updateFirebaseToken(task.getResult().getUser().getUid(), null);
                        AppController.getInstance().getPrefManager().storeUser(email, password, task.getResult().getUser().getUid());
                        ((LoginAndRegisterActivity) getActivity()).goToMain();
                    } else {
                        Toast.makeText(getActivity(), "Login failed!", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void updateFirebaseToken(String uid, String token) {
        FirebaseDatabase.getInstance()
                .getReference()
                .child(Constants.ARG_USERS)
                .child(uid)
                .child(Constants.ARG_FIREBASE_TOKEN)
                .setValue(token);
    }
}
