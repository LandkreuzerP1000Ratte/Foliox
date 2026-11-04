package landkreuzer.de.foliox;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ArrayList;

public class TableviewSidepanelBook {

    public static VBox create(String imageCover, ArrayList<String> basicInfo) {
        VBox vBox = new VBox(10);

        HBox hBox = new HBox(40);
        hBox.setPadding(new Insets(10, 10, 10, 10));


        URL resource = TableviewSidepanelBook.class.getResource("/landkreuzer/de/foliox/" + imageCover);
        if (resource != null) {
            ImageView img = new ImageView(new Image(resource.toExternalForm()));
            img.setPreserveRatio(true);
            img.setFitWidth(80);

            hBox.getChildren().add(img);
            //vBox.getChildren().add(img);
        } else {
            System.out.println("Bild nicht gefunden: " + imageCover);
        }

        VBox betterVBox = new VBox();
        for (String info : basicInfo) {
            Label label = new Label(info);
            betterVBox.getChildren().add(label);

            //vBox.getChildren().add(label);
        }
        hBox.getChildren().add(betterVBox);

        vBox.getChildren().add(hBox);

        return vBox;
    }
}