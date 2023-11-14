package nl.hva.ict.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import nl.hva.ict.models.BoekingsOverzicht;


public class BoekingsoverzichtPerKlantView extends View {

    private final GridPane rootPane;
    private final ListView<BoekingsOverzicht> boekingsOverzichtListView;
    private final ComboBox comboBox;

    public BoekingsoverzichtPerKlantView() {
        rootPane = new GridPane();
        comboBox = new ComboBox();
        boekingsOverzichtListView = new ListView<>();
        createRoot();
    }

    private void createRoot() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Gebruiker"), 0, 0);
        gridPane.add(comboBox, 1, 0);
        boekingsOverzichtListView.setPrefWidth(800);
        boekingsOverzichtListView.setPrefHeight(700);
        VBox vBox = new VBox(gridPane, boekingsOverzichtListView);
        rootPane.getChildren().addAll(vBox);
    }

    @Override
    public Parent getRoot() {
        return rootPane;
    }

    public GridPane getRootPane() {
        return rootPane;
    }

    public ListView<BoekingsOverzicht> getBoekingsOverzichtListView() {
        return boekingsOverzichtListView;
    }

    public ComboBox getComboBox() {
        return comboBox;
    }
}

