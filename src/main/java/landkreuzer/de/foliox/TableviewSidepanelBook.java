package landkreuzer.de.foliox;

import javafx.scene.control.ListView;

import java.io.File;
import java.util.ArrayList;

public class TableviewSidepanelBook extends ListView<TableviewSidepanelBook.Entry> {

    public record Entry(String name) {}

    public static TableviewSidepanelBook create(File imageCover, ArrayList<String> basicInfo) {
        TableviewSidepanelBook infoTable = new TableviewSidepanelBook();
        for (String info : basicInfo) {
            infoTable.getItems().add(new Entry(info));
        }
        return infoTable;
    }
}
