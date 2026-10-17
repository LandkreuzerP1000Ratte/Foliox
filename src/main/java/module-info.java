module com.example.foliox {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.foliox to javafx.fxml;
    exports com.example.foliox;
}