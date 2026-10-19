package landkreuzer.de.foliox;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


import java.io.File;

public class ScenePanel extends ListView<ScenePanel.Entry> {

    public record Entry(String name, String iconPath) {}

    public ScenePanel() {
        getItems().addAll(
                new Entry("Bücher",         "/icons/bücher.png"),
                new Entry("Schüler",         "/icons/schüler.png"),
                new Entry("Öffnungszeiten",     "/icons/öffnungszeiten.png"),
                new Entry("Einstellungen",     "/icons/einstellungen.png")
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

                ImageView icon;

                try {
                    Image image = new Image(getClass().getResourceAsStream(item.iconPath()));
                    if (image.isError()) throw new IllegalStateException("Image failed to load");
                    icon = new ImageView(image);
                } catch (Exception e) {
                    e.printStackTrace();
                    icon = new ImageView(new Image(new File("imagePlaceholder.jpg").toURI().toString()));
                }

                    icon.setFitWidth(20);
                    icon.setFitHeight(20);
                    icon.setPreserveRatio(true);

                    HBox topBox = new HBox(10, icon, new Label(item.name()));
                    topBox.setAlignment(Pos.CENTER_LEFT);
                    setGraphic(topBox);
                }
        });

        setPrefWidth(170);
        setFocusTraversable(false);
        getStyleClass().add("sidebar");
    }
}