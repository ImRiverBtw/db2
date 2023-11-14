package nl.hva.ict.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.hva.ict.MainApplication;
import nl.hva.ict.models.Reiziger;
import nl.hva.ict.views.ReizigersView;
import nl.hva.ict.views.View;

/**
 * ReizigerController
 * @author HBO-ICT
 */
public class ReizigerController extends Controller {

    private final ReizigersView reizigersView;

    public ReizigerController() {
        reizigersView = new ReizigersView();
        reizigersView.getReizigersViewListView().getSelectionModel().selectedItemProperty()
                .addListener(e -> getItemsInFields());
        reizigersView.getComboReistSamenMet().getSelectionModel().selectedItemProperty()
                .addListener(e -> getItemsComboBox());
        reizigersView.getBtSave().setOnAction(e -> save());
        reizigersView.getBtUpdateData().setOnAction(e -> refreshData());
        reizigersView.getBtNew().setOnAction(e -> insert());
        reizigersView.getBtDelete().setOnAction(e -> delete());
        loadData();
    }

    private void loadData() {
        //haal de waardes op uit de database voor MySQL
        ObservableList<Reiziger> reizigers = FXCollections.observableArrayList(MainApplication.getMySQLReizigers().getAll());

        // voor NOSQL
        // reizigers = FXCollections.observableArrayList(MainApplication.getMongoDBReizigers().getAll());

        reizigersView.getReizigersViewListView().setItems(reizigers);
        reizigersView.getComboReistSamenMet().getSelectionModel().select(null);
    }


    private void refreshData() {
        MainApplication.getMySQLReizigers().reload();
    }

    private void save() {
        // bewaar (update) record
    }

    private void delete() {
        // delete dit record
    }

    private void insert() {
        //Voeg toe
    }

    private void getItemsInFields() {
        Reiziger currentReiziger = reizigersView.getReizigersViewListView().getSelectionModel().getSelectedItem();
        reizigersView.getTxtReizigersCode().setText(currentReiziger.getReizigersCode());
        reizigersView.getTxtVoornaam().setText(currentReiziger.getVoornaam());
        reizigersView.getTxtAchternaam().setText(currentReiziger.getAchternaam());
        reizigersView.getTxtAdres().setText(currentReiziger.getAdres());
        reizigersView.getTxtPostcode().setText(currentReiziger.getPostcode());
        reizigersView.getTxtPlaats().setText(currentReiziger.getPlaats());
        reizigersView.getTxtLand().setText(currentReiziger.getLand());
    }


    /**
     * Nog niets mee gedaan
     */
    private void getItemsComboBox() {

    }

    /**
     * Methode om de view door te geven zoals dat ook bij OOP2 ging
     * @return View
     */
    @Override
    public View getView() {
        return reizigersView;
    }
}
