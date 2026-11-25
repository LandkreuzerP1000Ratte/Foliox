package landkreuzer.de.foliox;

import java.net.URL;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;


public class TableviewSidepanelBook {


    public static VBox create(String defImageName, Stage primaryStage) {
        VBox root = new VBox(10);
        root.setFillWidth(true);
        root.setPadding(new Insets(10));
        root.setMaxWidth(550);
        root.setPrefWidth(400);

        VBox content = new VBox(20);
        content.setPadding(new Insets(10));
        content.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));

        URL resource = TableviewSidepanelBook.class.getResource("/landkreuzer/de/foliox/" + defImageName);
        if(resource != null) {
            ImageView defImage = new ImageView(new Image(resource.toExternalForm()));
            defImage.setPreserveRatio(true);
            defImage.setFitWidth(150);
            content.getChildren().add(defImage);
        } else {
            Label popupLabel = new Label("Default Image was not found: " + defImageName);
            popupLabel.setStyle("-fx-background-color: #39b0bf; -fx-padding: 8; -fx-border-color: black;");
            popupErrorDisplay(primaryStage, popupLabel);
        }

        // Das war die einzigste Lösung die ich nach 2 Stunden gefunden hab
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        root.getChildren().add(scrollPane);
        return root;
    }


    private static void popupErrorDisplay(Stage stage, Label errorLabel) {

        Popup fileNotFoundPopup = new Popup();

        fileNotFoundPopup.getContent().add(errorLabel);
        fileNotFoundPopup.setAutoHide(true);

        PauseTransition delay = new PauseTransition(Duration.seconds(15));
        delay.setOnFinished(e -> fileNotFoundPopup.hide());
        delay.play();

        fileNotFoundPopup.show(stage, stage.getX() + stage.getWidth() - 40, stage.getY() + stage.getHeight() - 40);
    }
}