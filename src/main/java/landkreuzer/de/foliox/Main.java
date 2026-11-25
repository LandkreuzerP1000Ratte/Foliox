package landkreuzer.de.foliox;

import java.io.File;
import java.util.*;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private final ArrayList<String> tableColumns = new ArrayList<>(List.of("UUID", "Barcode", "Title", "Author", "Publisher", "Type", "Language", "Borrowed", "Category", "Condition", "Available"));


    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, root.getWidth(), root.getHeight(), Color.web("#313131"));
        scene.getStylesheets().add(getClass().getResource("prism-dark.css").toExternalForm());

        primaryStage.setTitle("Foliox");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();

        File bookFile = new File(System.getProperty("user.dir"),
                "src/main/resources/landkreuzer/de/foliox/formated_books.txt");

        String[][] allRows = FileEditor.readAll(bookFile).toArray(new String[0][]);

        TableView<String[]> table = TableviewFactory.create(tableColumns, allRows);
        table.setMinWidth(400);

        SplitPane split = new SplitPane(table, TableviewSidepanelBook.create("ImagePlaceholder.png", primaryStage));
        SplitPane.setResizableWithParent(split, false);
        split.setMinWidth(300);
        split.setPrefWidth(400);
        split.setDividerPositions(0.8);

        Map<String, Node> pages = new LinkedHashMap<>();
        pages.put("Bücher", split);
        pages.put("Schüler", new Label("Schüler kommt noch"));
        pages.put("Öffnungszeiten", new Label("Öffnungszeiten kommt noch"));
        pages.put("Einstellungen", new Label("Einstellungen kommt noch"));

        root.setLeft(new StagePanel(root, pages));
        root.setCenter(split);
    }
}