package nl.hva.ict.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.hva.ict.MainApplication;
import nl.hva.ict.models.Accommodatie;
import nl.hva.ict.models.Reiziger;
import nl.hva.ict.views.GeboektOpView;
import nl.hva.ict.views.View;
import java.time.LocalDate;

/**
 * Controller voor de pagina geboekt.
 * @author HBO-ICT
 */
public class BoekingsGeboektOpController extends Controller {

    private final GeboektOpView geboektOpView;

    /**
     * Constructor 
     */
    public BoekingsGeboektOpController() {
        // Maak instance van view.
        geboektOpView = new GeboektOpView();

        // Roep de DAO aan die weer alle data uit de database haalt voor de accommodatie
        ObservableList<Accommodatie> accommodaties = FXCollections.observableArrayList(MainApplication.getMySQLAccommodatie().getAll());

        //voeg alles toe aan de listview
        geboektOpView.getComboBoxAccommodaties().setItems(accommodaties);

        // luister naar wijzigingen in de combobox en ga naar de methode ListAccommodaties() als er iets veranderd.
        geboektOpView.getComboBoxAccommodaties().getSelectionModel().selectedItemProperty().addListener(event -> ListAccommodaties());

        // luister naar wijzigingen in de datepicker en ga naar de methode ListAccommodaties() als er iets veranderd.
        geboektOpView.getDatePicker().valueProperty().addListener(event -> ListAccommodaties());
    }


    /**
     * Welke accommodatie is er geboekt op welke datum?
     */
    private void ListAccommodaties() {
        // welke accommodate is geselecteerd in de combobox?
        if (geboektOpView.getComboBoxAccommodaties().getSelectionModel().getSelectedItem() != null) {
            Accommodatie accommodatieSelected = (Accommodatie) geboektOpView.getComboBoxAccommodaties().getSelectionModel().getSelectedItem();

            // Haal de AccommodatieCode op van het geselecteerde model
            String accommodatieCode = accommodatieSelected.getAccommodatieCode();

            // Welke datum is in de view geselecteerd?
            LocalDate datum = geboektOpView.getDatePicker().getValue();

            // haal de info op uit de DAO
            ObservableList<Reiziger> reizigers = FXCollections.observableArrayList(MainApplication.getMySQLBoekingsOverzicht().GeboektOp(accommodatieCode, datum));

            // Zet de data het in de listview
            geboektOpView.getBoekingsOverzichtListView().setItems(reizigers);
        }
    }

    /**
     * Methode om de view door te geven zoals dat ook bij OOP2 ging
     * @return View
     */
    @Override
    public View getView() {
        return geboektOpView;
    }
}
