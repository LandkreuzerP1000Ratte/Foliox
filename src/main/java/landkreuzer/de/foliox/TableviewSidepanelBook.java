package landkreuzer.de.foliox;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;

public class TableviewSidepanelBook {

    public static VBox create(String imageCover, Media displayedInfo) {
        VBox root = new VBox(10);
        root.setFillWidth(true);
        root.setPadding(new Insets(10));
        root.setMinWidth(300);
        root.setPrefWidth(400);

        VBox content = new VBox(20);
        content.setPadding(new Insets(10));
        content.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));

        URL resource = TableviewSidepanelBook.class.getResource("/landkreuzer/de/foliox/" + imageCover);
        if (resource != null) {
            ImageView img = new ImageView(new Image(resource.toExternalForm()));
            img.setPreserveRatio(true);
            img.setFitWidth(150);
            content.getChildren().add(img);
        } else {
            System.out.println("Bild nicht gefunden: " + imageCover);
        }

        VBox infoBox = new VBox(5);
        VBox.setVgrow(infoBox, Priority.ALWAYS);
        for (String info : displayedInfo) {
            infoBox.getChildren().add(new Label(info));
        }
        content.getChildren().add(infoBox);


        // Das war die einzigste Lösung die ich nach 2 Stunden gefunden hab

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        root.getChildren().add(scrollPane);
        return root;
    }
}