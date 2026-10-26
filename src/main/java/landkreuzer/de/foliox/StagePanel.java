package landkreuzer.de.foliox;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.Map;

public class StagePanel extends ListView<StagePanel.Entry> {

    public record Entry(String name) {}

    public StagePanel(BorderPane root, Map<String, Node> pages) {
        for (String name : pages.keySet()) {
            getItems().add(new Entry(name));
        }

        setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Entry item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                    return;
                }

                HBox box = new HBox(new Label(item.name()));
                box.setAlignment(Pos.CENTER_LEFT);
                setGraphic(box);
            }
        });

        getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                root.setCenter(pages.get(newVal.name()));
            }
        });

        setPrefWidth(170);
        setFocusTraversable(false);
        getStyleClass().add("sidebar");
    }
}