module landkreuzer.de.foliox {
    requires javafx.fxml;
    requires javafx.controls;
    requires jdk.javadoc;


    opens landkreuzer.de.foliox to javafx.fxml;
    exports landkreuzer.de.foliox;
}