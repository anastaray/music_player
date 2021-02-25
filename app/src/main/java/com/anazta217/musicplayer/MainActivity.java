package com.anazta217.musicplayer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account == null) {
            openSignInFragment();
        } else {
            openFirstFragment(account);
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
}