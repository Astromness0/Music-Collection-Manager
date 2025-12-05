package view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

import controller.DataManager;
import model.Artist;
import model.Song;
import model.Playlist;

public class MusicCollectionApp extends JFrame {

    private DataManager dataManager;
    private PlaylistManagementPanel playlistPanel;
    private ArtistManagementPanel artistPanel;
    private SongManagementPanel songPanel;

    public MusicCollectionApp() {
        this.dataManager = new DataManager();
        initializeUI();
        setupListeners();
    }

    private void initializeUI() {
        setTitle("Gestor de Colecciones de Música");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        playlistPanel = new PlaylistManagementPanel(dataManager);
        artistPanel = new ArtistManagementPanel(dataManager);
        songPanel = new SongManagementPanel(dataManager);

        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.add(playlistPanel);
        mainPanel.add(artistPanel);
        mainPanel.add(songPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane);

        setVisible(true);
    }

    private void setupListeners() {

        // ===== ARTISTAS =====
        artistPanel.addCreateArtistListener(e -> {
            String name = artistPanel.getArtistName();
            String country = artistPanel.getArtistCountry();

            if (name.isEmpty() || country.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Por favor completa todos los campos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } else {
                try {
                    dataManager.createArtist(name, country);
                    artistPanel.clearFields();
                    artistPanel.refreshArtistList();
                    songPanel.refreshArtistCombo();
                    JOptionPane.showMessageDialog(this, "Artista creado correctamente");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        artistPanel.addRefreshListener(e -> {
            artistPanel.refreshArtistList();
            songPanel.refreshArtistCombo();
        });

        // ===== CANCIONES =====
        songPanel.addCreateSongListener(e -> {
            String title = songPanel.getSongTitle();
            int duration = songPanel.getSongDuration();
            String genre = songPanel.getSongGenre();
            Artist artist = songPanel.getSelectedArtist();

            if (title.isEmpty() || genre.isEmpty() || artist == null || duration <= 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Por favor completa todos los campos y usa una duración válida",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } else {
                try {
                    dataManager.createSong(title, artist, duration, genre);
                    songPanel.clearFields();
                    songPanel.refreshSongList();
                    JOptionPane.showMessageDialog(this, "Canción creada correctamente");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        songPanel.addRefreshListener(e -> {
            songPanel.refreshSongList();
            songPanel.refreshArtistCombo();
        });

        // ===== PLAYLISTS =====
        playlistPanel.addPlaylistListener(e -> {
            String name = playlistPanel.getPlaylistName();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Por favor ingresa un nombre de playlist",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } else {
                try {
                    dataManager.createPlaylist(name);
                    playlistPanel.clearFields();
                    playlistPanel.refreshPlaylistList();
                    JOptionPane.showMessageDialog(this, "Playlist creada correctamente");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        playlistPanel.addSongToPlaylistListener(e -> {
            Playlist playlist = playlistPanel.getSelectedPlaylist();
            Song song = songPanel.getSelectedSong();

            if (playlist == null) {
                JOptionPane.showMessageDialog(
                    this,
                    "Selecciona una playlist",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } else if (song == null) {
                JOptionPane.showMessageDialog(
                    this,
                    "Selecciona una canción",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } else {
                boolean added = dataManager.addSongToPlaylist(playlist, song);
                if (added) {
                    playlistPanel.refreshPlaylistList();
                    JOptionPane.showMessageDialog(this, "Canción agregada correctamente");
                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        "La canción ya está en la playlist",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        playlistPanel.addRefreshListener(e -> playlistPanel.refreshPlaylistList());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MusicCollectionApp());
    }
}