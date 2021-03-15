package com.anazta217.musicplayer.ui.main.view;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.anazta217.musicplayer.R;

import java.io.File;

public class SongFragment extends Fragment implements View.OnClickListener {

    private Button playSongBtn;
    private SeekBar positionBar, volumeBar;
    private int totalTime;
    private MediaPlayer mediaPlayer;
    private TextView elapsedTimeLabel, remainingTimeLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.song_reply, container, false);

        inflate.findViewById(R.id.play_song_btn).setOnClickListener(this);
        inflate.findViewById(R.id.add_playlist_image).setOnClickListener(this);
       // inflate.findViewById(R.id.stop_song_btn).setOnClickListener(this);
        inflate.findViewById(R.id.next_song_btn).setOnClickListener(this);
        inflate.findViewById(R.id.previous_son_btn).setOnClickListener(this);
        inflate.findViewById(R.id.random_song_btn).setOnClickListener(this);
        inflate.findViewById(R.id.repeat_song_btn).setOnClickListener(this);

        elapsedTimeLabel = inflate.findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = inflate.findViewById(R.id.remainingTimeLabel);



        File file = new File("aa");
        Uri uri = Uri.fromFile(file);

        mediaPlayer = MediaPlayer.create(getContext(), uri);
        mediaPlayer.setLooping(true);
        mediaPlayer.seekTo(0);
        mediaPlayer.setVolume(0.5f, 0.5f);
        totalTime = mediaPlayer.getDuration();

        return inflate;
    }

    @Override
    public void onClick(View v) {

    }
}
