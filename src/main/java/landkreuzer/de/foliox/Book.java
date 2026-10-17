package landkreuzer.de.foliox;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final StringProperty barcode = new SimpleStringProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty autor = new SimpleStringProperty();
    private final StringProperty verlag = new SimpleStringProperty();

    public Book(String barcode, String title, String autor, String verlag) {
        this.barcode.set(barcode);
        this.title.set(title);
        this.autor.set(autor);
        this.verlag.set(verlag);
    }

    public StringProperty barcodeProperty() { return barcode; }
    public StringProperty titleProperty() { return title; }
    public StringProperty autorProperty() { return autor; }
    public StringProperty verlagProperty() { return verlag; }

    public String getBarcode() { return barcode.get(); }
    public String getTitle() { return title.get(); }
    public String getAutor() { return autor.get(); }
    public String getVerlag() { return verlag.get(); }
}
