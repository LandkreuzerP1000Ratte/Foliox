package landkreuzer.de.foliox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// Kommentare sind überbewertet

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 1200, 800, Color.web("#313131"));
        scene.getStylesheets().add(getClass().getResource("prism-dark.css").toExternalForm());

        primaryStage.setTitle("Foliox");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }
}

















/*
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



    @Override
    public void start(Stage primaryStage) {
        File bookFile = new File(System.getProperty("user.dir"),
                "src/main/java/landkreuzer/de/foliox/formated_books.txt");

        StagePanel sidebar = new StagePanel();dis

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
} */