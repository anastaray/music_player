package com.anazta217.musicplayer.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anazta217.musicplayer.R;
import com.anazta217.musicplayer.data.model.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Song> songs;

    public SongAdapter(Context context, List<Song> songs){
        this.songs = songs;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.song_item_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongAdapter.ViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.nameView.setText(song.getSongName());
        holder.singerView.setText(song.getSinger());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        final TextView singerView;
        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.song_name);
            singerView = view.findViewById(R.id.singer_name);
        }
    }
}
