package com.anazta217.musicplayer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CreatePlaylistFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
           View inflate = inflater.inflate(R.layout.fragment_create_playlist, container, false);
           return inflate;
    }
}