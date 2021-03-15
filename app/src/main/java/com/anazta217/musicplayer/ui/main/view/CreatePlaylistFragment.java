package com.anazta217.musicplayer.ui.main.view;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.anazta217.musicplayer.R;
import com.anazta217.musicplayer.data.model.Playlist;
import com.anazta217.musicplayer.data.model.Song;
import com.anazta217.musicplayer.ui.main.adapter.SongAdapter;
import com.anazta217.musicplayer.utils.Constants;
import com.anazta217.musicplayer.utils.PermissionUtil;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.util.ArrayList;

public class CreatePlaylistFragment extends Fragment implements View.OnClickListener {

    private EditText enterPlaylistName;
    private ImageView playlistImageView;
    private RecyclerView songListView;
    private Playlist playlist;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_create_playlist, container, false);

        enterPlaylistName = inflate.findViewById(R.id.input_playlist_name);

        playlist = new Playlist();

        inflate.findViewById(R.id.add_song_btn).setOnClickListener(this);
        playlistImageView = inflate.findViewById(R.id.add_playlist_image);
        playlistImageView.setOnClickListener(this);
        inflate.findViewById(R.id.save_playlist_btn).setOnClickListener(this);

        songListView = inflate.findViewById(R.id.songs_list_view);

        return inflate;
    }

    private void savePlaylist() {
        String title = enterPlaylistName.getText().toString();
        if (title.isEmpty()){
            Toast.makeText(getContext(), R.string.empty_title_error_message, Toast.LENGTH_LONG).show();
            return;
        }
        playlist.setPlaylistName(title);
        savePlaylistToDB();
    }

    private void savePlaylistToDB(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(Constants.PLAYLIST_COLLECTION_NAME).add(playlist);

    }

    private void loadImage() {
        Intent chooseFile = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        chooseFile = Intent.createChooser(chooseFile, getString(R.string.add_image_intent_title));
        startActivityForResult(chooseFile, Constants.CHOOSE_IMAGE_REQUEST_CODE);
    }

    private void addImagePath(Intent intent) {
        Uri selectedImageUri = intent.getData();
        String[] filePath = {MediaStore.Images.Media.DATA};
        if (selectedImageUri != null) {
            Cursor cursor = getActivity().getContentResolver().query(selectedImageUri, filePath, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePath[0]);
                playlist.setImagePath(cursor.getString(columnIndex));

                updatePlaylistImage();
            }
        }
    }

    private void addSongPath(Intent intent) {
        Uri selectedSongUri = intent.getData();
        String[] filePath = {MediaStore.Audio.Media.DATA};
        if (selectedSongUri != null) {
            Cursor cursor = getActivity().getContentResolver().query(selectedSongUri, filePath, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePath[0]);
                String path = cursor.getString(columnIndex);
                Song song = getSongByPath(path);

                if (playlist.getSongs() == null) {
                    playlist.setSongs(new ArrayList<>());
                }

                playlist.getSongs().add(song);

                updateSongs();
            }
        }
    }

    private Song getSongByPath(String path){
        PermissionUtil.checkPermission(getActivity());

        Song song = new Song();
        song.setPath(path);
        File file = new File(path);
        if(file.exists()){
            song.setSongName(file.getName());

        }
        return song;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.CHOOSE_IMAGE_REQUEST_CODE) {
            addImagePath(data);
        }
        if (requestCode == Constants.CHOOSE_SONG_REQUEST_CODE) {
            addSongPath(data);
        }
    }

    private void updateSongs() {
        songListView.setAdapter(new SongAdapter(getContext(), playlist.getSongs()));
    }

    private void updatePlaylistImage() {
        PermissionUtil.checkPermission(getActivity());
        File imageFile = new File(playlist.getImagePath());
        if (imageFile.exists()) {
            playlistImageView.setImageURI(Uri.fromFile(imageFile));
        }
    }

    private void addSong() {
        Intent chooseFile = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.INTERNAL_CONTENT_URI);
        chooseFile = Intent.createChooser(chooseFile, getString(R.string.add_audio_intent_title));
        startActivityForResult(chooseFile, Constants.CHOOSE_SONG_REQUEST_CODE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_playlist_image:
                loadImage();
                break;
            case R.id.add_song_btn:
                addSong();
                break;
            case R.id.save_playlist_btn:
                savePlaylist();
                break;
        }

    }
}