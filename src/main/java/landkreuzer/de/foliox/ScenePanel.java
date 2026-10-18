package landkreuzer.de.foliox;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ScenePanel extends ListView<ScenePanel.Entry> {

    public record Entry(String name, String iconPath) {}

    public ScenePanel() {
        getItems().addAll(
                new Entry("Custom",         "/icons/custom.png"),
                new Entry("Import",         "/icons/import.png"),
                new Entry("ATLauncher",     "/icons/atlauncher.png"),
                new Entry("CurseForge",     "/icons/curseforge.png"),
                new Entry("FTB",            "/icons/ftb.png"),
                new Entry("FTB Legacy",     "/icons/ftb.png"),
                new Entry("FTB App Import", "/icons/ftb.png"),
                new Entry("Modrinth",       "/icons/modrinth.png"),
                new Entry("Technic",        "/icons/technic.png")
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

                ImageView icon = new ImageView(
                        new Image(getClass().getResourceAsStream(item.iconPath())));
                icon.setFitWidth(20);
                icon.setFitHeight(20);
                icon.setPreserveRatio(true);

                HBox box = new HBox(10, icon, new Label(item.name()));
                box.setAlignment(Pos.CENTER_LEFT);
                setGraphic(box);
            }
        });

        setPrefWidth(170);
        setFocusTraversable(false);
        getStyleClass().add("sidebar");
    }
}