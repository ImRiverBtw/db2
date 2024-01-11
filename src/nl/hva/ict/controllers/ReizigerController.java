package nl.hva.ict.controllers;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.hva.ict.MainApplication;
import nl.hva.ict.data.MongoDB.MongoReizigers;
import nl.hva.ict.models.Reiziger;
import nl.hva.ict.views.ReizigersView;
import nl.hva.ict.views.View;
import org.bson.Document;

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
        //ObservableList<Reiziger> reizigers = FXCollections.observableArrayList(MainApplication.getMySQLReizigers().getAll());

        // voor NOSQL
        ObservableList<Reiziger> reizigers = FXCollections.observableArrayList(MainApplication.getMongoDBReizigers().getAll());
        reizigersView.getReizigersViewListView().setItems(reizigers);
        reizigersView.getComboReistSamenMet().getSelectionModel().select(null);
    }


    private void refreshData() {
        MainApplication.getMongoDBReizigers().load();
        ObservableList<Reiziger> reizigers = FXCollections.observableArrayList(MainApplication.getMongoDBReizigers().getAll());
        reizigersView.getReizigersViewListView().getItems().clear(); // Clear the existing items
        reizigersView.getReizigersViewListView().refresh();
        reizigersView.getReizigersViewListView().setItems(reizigers);
    }

    private void save() {
        // bewaar (update) record
            Reiziger selectedReiziger = reizigersView.getReizigersViewListView().getSelectionModel().getSelectedItem();

            if (selectedReiziger != null) {
                selectedReiziger.setReizigersCode(reizigersView.getTxtReizigersCode().getText());
                selectedReiziger.setVoornaam(reizigersView.getTxtVoornaam().getText());
                selectedReiziger.setAchternaam(reizigersView.getTxtAchternaam().getText());
                selectedReiziger.setAdres(reizigersView.getTxtAdres().getText());
                selectedReiziger.setPostcode(reizigersView.getTxtPostcode().getText());
                selectedReiziger.setPlaats(reizigersView.getTxtPlaats().getText());
                selectedReiziger.setLand(reizigersView.getTxtLand().getText());

                Document filter = new Document("reizigers_code", selectedReiziger.getReizigersCode());
                Document updateDocument = new Document("$set", new Document()
                        .append("reizigers_code", selectedReiziger.getReizigersCode())
                        .append("voornaam", selectedReiziger.getVoornaam())
                        .append("achternaam", selectedReiziger.getAchternaam())
                        .append("adres", selectedReiziger.getAdres())
                        .append("postcode", selectedReiziger.getPostcode())
                        .append("plaats", selectedReiziger.getPlaats())
                        .append("land", selectedReiziger.getLand())
                );
                try {
                    MainApplication.getMongoDBReizigers().collection.updateOne(filter, updateDocument);
                    refreshData();
                } catch (MongoException e) {
                    e.printStackTrace();
                }
            }
        }


    private void delete() {
        Reiziger selectedReiziger = reizigersView.getReizigersViewListView().getSelectionModel().getSelectedItem();
        if (selectedReiziger != null) {
            Document filter = new Document("reizigers_code", selectedReiziger.getReizigersCode());
            try {
                MainApplication.getMongoDBReizigers().collection.deleteOne(filter);
                refreshData();
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }

    private void insert() {
        Reiziger newReiziger = getReizigerInfo();
        Document reizigerDoc = new Document("reizigers_code", newReiziger.getReizigersCode())
                .append("voornaam", newReiziger.getVoornaam())
                .append("achternaam", newReiziger.getAchternaam())
                .append("adres", newReiziger.getAdres())
                .append("postcode", newReiziger.getPostcode())
                .append("plaats", newReiziger.getPlaats())
                .append("land", newReiziger.getLand())
                .append("hoofdreiziger", newReiziger.getHoofdreiziger());
        try {
            MainApplication.getMongoDBReizigers().collection.insertOne(reizigerDoc);
            refreshData();
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    private void getItemsInFields() {
        Reiziger currentReiziger = reizigersView.getReizigersViewListView().getSelectionModel().getSelectedItem();
        if (currentReiziger != null){
            reizigersView.getTxtReizigersCode().setText(currentReiziger.getReizigersCode());
            reizigersView.getTxtVoornaam().setText(currentReiziger.getVoornaam());
            reizigersView.getTxtAchternaam().setText(currentReiziger.getAchternaam());
            reizigersView.getTxtAdres().setText(currentReiziger.getAdres());
            reizigersView.getTxtPostcode().setText(currentReiziger.getPostcode());
            reizigersView.getTxtPlaats().setText(currentReiziger.getPlaats());
            reizigersView.getTxtLand().setText(currentReiziger.getLand());
        }

    }

    private Reiziger getReizigerInfo(){
        Reiziger currentReiziger;
        String reizigersCode = reizigersView.getTxtReizigersCode().getText();
        String voornaam = reizigersView.getTxtVoornaam().getText();
        String achternaam = reizigersView.getTxtAchternaam().getText();
        String adres = reizigersView.getTxtAdres().getText();
        String pCode = reizigersView.getTxtPostcode().getText();
        String plaats = reizigersView.getTxtPlaats().getText();
        String land = reizigersView.getTxtLand().getText();
        String hReiziger = "";
        currentReiziger = new Reiziger(reizigersCode, voornaam, achternaam, adres, pCode, plaats, land, hReiziger);
        return  currentReiziger;
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
