package landkreuzer.de.foliox;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main extends Application {

    // 1 = Spalte anzeigen, 0 = Spalte ausblenden
    private static final String COLUMN_PATTERN = "01111";

    public static void main(String[] args) {
        launch(args);
    }

    private final ArrayList<String> tableColumns =
            new ArrayList<>(List.of("UUID" ,"Barcode", "Titel", "Autor", "Verlag"));

    public String[][] readFileContentForTable(File filePath) {
        try (Scanner reader = new Scanner(filePath)) {
            List<String[]> rows = new ArrayList<>();

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                rows.add(line.split(" \\| "));
            }

            String[][] data = rows.toArray(new String[0][]);
            return data;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return new String[0][0];
        }
    }

    private boolean isVisible(int index, String pattern) {
        return index < pattern.length() && pattern.charAt(index) == '1';
    }

    private ArrayList<String> filterColumnNames(List<String> columns, String pattern) {
        ArrayList<String> visible = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            if (isVisible(i, pattern)) {
                visible.add(columns.get(i));
            }
        }
        return visible;
    }

    private String[][] filterColumnValues(String[][] data, String pattern) {
        String[][] result = new String[data.length][];
        for (int r = 0; r < data.length; r++) {
            List<String> row = new ArrayList<>();
            for (int c = 0; c < data[r].length; c++) {
                if (isVisible(c, pattern)) {
                    row.add(data[r][c]);
                }
            }
            result[r] = row.toArray(new String[0]);
        }
        return result;
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 1200, 800, Color.web("#313131"));
        scene.getStylesheets().add(getClass().getResource("prism-dark.css").toExternalForm());

        File bookFile = new File(System.getProperty("user.dir"),
                "src/main/resources/landkreuzer/de/foliox/formated_books.txt");

        String[][] allRows = readFileContentForTable(bookFile);

        ArrayList<String> visibleColumns = filterColumnNames(tableColumns, COLUMN_PATTERN);
        String[][] visibleValues = filterColumnValues(allRows, COLUMN_PATTERN);

        TableView<String[]> table = TableviewFactory.create(visibleColumns, visibleValues);

        Map<String, Node> pages = new LinkedHashMap<>();
        pages.put("Bücher", table);
        pages.put("Schüler", new Label("Schüler kommt noch"));
        pages.put("Öffnungszeiten", new Label("Öffnungszeiten kommt noch"));
        pages.put("Einstellungen", new Label("Einstellungen kommt noch"));

        root.setLeft(new StagePanel(root, pages));
        root.setCenter(table);
        root.setRight(TableviewSidepanelBook.create());

        primaryStage.setTitle("Foliox");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }
}