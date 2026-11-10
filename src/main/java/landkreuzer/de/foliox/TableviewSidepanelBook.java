package landkreuzer.de.foliox;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class TableviewSidepanelBook {

    public static VBox create(String imageCover, ArrayList<String> basicInfo) {
        VBox vBox = new VBox(10);
        vBox.setFillWidth(true);
        vBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("Green"), CornerRadii.EMPTY, Insets.EMPTY)));

        HBox hBox = new HBox(40);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.maxWidth(Double.MAX_VALUE);
        hBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("Red"), CornerRadii.EMPTY, Insets.EMPTY)));

        URL resource = TableviewSidepanelBook.class.getResource("/landkreuzer/de/foliox/" + imageCover);
        if (resource != null) {
            ImageView img = new ImageView(new Image(resource.toExternalForm()));
            img.setPreserveRatio(true);
            img.setFitWidth(80);
            hBox.getChildren().add(img);

        } else {
            System.out.println("Bild nicht gefunden: " + imageCover);
        }

        VBox betterVBox = new VBox();
        betterVBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("Blue"), CornerRadii.EMPTY, Insets.EMPTY)));
        for (int i = 0; i < basicInfo.size(); i++) {
            switch (i) {
                case 0,
            }
            Label label = new Label();
            betterVBox.getChildren().add(label);

        }
        hBox.getChildren().add(betterVBox);
        vBox.getChildren().add(hBox);

        return vBox;
    }
}