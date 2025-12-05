package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controller.DataManager;
import model.Playlist;

public class PlaylistManagementPanel extends JPanel {

    private DataManager dataManager;

    private JList<String> playlistList;
    private DefaultListModel<String> playlistModel;
    private JTextField playlistNameField;
    private JButton newPlaylistBtn;
    private JButton addSongBtn;
    private JButton refreshBtn;

    public PlaylistManagementPanel(DataManager dataManager) {
        this.dataManager = dataManager;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Gestión de playlists"));
        initializeComponents();
    }

    private void initializeComponents() {
        // Panel superior: nombre de playlist
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        topPanel.add(new JLabel("Nombre de playlist:"));
        playlistNameField = new JTextField(15);
        topPanel.add(playlistNameField);

        // Lista de playlists
        playlistModel = new DefaultListModel<>();
        playlistList = new JList<>(playlistModel);
        playlistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(playlistList);

        refreshPlaylistList();

        // Botones inferiores
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        newPlaylistBtn = new JButton("Crear playlist");
        addSongBtn = new JButton("Agregar canción");
        refreshBtn = new JButton("Actualizar");
        bottomPanel.add(newPlaylistBtn);
        bottomPanel.add(addSongBtn);
        bottomPanel.add(refreshBtn);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void refreshPlaylistList() {
        playlistModel.clear();
        for (Playlist p : dataManager.getPlaylists()) {
            playlistModel.addElement(p.toString());
        }
    }

    public void addPlaylistListener(ActionListener listener) {
        newPlaylistBtn.addActionListener(listener);
    }

    public void addSongToPlaylistListener(ActionListener listener) {
        addSongBtn.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        refreshBtn.addActionListener(listener);
    }

    public String getPlaylistName() {
        return playlistNameField.getText().trim();
    }

    public Playlist getSelectedPlaylist() {
        int index = playlistList.getSelectedIndex();
        if (index >= 0 && index < dataManager.getPlaylists().size()) {
            return dataManager.getPlaylists().get(index);
        }
        return null;
    }

    public void clearFields() {
        playlistNameField.setText("");
        playlistList.clearSelection();
    }
}