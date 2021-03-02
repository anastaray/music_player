package com.anazta217.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 15;
    private EditText emailInput;
    private EditText passwordInput;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.sign_in_fragment, container, false);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        firebaseAuth = FirebaseAuth.getInstance();

        emailInput = inflate.findViewById(R.id.emailInput);
        passwordInput = inflate.findViewById(R.id.passwordInput);

        inflate.findViewById(R.id.sign_in_button).setOnClickListener(this);
        inflate.findViewById(R.id.sign_up_button).setOnClickListener(this);
        inflate.findViewById(R.id.sign_in_firebase_button).setOnClickListener(this);

        return inflate;
    }

    private void loginFirebase(){
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(getActivity(), "Logged in", Toast.LENGTH_LONG).show();
                            updateUI(user);
                        }
                        else {
                            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();;
                        }
                    }
                });
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            updateUI(account);
        } catch (ApiException e) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
           Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
           handleSignInResult(task);
        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void updateUI(GoogleSignInAccount account){
        ((MainActivity)getActivity()).playlistsFragment(account.getEmail());
    }

    public void updateUI(FirebaseUser user) {
        ((MainActivity) getActivity()).playlistsFragment(user.getEmail());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.sign_up_button:
                ((MainActivity)getActivity()).openCreateUserFragment();
                break;
            case R.id.sign_in_firebase_button:
                loginFirebase();
                break;
        }
    }
}
