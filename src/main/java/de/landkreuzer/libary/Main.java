package de.landkreuzer.libary;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Main {

    private static JPanel sideMenu;
    private static JFrame frame;

    public static List<String> bookReader(File bookfile) {

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(bookfile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + bookfile);

        } catch (AccessDeniedException e) {
            System.out.println("Access Denied to Read the File: " + bookfile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    public static void attachContextMenu(JComponent component) {
        JPopupMenu popupMenu = createContextMenu();

        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopupIfTriggered(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopupIfTriggered(e);
            }

            private void showPopupIfTriggered(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    public static JPopupMenu createContextMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem editItem = new JMenuItem("Configure");
        editItem.addActionListener(e -> showEditLinePopupWindow(frame));

        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(e -> System.out.println("Clicked delete"));

        popupMenu.add(editItem);
        popupMenu.add(deleteItem);

        return popupMenu;
    }

    public static List<String[]> parseLines(List<String> lines) {
        List<String[]> rows = new ArrayList<>();
        for (String line : lines) {
            rows.add(line.split("\\|"));
        }
        return rows;
    }

    public static void showEditLinePopupWindow(Frame owner) {
        JDialog dialog = new JDialog(owner, "Colum selected", true);
        dialog.setSize(475, 500);
        dialog.setResizable(false);
        dialog.setLayout(new BorderLayout(10, 10));


        DualListPanel<String> dualListPanel = new DualListPanel<>("Selected", "Available");
        dualListPanel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        dualListPanel.setItems(
                List.of("Title", "Author"),
                List.of("Publisher", "Borrowed", "Art", "ID")
        );
        dialog.add(dualListPanel, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("discard");

        ok.addActionListener(e -> {
            List<String> result = dualListPanel.getSelectedItems();
            System.out.println("Selected colum: " + result);
            dialog.dispose();
        });
        cancel.addActionListener(e -> dialog.dispose());

        bottom.add(ok);
        bottom.add(cancel);
        dialog.add(bottom, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(owner);
        dialog.setVisible(true);
    }


    public static void main(String[] args) {
        FlatDarkLaf.setup(); // Modern dark design

        frame = new JFrame("Foliox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Column Headings
        String[] columns = {"ID", "Title", "Author", "Publisher", "Borrowed", "Art"};

        Path localDir = Path.of(System.getProperty("user.dir"));
        Path bookFile = localDir.resolve("src\\main\\java\\de\\landkreuzer\\libary\\data").resolve("books.txt");

        List<String> rawLines = bookReader(bookFile.toFile());
        List<String[]> data = parseLines(rawLines);

        Object[][] rowData = data.toArray(new Object[0][]);

        JTable table = new JTable(rowData, columns);
        table.setShowGrid(true);

        attachContextMenu(table);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Sidemenu
        sideMenu = buildSideMenu();
        sideMenu.setVisible(false);
        frame.add(sideMenu, BorderLayout.EAST);

        // Toggle-button
        JButton toggleButton = new JButton("☰ Menü");
        toggleButton.addActionListener(e -> {
            sideMenu.setVisible(!sideMenu.isVisible());
            frame.revalidate();
            frame.repaint();
        });

        JToolBar toolBar = new JToolBar();
        toolBar.add(toggleButton);
        frame.add(toolBar, BorderLayout.NORTH);

        frame.setSize(1500, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel buildSideMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(250, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField search = new JTextField(15);
        search.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        search.setAlignmentX(Component.LEFT_ALIGNMENT);



        JButton addBook = new JButton("Buch hinzufügen");
        JButton deleteBook = new JButton("Buch löschen");


        search.setAlignmentX(Component.LEFT_ALIGNMENT);
        addBook.setAlignmentX(Component.LEFT_ALIGNMENT);
        deleteBook.setAlignmentX(Component.LEFT_ALIGNMENT);



        panel.add(search);
        panel.add(Box.createVerticalStrut(8));
        panel.add(addBook);
        panel.add(Box.createVerticalStrut(8));
        panel.add(deleteBook);



        return panel;
    }
}