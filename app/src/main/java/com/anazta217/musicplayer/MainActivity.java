package com.anazta217.musicplayer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser currentUser =  FirebaseAuth.getInstance().getCurrentUser();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account == null || currentUser == null) {
            openSignInFragment();
        } else {
            playlistsFragment(account.getEmail());
        }
    }

    public void openSignInFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        SignInFragment signInFragment = new SignInFragment();
        fragmentTransaction.replace(R.id.mainContainer, signInFragment);
        fragmentTransaction.commit();
    }

    public void openFirstFragment(GoogleSignInAccount account) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PlaylistsFragment playlistsFragment = new PlaylistsFragment();
        playlistsFragment.setText(account.getDisplayName() + " the best");
        fragmentTransaction.replace(R.id.mainContainer, playlistsFragment);
        fragmentTransaction.commit();
    }

    public void openCreateUserFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        CreateUserFragment createUserFragment = new CreateUserFragment();
        fragmentTransaction.replace(R.id.mainContainer, createUserFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}