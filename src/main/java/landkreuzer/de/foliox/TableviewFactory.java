package landkreuzer.de.foliox;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class TableviewFactory {

    private TableviewFactory() {}

    public static TableView<String[]> create(ArrayList<String> columns, String[][] contents) {
        TableView<String[]> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

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

    public static void save(TableView<String[]> table, File file) throws IOException {
        List<String> lines =  new ArrayList<>();
        for (String[] row : table.getItems()) {
            lines.add(String.join(" | ", row));
        }
    }
}

//public static void removeBook(TableView<String[]> String UUID)
