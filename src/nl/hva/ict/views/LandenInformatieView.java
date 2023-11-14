package nl.hva.ict.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import nl.hva.ict.models.Landen;

public class LandenInformatieView extends View {

    private final GridPane rootPane;
    private final ComboBox comboBox;
    private final ListView<Landen> landenInformatieListView;
    private final CheckBox checkBox;
    private final TextArea textArea;

    public LandenInformatieView() {
        this.rootPane = new GridPane();
        this.comboBox = new ComboBox();
        this.checkBox = new CheckBox();
        this.textArea = new TextArea();
        this.landenInformatieListView = new ListView<>();
        createRoot();
    }

    private void createRoot() {
        landenInformatieListView.setPrefWidth(800);
        landenInformatieListView.setPrefHeight(800);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 20, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(new Label("Vraag: "), 2, 0);
        gridPane.add(comboBox, 3, 0);
        gridPane.add(new Label("Alleen in Afrika: "), 2, 1);
        gridPane.add(checkBox, 3, 1);
        gridPane.add(new Label("Informatie "), 2, 2);
        gridPane.add(textArea, 3, 2);

        VBox vBox = new VBox(gridPane, landenInformatieListView);
        rootPane.getChildren().addAll(vBox);
    }

    @Override
    public Parent getRoot() {
        return rootPane;
    }

    public ListView<Landen> getLandenInformatieListView() {
        return landenInformatieListView;
    }

    public ComboBox getComboBox() {
        return comboBox;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public TextArea getTextArea() {
        return textArea;
    }
}