package com.anazta217.musicplayer.ui.main.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import com.anazta217.musicplayer.R;
import com.anazta217.musicplayer.data.model.Playlist;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  navController = Navigation.findNavController(this, R.id.nav_host_fragment);

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

    public void playlistsFragment(String name) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PlaylistsFragment playlistsFragment = new PlaylistsFragment();
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

    public void openCreatePlaylistFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        CreatePlaylistFragment createPlaylistFragment = new CreatePlaylistFragment();
        fragmentTransaction.replace(R.id.mainContainer,createPlaylistFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void createResetPasswordFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ResetPasswordFragment resetPasswordFragment = new ResetPasswordFragment();
        fragmentTransaction.replace(R.id.mainContainer, resetPasswordFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void openPlaylistFragment(Playlist playlist){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PlaylistFragment playlistFragment = new PlaylistFragment(playlist);
        fragmentTransaction.replace(R.id.mainContainer,playlistFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}