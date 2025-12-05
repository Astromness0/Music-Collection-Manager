package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Playlist {
    private String id;
    private String name;
    private ArrayList<Song> songs;
    private Date creationDate;

    public Playlist(String name) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
        this.songs = new ArrayList<>();
        this.creationDate = new Date();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public boolean addSong(Song song) {
        if (song != null && !songs.contains(song)) {
            songs.add(song);
            return true;
        }
        return false;
    }

    public boolean removeSong(Song song) {
        return songs.remove(song);
    }

    public int getSongCount() {
        return songs.size();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return name + " (" + songs.size() + " canciones)";
    }
}