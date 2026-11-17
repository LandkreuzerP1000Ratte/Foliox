package landkreuzer.de.foliox;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.util.*;

import static javafx.scene.layout.Border.EMPTY;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private final ArrayList<String> tableColumns = new ArrayList<>(List.of("UUID", "Barcode", "Title", "Author", "Publisher", "Type", "Language", "Borrowed", "Category", "Condition", "Available"));


    @Override
    public void start(Stage primaryStage) {

        File bookFile = new File(System.getProperty("user.dir"),
                "src/main/resources/landkreuzer/de/foliox/formated_books.txt");

        String[][] allRows = FileEditor.readAll(bookFile).toArray(new String[0][]);

        TableView<String[]> table = TableviewFactory.create(tableColumns, allRows);

        BorderPane root = new BorderPane();
        table.setMinWidth(400);

        Scene scene = new Scene(root, 1920, 1080, Color.web("#313131"));
        scene.getStylesheets().add(getClass().getResource("prism-dark.css").toExternalForm());


        SplitPane split = new SplitPane(table, TableviewSidepanelBook.create("bildoderso.png", tableColumns));
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


        primaryStage.setTitle("Foliox");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }
}