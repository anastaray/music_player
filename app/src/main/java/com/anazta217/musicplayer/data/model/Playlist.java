package com.anazta217.musicplayer.data.model;

import java.util.List;

public class Playlist {

    private String playlistName;
    List<Song> songs;

    public String getPlaylistName() {
        return playlistName;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
}
