package com.anazta217.musicplayer.ui.main.view;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.anazta217.musicplayer.R;
import com.anazta217.musicplayer.data.model.Playlist;
import com.anazta217.musicplayer.ui.main.adapter.SongAdapter;
import com.anazta217.musicplayer.utils.PermissionUtil;

import java.io.File;

public class PlaylistFragment extends Fragment implements View.OnClickListener {

    private Playlist playlist;

    private ImageView playlistImageView;
    private TextView playlistNameTextView;
    private RecyclerView songListView;

    public PlaylistFragment(){

    }

    public PlaylistFragment(Playlist playlist){
        this.playlist = playlist;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_show_playlist, container, false);

        playlistImageView = inflate.findViewById(R.id.imagePlaylistView);
        playlistNameTextView = inflate.findViewById(R.id.playlistName);
        songListView = inflate.findViewById(R.id.songs_list_view_playlist);

        updateUI();

        return inflate;
    }

    private void updateUI(){
        PermissionUtil.checkPermission(getActivity());
        File imageFile = new File(playlist.getImagePath());
        if (imageFile.exists()) {
            playlistImageView.setImageURI(Uri.fromFile(imageFile));
        }

        playlistNameTextView.setText(playlist.getPlaylistName());

        songListView.setAdapter(new SongAdapter(getContext(), playlist.getSongs()));
    }

    @Override
    public void onClick(View v) {

    }
}
