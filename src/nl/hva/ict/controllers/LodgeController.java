package nl.hva.ict.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.hva.ict.MainApplication;
import nl.hva.ict.models.Lodge;
import nl.hva.ict.views.LodgeView;
import nl.hva.ict.views.View;

/**
 * Controller voor de Lodge view
 * @author HBO-ICT
 */
public class LodgeController extends Controller {

    private final LodgeView lodgeView;

    /**
     * Constructor
     */
    public LodgeController() {

        // Maak instance van je view
        lodgeView = new LodgeView();

        // luister naar wijzigingen in de listview en ga naar de functie getItemsInFields() als er een item wordt geselecteerd
        lodgeView.getLodgeViewListView().getSelectionModel().selectedItemProperty()
                .addListener(e -> getItemsInFields());

        // Set wat acties als op de buttons wordt geklikt
        lodgeView.getBtSave().setOnAction(e -> save());
        lodgeView.getBtUpdateData().setOnAction(e -> refreshData());
        lodgeView.getBtNew().setOnAction(e -> insert());
        lodgeView.getBtDelete().setOnAction(e -> delete());

        //haal de waardes op uit de database
        ObservableList<Lodge> lodges = FXCollections.observableArrayList(MainApplication.getMySQLLodge().getAll());
        lodgeView.getLodgeViewListView().setItems(lodges);
    }

    /**
     * Opnieuw laden van de data
     */
    private void refreshData() {
        MainApplication.getMySQLLodge().reload();
    }

    /**
     * Bewaar een lodge
     */
    private void save() {
        // bewaar (update) record
    }

    /**
     * Verwijder een lodge
     */
    private void delete() {
        // delete dit record
    }

    /**
     * Maak alle velden lees
     */
    private void insert() {
        //Voeg toe
    }

    /**
     * Set alle velden als er een object in de Listview is aangeklikt
     */
    private void getItemsInFields() {
        Lodge currentLodge = lodgeView.getLodgeViewListView().getSelectionModel().getSelectedItem();
        lodgeView.getTxtAccommodatieCode().setText((currentLodge.getAccommodatieCode()));
        lodgeView.getTxtNaam().setText(currentLodge.getNaam());
        lodgeView.getTxtStad().setText(currentLodge.getStad());
        lodgeView.getTxtLand().setText(currentLodge.getLand());
        lodgeView.getTxtKamertype().setText(currentLodge.getKamer());
        lodgeView.getTxtAantalPersonen().setText((String.valueOf(currentLodge.getPersonen())));
        lodgeView.getTxtPrijsPerWeek().setText(String.valueOf(currentLodge.getPrijsPerWeek()));
        lodgeView.getCheckAutohuur().setSelected(currentLodge.isAutoHuren());
    }

    /**
     * Methode om de view door te geven zoals dat ook bij OOP2 ging
     * @return View
     */
    @Override
    public View getView() {
        return lodgeView;
    }
}
