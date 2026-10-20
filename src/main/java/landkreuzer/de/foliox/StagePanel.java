package landkreuzer.de.foliox;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class StagePanel extends ListView<StagePanel.Entry> {

    public record Entry(String name) {}

    public StagePanel() {
        getItems().addAll(
                new Entry("Bücher"),
                new Entry("Schüler"),
                new Entry("Öffnungszeiten"),
                new Entry("Einstellungen")
        );

        setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Entry item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                    return;
                }

                    HBox topBox = new HBox(10, new Label(item.name()));
                    topBox.setAlignment(Pos.CENTER_LEFT);
                    setGraphic(topBox);
                }
        });

        setPrefWidth(170);
        setFocusTraversable(false);
        getStyleClass().add("sidebar");
    }
}