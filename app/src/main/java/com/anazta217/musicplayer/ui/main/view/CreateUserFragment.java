package com.anazta217.musicplayer.ui.main.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anazta217.musicplayer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class CreateUserFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private EditText createEmailInput;
    private EditText createPasswordInput;
    private EditText confirmPasswordInput;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_create_user, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        createEmailInput = inflate.findViewById(R.id.createEmailInput);
        createPasswordInput = inflate.findViewById(R.id.createPasswordInput);
        confirmPasswordInput = inflate.findViewById(R.id.confirmPasswordInput);

        Button submitButton = inflate.findViewById(R.id.sign_up_btn);
        submitButton.setOnClickListener(this);

        inflate.findViewById(R.id.back_btn).setOnClickListener(this);

        return inflate;
    }

    private void createUser() {
        String email = createEmailInput.getText().toString();
        String password = createPasswordInput.getText().toString();
        String confirmPassword = confirmPasswordInput.getText().toString();

        if (!password.equals(confirmPassword)){
            Toast.makeText(getContext(), R.string.password_not_equal_message,Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(getActivity(), "logged in", Toast.LENGTH_LONG).show();
                            updateUI(user);
                        } else {
                            Toast.makeText(getActivity(), "Registration failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void updateUI(FirebaseUser user) {
        ((MainActivity) getActivity()).playlistsFragment(user.getEmail());
    }

    public void previousScreen(){
        ((MainActivity)getActivity()).openSignInFragment();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.back_btn) :
                previousScreen();
                break;
            case (R.id.sign_up_btn) :
                createUser();
                break;
        }

    }
}
