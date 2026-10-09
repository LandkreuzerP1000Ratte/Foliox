package com.example.foliox;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class Main extends Application {

    private final ArrayList<String> tableColumns = new ArrayList<>(List.of("Barcode", "Titel", "Autor", "Verlag"));
    private String[][] content = {{"Sigma", "Richtig Sigma"},
                                {"Sigma", "Richtig Sigma"}};

    Group root = new Group();

    public static void main(String[] args) {
        launch(args);
    }

//---------------------------------------------------------------------------------------

    public String[][] readFileContentForTable(File filePath) {
        try (Scanner reader = new Scanner(filePath)) {
            List<String[]> rows = new ArrayList<>();

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] fields = line.split(" \\| ");
                rows.add(fields);
            }

            String[][] data = rows.toArray(new String[0][]);
            System.out.println(Arrays.deepToString(data));
            return data;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new String[0][0];
        }
    }
//---------------------------------------------------------------------------------------

    public void createTable(double posX, double posY, double width, double height, ArrayList<String> tableColoumns, String[][] tableContens) {
        Pane tablePane = new Pane();
        TableView<String[]> tableV1 = new TableView<>();

        tableV1.setPrefSize(width, height);
        tableV1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        for (int i = 0; i < tableColoumns.size(); i++) {
            final int colIndex = i;
            TableColumn<String[], String> column = new TableColumn<>(tableColoumns.get(i));
            column.setCellValueFactory(cell -> {
                String[] row = cell.getValue();
                String value = colIndex < row.length ? row[colIndex] : "";
                return new SimpleStringProperty(value);
            });
            tableV1.getColumns().add(column);
        }
        tableV1.setItems(FXCollections.observableArrayList(tableContens));

        tablePane.getChildren().addAll(tableV1);
        tablePane.relocate(posX, posY);
        root.getChildren().add(tablePane);
    }

//---------------------------------------------------------------------------------------

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("prism-dark.css").toExternalForm());
        Stage stage = new Stage();

        String bookFilePath = System.getProperty("user.dir");
        File filePath = new File(bookFilePath.concat("/src/main/java/com/example/foliox/formated_books.txt"));

        createTable(100, 100, 900, 300, tableColumns, readFileContentForTable(filePath));

        stage.setScene(scene);
        stage.show();
    }
}