package landkreuzer.de.foliox;

import javafx.scene.control.ListView;

public class TableviewSidepanelBook extends ListView<TableviewSidepanelBook.Entry>{

    public record Entry(String name) {}

    public TableviewSidepanelBook(String[] content) {
        getItems().add(new Entry("Test"));
    }
}
