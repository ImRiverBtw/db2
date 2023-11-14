package nl.hva.ict.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.hva.ict.models.Lodge;

public class LodgeView extends View {

    private final GridPane rootPane;
    private TextField txtAccommodatieCode;
    private TextField txtNaam;
    private TextField txtStad;
    private TextField txtLand;
    private TextField txtKamertype;
    private TextField txtPrijsPerWeek;
    private TextField txtAantalPersonen;
    private CheckBox checkAutohuur;
    private Button btDelete;
    private Button btNew;
    private Button btSave;
    private Button btUpdateData;
    private ListView<Lodge> lodgeViewListView;

    public LodgeView() {
        rootPane = new GridPane();
        createRoot();
    }

    private void createRoot() {
        //layout
        rootPane.setPadding(new Insets(10, 10, 10, 10));
        rootPane.setHgap(5);
        rootPane.setVgap(5);

        // maak velden
        txtAccommodatieCode = new TextField();
        txtNaam = new TextField();
        txtStad = new TextField();
        txtKamertype = new TextField();
        txtStad = new TextField();
        txtLand = new TextField();
        txtPrijsPerWeek = new TextField();
        txtAantalPersonen = new TextField();
        checkAutohuur = new CheckBox();


        //zet velden in leftPane
        GridPane leftPane = new GridPane();
        leftPane.setMinWidth(400);
        leftPane.setPadding(new Insets(10, 10, 10, 10));
        leftPane.setHgap(5);
        leftPane.setVgap(5);
        leftPane.add(new Label("Accommodatiecode"), 0, 0);
        leftPane.add(txtAccommodatieCode, 1, 0);
        leftPane.add(new Label("Naam"), 0, 1);
        leftPane.add(txtNaam, 1, 1);
        leftPane.add(new Label("Stad"), 0, 2);
        leftPane.add(txtStad, 1, 2);
        leftPane.add(new Label("Land"), 0, 3);
        leftPane.add(txtLand, 1, 3);
        leftPane.add(new Label("Prijs per week"), 0, 4);
        leftPane.add(txtPrijsPerWeek, 1, 4);
        leftPane.add(new Label("Kamertype"), 0, 5);
        leftPane.add(txtKamertype, 1, 5);
        leftPane.add(new Label("Maximaal aantal personen"), 0, 6);
        leftPane.add(txtAantalPersonen, 1, 6);
        leftPane.add(new Label("Autohuur inbegrepen"), 0, 7);
        leftPane.add(checkAutohuur, 1, 7);

        // voeg buttons toe
        btDelete = new Button("Verwijder");
        btNew = new Button("Insert");
        btSave = new Button("Update");
        HBox buttonBox = new HBox(btDelete, btNew, btSave);
        buttonBox.setSpacing(5);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        leftPane.add(buttonBox, 1, 8);

        //voeg leftPane toe aan rootPane
        rootPane.add(leftPane, 0, 0);

        // maak een rightPane
        lodgeViewListView = new ListView<>();
        btUpdateData = new Button(" Refresh lijst ");
        VBox rightPane = new VBox();
        rightPane.setMinWidth(300);
        rightPane.setSpacing(5);
        rightPane.getChildren().addAll(lodgeViewListView, btUpdateData);
        rightPane.setAlignment(Pos.CENTER_RIGHT);

        //voeg rightPane toe aan rootPane
        rootPane.add(rightPane, 1, 0);
    }

    public TextField getTxtAccommodatieCode() {
        return txtAccommodatieCode;
    }

    public TextField getTxtNaam() {
        return txtNaam;
    }

    public TextField getTxtStad() {
        return txtStad;
    }

    public TextField getTxtLand() {
        return txtLand;
    }

    public TextField getTxtKamertype() {
        return txtKamertype;
    }

    public TextField getTxtPrijsPerWeek() {
        return txtPrijsPerWeek;
    }

    public TextField getTxtAantalPersonen() {
        return txtAantalPersonen;
    }

    public CheckBox getCheckAutohuur() {
        return checkAutohuur;
    }

    public Button getBtUpdateData() {
        return btUpdateData;
    }

    public GridPane getRootPane() {
        return rootPane;
    }

    public Button getBtDelete() {
        return btDelete;
    }

    public Button getBtNew() {
        return btNew;
    }

    public Button getBtSave() {
        return btSave;
    }

    public Button getBtClose() {
        return btUpdateData;
    }

    public ListView<Lodge> getLodgeViewListView() {
        return lodgeViewListView;
    }

    @Override
    public Parent getRoot() {
        return rootPane;
    }
}
