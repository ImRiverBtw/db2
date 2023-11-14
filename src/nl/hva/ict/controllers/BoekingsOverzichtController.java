package nl.hva.ict.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.hva.ict.MainApplication;
import nl.hva.ict.models.BoekingsOverzicht;
import nl.hva.ict.views.BoekingsoverzichtView;
import nl.hva.ict.views.View;


/**
 * Controller voor het boekingsoverzicht
 * @author HBO-ICT
 */
public class BoekingsOverzichtController extends Controller {

    private final BoekingsoverzichtView boekingsoverzichtView;

    /**
     * Constructor
     */
    public BoekingsOverzichtController() {
        // Maak instance van scherm
        boekingsoverzichtView = new BoekingsoverzichtView();

        // Maak verbinding met de DAO, haal arrayList op met alle boekingen en stop dit in een observable list
        ObservableList<BoekingsOverzicht> observableBoeking = FXCollections.observableList(MainApplication.getMySQLBoekingsOverzicht().getAll());
        // Zet alle informatie in de listview
        boekingsoverzichtView.getBoekingsOverzichtListView().setItems(observableBoeking);
    }

    /**
     * Methode om de view door te geven zoals dat ook bij OOP2 ging
     * @return View
     */
    @Override
    public View getView() {
        return boekingsoverzichtView;
    }
}
