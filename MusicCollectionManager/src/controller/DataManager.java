package controller;

import java.util.ArrayList;

import model.Artist;
import model.Playlist;
import model.Song;
import util.FileManager;

public class DataManager {

    private ArrayList<Artist> artists;
    private ArrayList<Song> songs;
    private ArrayList<Playlist> playlists;
    private FileManager fileManager;

    public DataManager() {
        this.artists = new ArrayList<>();
        this.songs = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.fileManager = new FileManager("data/playlists.csv");
        loadInitialData();
    }

    public Artist createArtist(String name, String country) {
        if (name == null || name.isEmpty() || country == null || country.isEmpty()) {
            throw new IllegalArgumentException("Nombre y país son requeridos");
        }
        Artist artist = new Artist(name, country);
        artists.add(artist);
        return artist;
    }

    public Song createSong(String title, Artist artist, int duration, String genre) {
        if (title == null || title.isEmpty() || artist == null || genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("Título, artista y género son requeridos");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor a 0");
        }
        Song song = new Song(title, artist, duration, genre);
        songs.add(song);
        return song;
    }

    public Playlist createPlaylist(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la playlist es requerido");
        }
        Playlist playlist = new Playlist(name);
        playlists.add(playlist);
        return playlist;
    }

    public boolean addSongToPlaylist(Playlist playlist, Song song) {
        if (playlist != null && song != null) {
            return playlist.addSong(song);
        }
        return false;
    }

    public ArrayList<Artist> getArtists() {
        return new ArrayList<>(artists);
    }

    public ArrayList<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public ArrayList<Playlist> getPlaylists() {
        return new ArrayList<>(playlists);
    }

    public void loadInitialData() {
        try {
            ArrayList<Playlist> loadedPlaylists = fileManager.loadPlaylistsFromFile();
            playlists.addAll(loadedPlaylists);
        } catch (Exception e) {
            System.out.println("No se pudo cargar el archivo inicial: " + e.getMessage());
        }
    }

    public void saveData() {
        try {
            fileManager.savePlaylists(playlists);
        } catch (Exception e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }
}