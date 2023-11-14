package nl.hva.ict.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import nl.hva.ict.models.Reiziger;

public class GeboektOpView extends View {

    private final GridPane rootPane;
    private final ListView<Reiziger> boekingsOverzichtListView;
    private DatePicker datePicker;
    private ComboBox comboBoxAccommodaties;

    public GeboektOpView() {
        rootPane = new GridPane();
        boekingsOverzichtListView = new ListView<>();
        createRoot();
    }

    private void createRoot() {
        datePicker = new DatePicker();
        comboBoxAccommodaties = new ComboBox();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Datum"), 0, 0);
        gridPane.add(datePicker, 1, 0);
        gridPane.add(new Label("Accommodatie "), 2, 0);
        gridPane.add(comboBoxAccommodaties, 3, 0);
        boekingsOverzichtListView.setPrefWidth(800);
        boekingsOverzichtListView.setPrefHeight(700);
        VBox vBox = new VBox(gridPane, boekingsOverzichtListView);
        rootPane.getChildren().addAll(vBox);
        rootPane.getChildren().addAll();
    }

    @Override
    public Parent getRoot() {
        return rootPane;
    }

    public GridPane getRootPane() {
        return rootPane;
    }

    public ListView<Reiziger> getBoekingsOverzichtListView() {
        return boekingsOverzichtListView;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public ComboBox getComboBoxAccommodaties() {
        return comboBoxAccommodaties;
    }
}