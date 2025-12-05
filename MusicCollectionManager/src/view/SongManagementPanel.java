package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controller.DataManager;
import model.Artist;
import model.Song;

public class SongManagementPanel extends JPanel {

    private DataManager dataManager;

    private JList<String> songList;
    private DefaultListModel<String> songModel;
    private JTextField titleField;
    private JTextField durationField;
    private JTextField genreField;
    private JComboBox<String> artistCombo;
    private JButton createBtn;
    private JButton refreshBtn;

    public SongManagementPanel(DataManager dataManager) {
        this.dataManager = dataManager;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Gestión de canciones"));
        initializeComponents();
    }

    private void initializeComponents() {
        // Panel de entrada (arriba)
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        inputPanel.add(new JLabel("Título:"));
        titleField = new JTextField(15);
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Artista:"));
        artistCombo = new JComboBox<>();
        refreshArtistCombo();
        inputPanel.add(artistCombo);

        inputPanel.add(new JLabel("Duración (seg):"));
        durationField = new JTextField(15);
        inputPanel.add(durationField);

        inputPanel.add(new JLabel("Género:"));
        genreField = new JTextField(15);
        inputPanel.add(genreField);

        // Lista de canciones (centro)
        songModel = new DefaultListModel<>();
        songList = new JList<>(songModel);
        songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(songList);

        refreshSongList();

        // Botones (abajo)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        createBtn = new JButton("Crear canción");
        refreshBtn = new JButton("Actualizar");
        buttonPanel.add(createBtn);
        buttonPanel.add(refreshBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void refreshSongList() {
        songModel.clear();
        for (Song s : dataManager.getSongs()) {
            songModel.addElement(s.toString());
        }
    }

    public void refreshArtistCombo() {
        artistCombo.removeAllItems();
        for (Artist a : dataManager.getArtists()) {
            artistCombo.addItem(a.getName());
        }
    }

    public void addCreateSongListener(ActionListener listener) {
        createBtn.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        refreshBtn.addActionListener(listener);
    }

    public String getSongTitle() {
        return titleField.getText().trim();
    }

    public int getSongDuration() {
        try {
            return Integer.parseInt(durationField.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getSongGenre() {
        return genreField.getText().trim();
    }

    public Artist getSelectedArtist() {
        String artistName = (String) artistCombo.getSelectedItem();
        if (artistName == null) return null;
        for (Artist a : dataManager.getArtists()) {
            if (a.getName().equals(artistName)) {
                return a;
            }
        }
        return null;
    }

    public Song getSelectedSong() {
        int index = songList.getSelectedIndex();
        if (index >= 0 && index < dataManager.getSongs().size()) {
            return dataManager.getSongs().get(index);
        }
        return null;
    }

    public void clearFields() {
        titleField.setText("");
        durationField.setText("");
        genreField.setText("");
        if (artistCombo.getItemCount() > 0) {
            artistCombo.setSelectedIndex(0);
        }
        songList.clearSelection();
    }
}