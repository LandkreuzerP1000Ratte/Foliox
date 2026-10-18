package landkreuzer.de.foliox;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {

    private final ArrayList<String> tableColumns =
            new ArrayList<>(List.of("Barcode", "Titel", "Autor", "Verlag"));

    public static void main(String[] args) {
        launch(args);
    }

    public String[][] readFileContentForTable(File filePath) {
        try (Scanner reader = new Scanner(filePath)) {
            List<String[]> rows = new ArrayList<>();

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                rows.add(line.split(" \\| "));
            }

            String[][] data = rows.toArray(new String[0][]);
            System.out.println(Arrays.deepToString(data));
            return data;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return new String[0][0];
        }
    }

    public TableView<String[]> createTable(ArrayList<String> columns, String[][] contents) {
        TableView<String[]> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        for (int i = 0; i < columns.size(); i++) {
            final int colIndex = i;
            TableColumn<String[], String> column = new TableColumn<>(columns.get(i));
            column.setCellValueFactory(cell -> {
                String[] row = cell.getValue();
                String value = colIndex < row.length ? row[colIndex] : "";
                return new SimpleStringProperty(value);
            });
            table.getColumns().add(column);
        }

        table.setItems(FXCollections.observableArrayList(contents));
        return table;
    }

    @Override
    public void start(Stage primaryStage) {
        File bookFile = new File(System.getProperty("user.dir"),
                "src/main/java/landkreuzer/de/foliox/formated_books.txt");

        ScenePanel sidebar = new ScenePanel();

        TableView<String[]> table = createTable(tableColumns, readFileContentForTable(bookFile));
        StackPane content = new StackPane(table);
        content.setPadding(new Insets(20));

        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.setCenter(content);

        Scene scene = new Scene(root, 1200, 800, Color.web("#313131"));
        scene.getStylesheets().add(getClass().getResource("prism-dark.css").toExternalForm());

        primaryStage.setTitle("Folio X");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }
}