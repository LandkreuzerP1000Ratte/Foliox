package de.landkreuzer.libary;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.JTextComponent;
import javax.swing.undo.UndoManager;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

    public static List<String[]> parseLines(List<String> lines) {
        List<String[]> rows = new ArrayList<>();
        for (String line : lines) {
            rows.add(line.split("\\|"));
        }
        return rows;
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup(); // Modernes dunkles Design aktivieren

        frame = new JFrame("Bibliothek");
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

        // Wichtig: Tabelle in ein ScrollPane setzen für die Kopfzeile
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Das ausklappbare Seitenmenü
        sideMenu = buildSideMenu();
        sideMenu.setVisible(false); // Standardmäßig eingeklappt
        frame.add(sideMenu, BorderLayout.EAST);

        // Toggle-Button oben
        JButton toggleButton = new JButton("☰ Menü");
        toggleButton.addActionListener(e -> {
            sideMenu.setVisible(!sideMenu.isVisible());
            frame.revalidate();
            frame.repaint();
        });

        JToolBar toolBar = new JToolBar();
        toolBar.add(toggleButton);
        frame.add(toolBar, BorderLayout.NORTH);

        // pack() NICHT aufrufen, sonst überschreibt es setSize()
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