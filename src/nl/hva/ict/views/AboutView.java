package nl.hva.ict.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import nl.hva.ict.MainApplication;

public class AboutView extends View {

    private final BorderPane rootPane;

    public AboutView() {
        rootPane = new BorderPane();
        createRoot();
    }

    private void createRoot() {
        TextArea melding = new TextArea();
        melding.setStyle("-fx-border-color: black ; -fx-border-width: 2px ;");
        melding.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        melding.setEditable(false);
        melding.setText(MainApplication.getTITLE() + " + een leuke tekst");
        rootPane.setCenter(melding);
    }

    @Override
    public Parent getRoot() {
        return rootPane;
    }
}