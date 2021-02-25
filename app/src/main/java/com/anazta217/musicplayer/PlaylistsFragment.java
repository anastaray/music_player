package com.anazta217.musicplayer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PlaylistsFragment extends Fragment {

    private String text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_playlists, container, false);
        TextView textView = inflate.findViewById(R.id.hello_text);
        textView.setText(text);
        return inflate;
    }

    public void setText(String text) {
        this.text = text;
    }
}