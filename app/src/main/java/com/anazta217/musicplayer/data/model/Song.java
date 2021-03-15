package com.anazta217.musicplayer.data.model;

public class Song {

    private String path;
    private String songName;
    private String singer;
    private String songLength;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSinger() {
        return singer;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }


    public String getSongLength() {
        return songLength;
    }

    public void setSongLength(String songLength) {
        this.songLength = songLength;
    }
}
