package landkreuzer.de.foliox;

import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;
import java.util.ArrayList;

public class TableviewSidepanelBook extends ListView<TableviewSidepanelBook.Entry> {

    public record Entry(String name) {}
    private Image cover;

    public static TableviewSidepanelBook create(File imageCover, ArrayList<String> basicInfo) {
        TableviewSidepanelBook infoTable = new TableviewSidepanelBook();

        HBox box = new HBox();
        if (infoTable.cover != null) {
            ImageView coverView = new ImageView(infoTable.cover);
            coverView.setFitWidth(120);
            coverView.setPreserveRatio(true);
            box.getChildren().add(coverView);
            box.setAlignment(Pos.CENTER_LEFT);
        }

        /*for (String info : basicInfo) {
            infoTable.getItems().add(new Entry(info));
        }*/
        return infoTable;
    }
}
