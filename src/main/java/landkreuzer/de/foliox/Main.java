package landkreuzer.de.foliox;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private final ArrayList<String> tableColumns = new ArrayList<>(List.of("UUID" ,"Barcode", "Title", "Author", "Publisher", "Type", "Language", "Borrowed", "Category", "Condition", "Available"));

    public String[][] readFileContentForTable(File filePath) {
        List<String[]> rows = new ArrayList<>();

        try (var reader = Files.newBufferedReader(filePath.toPath(), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] cells = line.split("\\|", -1);
                for (int i = 0; i < cells.length; i++) {
                    cells[i] = cells[i].trim();
                }
                rows.add(cells);
            }
        } catch (IOException e) {
            System.out.println("Datei konnte nicht gelesen werden: " + e.getMessage());
            e.printStackTrace();
            return new String[0][0];
        }

        return rows.toArray(new String[0][]);
    }

    private boolean isVisible(int index, String pattern) {
        return index < pattern.length() && pattern.charAt(index) == '1';
    }


    @Override
    public void start(Stage primaryStage) {

        File bookFile = new File(System.getProperty("user.dir"),
                "src/main/resources/landkreuzer/de/foliox/formated_books.txt");

        String[][] allRows = readFileContentForTable(bookFile);


        TableView<String[]> table = TableviewFactory.create(tableColumns, allRows);

        BorderPane root = new BorderPane();

        table.setMinWidth(400);

        Scene scene = new Scene(root, 1200, 800, Color.web("#313131"));
        scene.getStylesheets().add(getClass().getResource("prism-dark.css").toExternalForm());

        SplitPane split = new SplitPane(table, TableviewSidepanelBook.create("bildoderso.png", tableColumns));
        split.setDividerPositions(0.9);

        Map<String, Node> pages = new LinkedHashMap<>();
        pages.put("Bücher", split);
        pages.put("Schüler", new Label("Schüler kommt noch"));
        pages.put("Öffnungszeiten", new Label("Öffnungszeiten kommt noch"));
        pages.put("Einstellungen", new Label("Einstellungen kommt noch"));

        root.setLeft(new StagePanel(root, pages));
        root.setCenter(split);


        primaryStage.setTitle("Foliox");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }
}