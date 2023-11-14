package nl.hva.ict.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.hva.ict.MainApplication;
import nl.hva.ict.models.Landen;
import nl.hva.ict.views.LandenInformatieView;
import nl.hva.ict.views.View;

/**
 * Controller om de landenpagina te bedienen
 * @author HBO-ICT
 */
public class LandeninformatieController extends Controller {

    private final LandenInformatieView landenInformatieView;
    private final String[] vragen = {"In welke landen spreken ze Engels ?", "In welke landen spreken ze Frans ?", "In welke landen kan je met euro's betalen ?", "Welke landen liggen er in West-Europa ?", "Welke landen liggen er in Afrika ?", "Hoeveel inwoners zijn er in Oost Afrika?"};

    /**
     * Voeg listeners toe om om te reageren op wat de gebruiker doet
     * */
    public LandeninformatieController() {
        landenInformatieView = new LandenInformatieView();
        // Voeg de vragen aan de combobox toe
        landenInformatieView.getComboBox().setItems(FXCollections.observableArrayList(this.vragen));
        // luister naar wijzigingen in de combobox
        landenInformatieView.getComboBox().getSelectionModel().selectedItemProperty().addListener(event -> BeantwoordVraag());
        // luister naar de checkbox en reageer als die wordt aan of uitgezet.
        landenInformatieView.getCheckBox().selectedProperty().addListener(event -> BeantwoordVraag());
    }

    /**
     * Methode om de juiste data te raadplegen. Het antwoord wordt in de arraylist gezet van de DAO en op het einde gekoppeld aan de listview
     */
    private void BeantwoordVraag() {

        // Effe checken of de combobox wel geselecteerd is. Misschien heb je wel alleen de "Alleen uit Afrika" checkbox aangeklikt
        // zonder een selectie te maken in de combobox. Dit geeft anders een null pointer error.
        if (landenInformatieView.getComboBox().getSelectionModel().getSelectedItem() != null) {

            // Lees de velden.
            String selectedItem = landenInformatieView.getComboBox().getSelectionModel().getSelectedItem().toString();
            boolean alleenAfrika = landenInformatieView.getCheckBox().isSelected();

            // Selecteer de juiste methode bij de juiste vraag uit de combobox
            if (selectedItem == vragen[0]) {
                MainApplication.getMongoLandenInformatie().wieSpreekt("English", alleenAfrika);
            } else if (selectedItem == vragen[1]) {
                MainApplication.getMongoLandenInformatie().wieSpreekt("French", alleenAfrika);
            } else if (selectedItem == vragen[2]) {
                MainApplication.getMongoLandenInformatie().waarBetaalJeMet("Euro", alleenAfrika);
            } else if (selectedItem == vragen[3]) {
                MainApplication.getMongoLandenInformatie().welkeLandenZijnErIn("Western Europe");
            } else if (selectedItem == vragen[4]) {
                MainApplication.getMongoLandenInformatie().welkeLandenZijnErIn("Africa");
            } else if (selectedItem == vragen[5]) {
                int totaalInwoners = MainApplication.getMongoLandenInformatie().hoeveelInwonersOostAfrika();
                landenInformatieView.getTextArea().setText("Er wonen in Oost- Afrika in totaal " + totaalInwoners + " inwoners");
            }

            // Haal de actuele data op
            ObservableList<Landen> landen = FXCollections.observableArrayList(MainApplication.getMongoLandenInformatie().getAll());

            // stop dit in de lijstView
            landenInformatieView.getLandenInformatieListView().setItems(landen);
        }
    }

    /**
     * Methode om de view door te geven zoals dat ook bij OOP2 ging
     * @return View
     */
    @Override
    public View getView() {
        return landenInformatieView;
    }
}
