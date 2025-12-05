package model;

import java.util.UUID;

public class Song {
    private String id;
    private String title;
    private Artist artist;
    private int duration; // en segundos
    private String genre;

    public Song(String title, Artist artist, int duration, String genre) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDurationFormatted() {
        int minutes = duration / 60;
        int seconds = duration % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    public String toString() {
        return title + " - " + artist.getName() + " (" + getDurationFormatted() + ")";
    }
}