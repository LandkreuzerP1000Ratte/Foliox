module landkreuzer.de.foliox {
    requires javafx.controls;
    requires javafx.fxml;


    opens landkreuzer.de.foliox to javafx.fxml;
    exports landkreuzer.de.foliox;
}