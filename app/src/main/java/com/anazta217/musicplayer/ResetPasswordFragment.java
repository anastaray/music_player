package com.anazta217.musicplayer;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordFragment extends Fragment implements View.OnClickListener {

    private EditText emailInput;
    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_reset_password, container, false);

        emailInput = inflate.findViewById(R.id.reset_email);

        inflate.findViewById(R.id.reset_password_btn).setOnClickListener(this);
        inflate.findViewById(R.id.back_btn).setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        return inflate;
    }

    public void resetPassword(){
        String email = emailInput.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "Enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Check email to reset your password", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Fail to send reset password email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void previousScreen() { ((MainActivity)getActivity()).openSignInFragment(); }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.reset_password_btn):
                resetPassword();
                break;
            case (R.id.back_btn):
                previousScreen();
                break;
        }
    }
}


