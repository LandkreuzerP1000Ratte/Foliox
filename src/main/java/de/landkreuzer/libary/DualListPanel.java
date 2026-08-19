package de.landkreuzer.libary;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.NORTHWEST;


public class DualListPanel<T> extends JPanel {

    private final DefaultListModel<T> selectedModel = new DefaultListModel<>();
    private final DefaultListModel<T> availableModel = new DefaultListModel<>();

    private final JList<T> selectedList = new JList<>(selectedModel);
    private final JList<T> availableList = new JList<>(availableModel);

    public DualListPanel(String selectedTitle, String availableTitle) {
        setLayout(new GridBagLayout());

        JPanel leftPanel = wrapInTitledPane(selectedTitle, selectedList);
        leftPanel.setPreferredSize(new Dimension(70, 100));

        JPanel rightPanel = wrapInTitledPane(availableTitle, availableList);
        rightPanel.setPreferredSize(new Dimension(70, 50));

        JPanel buttonPanel = buildButtonPanel();


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;


        gbc.anchor = NORTHWEST; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; gbc.fill = GridBagConstraints.NONE;
        add(leftPanel, gbc);

        gbc.gridx = 1; gbc.weightx = 0; gbc.weighty = 0; gbc.fill = GridBagConstraints.NONE;
        add(buttonPanel, gbc);

        gbc.gridx = 2; gbc.weightx = 1; gbc.weighty = 1; gbc.fill = GridBagConstraints.NONE;
        add(rightPanel, gbc);
    }


    public void setItems(java.util.List<T> selected, java.util.List<T> available) {
        selectedModel.clear();
        availableModel.clear();
        selected.forEach(selectedModel::addElement);
        available.forEach(availableModel::addElement);
    }


    public java.util.List<T> getSelectedItems() {
        return java.util.Collections.list(selectedModel.elements());
    }

    private JPanel buildButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));

        JButton up = new JButton("↑");
        up.setPreferredSize(new Dimension (25, 25));

        JButton down = new JButton("↓");
        down.setPreferredSize(new Dimension (25, 25));

        JButton toSelected = new JButton("←");
        toSelected.setPreferredSize(new Dimension (25, 25));

        JButton toAvailable = new JButton("→");
        toAvailable.setPreferredSize(new Dimension (25, 25));




        buttonPanel.add(up);
        buttonPanel.add(down);
        buttonPanel.add(toSelected);
        buttonPanel.add(toAvailable);

        toSelected.addActionListener(e -> {
            for (T value : availableList.getSelectedValuesList()) {
                availableModel.removeElement(value);
                selectedModel.addElement(value);
            }
        });

        toAvailable.addActionListener(e -> {
            for (T value : selectedList.getSelectedValuesList()) {
                selectedModel.removeElement(value);
                availableModel.addElement(value);
            }
        });

        up.addActionListener(e -> moveSelected(selectedList, selectedModel, -1));
        down.addActionListener(e -> moveSelected(selectedList, selectedModel, +1));

        return buttonPanel;
    }

    private void moveSelected(JList<T> list, DefaultListModel<T> model, int delta) {
        int index = list.getSelectedIndex();
        int newIndex = index + delta;
        if (index < 0 || newIndex < 0 || newIndex >= model.getSize()) return;

        T value = model.getElementAt(index);
        model.removeElementAt(index);
        model.insertElementAt(value, newIndex);
        list.setSelectedIndex(newIndex);
    }

    private JPanel wrapInTitledPane(String title, JList<T> list) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(title), BorderLayout.NORTH);
        list.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        return panel;
    }
}
