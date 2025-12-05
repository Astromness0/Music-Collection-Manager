package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Artist;
import model.Playlist;
import model.Song;

public class FileManager {

    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Playlist> loadPlaylistsFromFile() throws IOException {
        ArrayList<Playlist> playlists = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Archivo no encontrado: " + filePath);
            return playlists;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            Playlist playlist = parsePlaylistLine(line);
            if (playlist != null) {
                playlists.add(playlist);
            }
        }
        reader.close();
        return playlists;
    }

    public void savePlaylists(ArrayList<Playlist> playlists) throws IOException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Playlist playlist : playlists) {
            StringBuilder line = new StringBuilder();
            line.append(playlist.getName()).append(",");

            ArrayList<Song> songs = playlist.getSongs();
            for (int i = 0; i < songs.size(); i++) {
                Song song = songs.get(i);
                line.append(song.getArtist().getName())
                    .append("|")
                    .append(song.getTitle())
                    .append("|")
                    .append(song.getDuration());

                if (i < songs.size() - 1) {
                    line.append(",");
                }
            }

            writer.write(line.toString());
            writer.newLine();
        }
        writer.close();
    }

    private Playlist parsePlaylistLine(String line) {
        String[] parts = line.split(",");
        if (parts.length < 1) return null;

        Playlist playlist = new Playlist(parts[0]);

        // Por ahora dejamos la playlist sin canciones.
        return playlist;
    }
}