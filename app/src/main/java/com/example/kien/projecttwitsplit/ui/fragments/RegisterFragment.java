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
import com.example.kien.projecttwitsplit.R;
import com.example.kien.projecttwitsplit.pojos.User;
import com.example.kien.projecttwitsplit.ui.activities.LoginAndRegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterFragment extends Fragment {

    private static final String TAG = "register_fragment";


    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnRegister.setOnClickListener(v -> register());
    }

    private void register() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
            performFirebaseRegistration(email, password);
        }
    }

    private void performFirebaseRegistration(String email, String password) {
        Log.d(TAG, "performFirebaseRegistration: vao nek");
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    Log.e(TAG, "performFirebaseRegistration:onComplete:" + task.isSuccessful());
                    if (!task.isSuccessful()) {
                        Toast.makeText(getActivity(), "Register Failed!", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d(TAG, "performFirebaseRegistration: ok");
                        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                        User user = new User(task.getResult().getUser().getUid(), email);
                        database.child("users").push().setValue(user);
                        AppController.getInstance().getPrefManager().storeUser(email, password, task.getResult().getUser().getUid());
                        ((LoginAndRegisterActivity) getActivity()).goToMain();
                    }
                });
    }
}
