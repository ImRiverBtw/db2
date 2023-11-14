package nl.hva.ict.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.hva.ict.MainApplication;
import nl.hva.ict.models.BoekingsOverzicht;
import nl.hva.ict.models.Reiziger;
import nl.hva.ict.views.BoekingsoverzichtPerKlantView;
import nl.hva.ict.views.View;


/**
 * Boekinsoverzicht DAO
 * @author HBO-ICT
 */
public class BoekingsOverzichtPerKlantController extends Controller {

    private final BoekingsoverzichtPerKlantView boekingsoverzichtPerKlantView;

    /**
     * Constructor
     */
    public BoekingsOverzichtPerKlantController() {

        // Maak instance van view
        boekingsoverzichtPerKlantView = new BoekingsoverzichtPerKlantView();

        // luister naar wijzigingen in de combobox en ga naar de functie ListViewPerKlant() als er iets wijzigt.
        boekingsoverzichtPerKlantView.getComboBox().getSelectionModel().selectedItemProperty().addListener(event -> ListViewPerKlant());

        // Maak verbinding met de DAO, haal arrayList op met alle boekingen en stop dit in een observable list
        ObservableList<Reiziger> reizigers = FXCollections.observableArrayList(MainApplication.getMySQLReizigers().getAll());

        // Update de listview
        boekingsoverzichtPerKlantView.getComboBox().setItems(reizigers);
    }


    private void ListViewPerKlant() {

        // Wat is geselecteerd?
        Reiziger selectedReiziger = (Reiziger) boekingsoverzichtPerKlantView.getComboBox().getSelectionModel().getSelectedItem();

        // Haal data op van deze reiziger
        ObservableList<BoekingsOverzicht> BoekingsOverzichtList = FXCollections.observableArrayList(MainApplication.getMySQLBoekingsOverzicht().getBoekingVoor(selectedReiziger.getReizigersCode()));

        // Zet in listview
        boekingsoverzichtPerKlantView.getBoekingsOverzichtListView().setItems(BoekingsOverzichtList);
    }

    /**
     * Methode om de view door te geven zoals dat ook bij OOP2 ging
     * @return View
     */
    @Override
    public View getView() {
        return boekingsoverzichtPerKlantView;
    }
}
