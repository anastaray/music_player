package com.anazta217.musicplayer.ui.main.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anazta217.musicplayer.R;
import com.anazta217.musicplayer.data.model.Playlist;
import com.anazta217.musicplayer.ui.main.view.MainActivity;

import java.io.File;
import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder>{

    private final MainActivity mainActivity;
    private final LayoutInflater inflater;
    private final List<Playlist> playlists;

    public PlaylistAdapter(Context context, List<Playlist> playlists, MainActivity activity) {
        this.inflater = LayoutInflater.from(context);
        this.playlists = playlists;
        this.mainActivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.playlist_item, parent, false);
        return new PlaylistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = playlists.get(position);
        holder.imageView.setImageURI(Uri.fromFile(new File(playlist.getImagePath())));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.openPlaylistFragment(playlist);
            }
        });
        holder.titleTextView.setText(playlist.getPlaylistName());

    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView titleTextView;
        final TextView songsCountTextView;
        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.playlist_item_image);
            titleTextView = view.findViewById(R.id.playlist_item_title);
            songsCountTextView = view.findViewById(R.id.playlist_item_songs_count);
        }
    }
}


