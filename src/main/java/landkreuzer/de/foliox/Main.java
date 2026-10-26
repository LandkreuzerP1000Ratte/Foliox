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

// Kommentare sind überbewertet

public class Main extends Application {




    public static void main(String[] args) {
        launch(args);
    }

    private final ArrayList<String> tableColumns =
            new ArrayList<>(List.of("Barcode", "Titel", "Autor", "Verlag"));

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
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 1200, 800, Color.web("#313131"));
        scene.getStylesheets().add(getClass().getResource("prism-dark.css").toExternalForm());

        File bookFile = new File(System.getProperty("user.dir"),
                "src/main/java/landkreuzer/de/foliox/formated_books.txt");

        TableView<String[]> table = TableviewFactory.create(tableColumns, readFileContentForTable(bookFile));

        Map<String, Node> pages = new LinkedHashMap<>();
        pages.put("Bücher", table);
        pages.put("Schüler", new Label("Schüler kommt noch"));
        pages.put("Öffnungszeiten", new Label("Öffnungszeiten kommt noch"));
        pages.put("Einstellungen", new Label("Einstellungen kommt noch"));


        root.setLeft(new StagePanel(root, pages));
        root.setCenter(table);


        primaryStage.setTitle("Foliox");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }
}