package com.anazta217.musicplayer.data.model;

import java.util.List;

public class Playlist {

    private String playlistName;
    private String imagePath;
    private List<Song> songs;

    public String getPlaylistName() {
        return playlistName;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
}
