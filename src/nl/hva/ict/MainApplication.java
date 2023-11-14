package nl.hva.ict;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nl.hva.ict.controllers.MainController;
import nl.hva.ict.data.MongoDB.MongoLandeninformatie;
import nl.hva.ict.data.MongoDB.MongoReizigers;
import nl.hva.ict.data.MySQL.*;

/**
 * Main class met applicatie logica
 * @author HBO-ICT
 */
public class MainApplication extends Application {

    private static final String TITLE = "Practicumopdracht DB2 - Startproject - versie 2022 ";

    //MySQL
    private static final String MYSQL_HOST = "jdbc:mysql://localhost:3306/big_five_safari?autoReconnect=true&serverTimezone=Europe/Amsterdam&useSSL=False";
    private static final String MYSQL_USERNAME = "admin";  // vul hier je DB username in
    private static final String MYSQL_PASSWORD = "root"; // vul hier je DB wachtwoord in

    //Mongo NoSQL
    private static final String NOSQL_HOST = ""; // Vul hier je MongoDB gegevens in. Iets met mongodb+srv://......
    private static final String NOSQL_DATABASE = ""; // Vul hier je database gegevens in;
    
    //Data models
    private static final MySQLReizigers mySQLReizigers = new MySQLReizigers();
    private static final MySQLLodge mySQLLodge = new MySQLLodge();
    private static final MySQLHotel mySQLHotel = new MySQLHotel();
    private static final MySQLAccommodatie mySQLAccommodatie = new MySQLAccommodatie();
    private static final MySQLBoekingsOverzicht mySQLBoekingsOverzicht = new MySQLBoekingsOverzicht();
    private static final MongoReizigers mongoDBReizigers = new MongoReizigers();
    private static final MongoLandeninformatie mongoLandenInformatie = new MongoLandeninformatie();
    //JavaFX
    private static Stage stage;
    private static MainController mainController;

    public static void switchController(Parent pane) {
        mainController.getBorderPane().setCenter(pane);
    }

    public static String getMysqlHost() {
        return MYSQL_HOST;
    }

    public static String getMysqlUsername() {
        return MYSQL_USERNAME;
    }

    public static String getMysqlPassword() {
        return MYSQL_PASSWORD;
    }

    public static String getNosqlHost() {
        return NOSQL_HOST;
    }

    public static String getNosqlDatabase() {
        return NOSQL_DATABASE;
    }

    public static String getTITLE() {
        return TITLE;
    }

    public static MySQLReizigers getMySQLReizigers() {
        return mySQLReizigers;
    }

    public static MySQLLodge getMySQLLodge() {
        return mySQLLodge;
    }

    public static MySQLHotel getMySQLHotel() {
        return mySQLHotel;
    }

    public static MySQLBoekingsOverzicht getMySQLBoekingsOverzicht() {
        return mySQLBoekingsOverzicht;
    }

    public static MySQLAccommodatie getMySQLAccommodatie() {
        return mySQLAccommodatie;
    }

    public static MongoReizigers getMongoDBReizigers() {
        return mongoDBReizigers;
    }

    public static MongoLandeninformatie getMongoLandenInformatie() {
        return mongoLandenInformatie;
    }

    /**
     * Opstarten JavaFX applicatie.
     * Kijk voor meer informatie over JavaFX ook bij OOP2
     * @param stage de stage welke gebruikt wordt
     */
    @Override
    public void start(Stage stage) {
        MainApplication.stage = stage;
        MainApplication.stage.setTitle(TITLE);
        int WIDTH = 800;
        MainApplication.stage.setWidth(WIDTH);
        int HEIGHT = 800;
        MainApplication.stage.setHeight(HEIGHT);
        mainController = new MainController();

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        MainApplication.stage.setX((primaryScreenBounds.getWidth() - WIDTH) / 2f);
        MainApplication.stage.setY((primaryScreenBounds.getHeight() - HEIGHT) / 2f);

        MainApplication.stage.setMinWidth(750);
        MainApplication.stage.setMinHeight(600);
        MainApplication.stage.setScene(new Scene(mainController.getView().getRoot()));
        stage.show();
    }
}

