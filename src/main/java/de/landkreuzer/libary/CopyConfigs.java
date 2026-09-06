package de.landkreuzer.libary;

import javax.swing.*;
import java.awt.*;


public class CopyConfigs<T> extends JPanel {

    private final DefaultListModel<T> selectedModel = new DefaultListModel<>();
    private final DefaultListModel<T> availableModel = new DefaultListModel<>();

    private final JList<T> selectedList = new JList<>(selectedModel);
    private final JList<T> availableList = new JList<>(availableModel);

    public CopyConfigs(String selectedTitle, String availableTitle) {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JPanel leftPanel = wrapInTitledPane(selectedTitle, selectedList);
        leftPanel.setMaximumSize(new Dimension(100, 270));
        leftPanel.setBackground(Color.black);

        JPanel rightPanel = wrapInTitledPane(availableTitle, availableList);
        rightPanel.setMaximumSize(new Dimension(100, 270));
        rightPanel.setBackground(Color.black);

        JPanel buttonPanel = buildButtonPanel();
        buttonPanel.setMaximumSize(new Dimension(50, 150));
        buttonPanel.setBackground(Color.blue);

        layout.setHorizontalGroup(
                layout.createSequentialGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(leftPanel)
                        .addComponent(buttonPanel)
                        .addComponent(rightPanel)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(leftPanel)
                        .addComponent(buttonPanel)
                        .addComponent(rightPanel)
        );
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
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 0, 5));

        JButton up = new JButton("↑");
        up.setMaximumSize(new Dimension(25, 25));

        JButton down = new JButton("↓");
        down.setMaximumSize(new Dimension(25, 25));

        JButton toSelected = new JButton("←");
        toSelected.setMaximumSize(new Dimension(25, 25));

        JButton toAvailable = new JButton("→");
        toAvailable.setMaximumSize(new Dimension(25, 25));

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
        JPanel panel = new JPanel(new BorderLayout(0, 4)); // hgap=0, vgap=8
        panel.add(new JLabel(title), BorderLayout.NORTH);
        list.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        return panel;
    }
}