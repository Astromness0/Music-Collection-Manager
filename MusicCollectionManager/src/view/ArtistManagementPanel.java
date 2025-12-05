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
import model.Artist;

public class ArtistManagementPanel extends JPanel {

    private DataManager dataManager;

    private JList<String> artistList;
    private DefaultListModel<String> artistModel;
    private JTextField nameField;
    private JTextField countryField;
    private JButton createBtn;
    private JButton refreshBtn;

    public ArtistManagementPanel(DataManager dataManager) {
        this.dataManager = dataManager;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Gestión de artistas"));
        initializeComponents();
    }

    private void initializeComponents() {
        // Panel de entrada (arriba)
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.add(new JLabel("Nombre:"));
        nameField = new JTextField(15);
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("País:"));
        countryField = new JTextField(15);
        inputPanel.add(countryField);

        // Lista de artistas (centro)
        artistModel = new DefaultListModel<>();
        artistList = new JList<>(artistModel);
        artistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(artistList);

        refreshArtistList();

        // Botones (abajo)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        createBtn = new JButton("Crear artista");
        refreshBtn = new JButton("Actualizar");
        buttonPanel.add(createBtn);
        buttonPanel.add(refreshBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void refreshArtistList() {
        artistModel.clear();
        for (Artist a : dataManager.getArtists()) {
            artistModel.addElement(a.toString());
        }
    }

    public void addCreateArtistListener(ActionListener listener) {
        createBtn.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        refreshBtn.addActionListener(listener);
    }

    public String getArtistName() {
        return nameField.getText().trim();
    }

    public String getArtistCountry() {
        return countryField.getText().trim();
    }

    public Artist getSelectedArtist() {
        int index = artistList.getSelectedIndex();
        if (index >= 0 && index < dataManager.getArtists().size()) {
            return dataManager.getArtists().get(index);
        }
        return null;
    }

    public void clearFields() {
        nameField.setText("");
        countryField.setText("");
        artistList.clearSelection();
    }
}