package landkreuzer.de.foliox;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;

import static java.awt.SystemColor.info;

public class TableviewSidepanelBook {

    public static VBox create(String imageCover, ArrayList<String> basicInfo) {
        VBox box = new VBox(10);
        box.setBackground(Background.fill(Color.AQUA)); // nur zum Testen, später entfernen
        box.setPadding(double 10, double 10, double 10, 10);

        URL resource = TableviewSidepanelBook.class.getResource("/landkreuzer/de/foliox/" + imageCover);
        if (resource != null) {
            ImageView img = new ImageView(new Image(resource.toExternalForm()));
            img.setPreserveRatio(true);
            img.setFitWidth(200);
            box.getChildren().add(img);
        } else {
            System.out.println("Bild nicht gefunden: " + imageCover);
        }

        for (String info : basicInfo) {
            Label label = new Label(info);
            box.getChildren().add(label);
        }

        //VBox.setVgrow(table, Priority.ALWAYS);
        return box;
    }
}