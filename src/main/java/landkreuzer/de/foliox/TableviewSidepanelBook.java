package landkreuzer.de.foliox;

import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;

public class TableviewSidepanelBook extends ListView<TableviewSidepanelBook.Entry> {

    public record Entry(String name) {}
    private Image cover;

    public static TableviewSidepanelBook create(String imageCover, ArrayList<String> basicInfo) {
        TableviewSidepanelBook infoTable = new TableviewSidepanelBook();

        String url = String.valueOf(TableviewSidepanelBook.class.getResource("/landkreuzer/de/foliox/" + imageCover));

        System.out.println(imageCover + "end");
        System.out.println(url);


        ImageView img = null;

        if (imageCover != null) {
            infoTable.cover = new Image(url);
            img = new ImageView(infoTable.cover);
        }

        VBox box = new VBox();
        box.setMinWidth(400);
        box.getChildren().addAll(img);


        return infoTable;
    }
}
