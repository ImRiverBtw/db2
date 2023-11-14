package nl.hva.ict.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.hva.ict.MainApplication;

public class MainView extends View {

    private final MenuBar menuBar;
    private BorderPane borderPane;
    private Menu fileMenu;
    private MenuItem closeMenuItem;
    private MenuItem startschermMenuItem;
    private Menu stamMenu;
    private MenuItem hotelMenuItem;
    private MenuItem lodgeMenuItem;
    private MenuItem reizigersMenuItem;
    private Menu boekingenMenu;
    private MenuItem overzichtAlleBoekingen;
    private MenuItem overzichtPerKlant;
    private MenuItem overzichtGeboektOp;
    private Menu informatieMenu;
    private MenuItem landenInformatie;

    private Menu helpMenu;
    private MenuItem aboutMenuItem;
    private Label message;

    public MainView() {
        menuBar = createMenuBar();
        borderPane = createBorderPane();
    }

    private MenuBar createMenuBar() {
        // File-menu
        fileMenu = new Menu("Bestand");
        startschermMenuItem = new MenuItem("Ga naar beginscherm");
        closeMenuItem = new MenuItem("Afsluiten");
        fileMenu.getItems().addAll(
                startschermMenuItem,
                new SeparatorMenuItem(),
                closeMenuItem
        );

        // Stamgegevens
        stamMenu = new Menu("Stamgegevens");
        hotelMenuItem = new MenuItem("Hotels");
        lodgeMenuItem = new MenuItem("Lodges");
        reizigersMenuItem = new MenuItem("Reizigers");
        stamMenu.getItems().addAll(
                hotelMenuItem,
                lodgeMenuItem,
                reizigersMenuItem
        );

        informatieMenu = new Menu("Landen");
        landenInformatie = new MenuItem("Landen informatie");
        informatieMenu.getItems().addAll(landenInformatie);

        //Reserveringen
        boekingenMenu = new Menu("Boekingen");
        overzichtAlleBoekingen = new MenuItem("Boekingsoverzicht");
        overzichtPerKlant = new MenuItem("Boekingen per klant");
        overzichtGeboektOp = new MenuItem("Geboekt op");
        boekingenMenu.getItems().addAll(overzichtAlleBoekingen, overzichtPerKlant, overzichtGeboektOp);

        //Help-menu
        helpMenu = new Menu("Help");
        aboutMenuItem = new MenuItem("Over de " + MainApplication.getTITLE());
        helpMenu.getItems().addAll(aboutMenuItem);

        //Menubalk
        return new MenuBar(
                fileMenu,
                stamMenu,
                boekingenMenu,
                informatieMenu,
                helpMenu
        );
    }

    public Pane createRoot() {
        return new GridPane();
    }

    private BorderPane createBorderPane() {
        message = new Label();
        message.setStyle("-fx-background-color: #65737e");
        message.setTextFill(Color.WHITE);
        message.setMaxWidth(Double.MAX_VALUE);
        message.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        message.setAlignment(Pos.CENTER);
        message.setFont(new Font("Arial", 16));

        borderPane = new BorderPane();
        borderPane.setTop(message);

        return borderPane;
    }

    @Override
    public Parent getRoot() {
        return new VBox(menuBar, borderPane);
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public Menu getFileMenu() {
        return fileMenu;
    }

    public MenuItem getCloseMenuItem() {
        return closeMenuItem;
    }

    public Menu getStamMenu() {
        return stamMenu;
    }

    public MenuItem getHotelMenuItem() {
        return hotelMenuItem;
    }

    public MenuItem getLodgeMenuItem() {
        return lodgeMenuItem;
    }

    public MenuItem getReizigersMenuItem() {
        return reizigersMenuItem;
    }

    public Menu getBoekingenMenu() {
        return boekingenMenu;
    }

    public MenuItem getOverzichtAlleBoekingen() {
        return overzichtAlleBoekingen;
    }

    public MenuItem getOverzichtPerKlant() {
        return overzichtPerKlant;
    }

    public MenuItem getOverzichtGeboektOp() {
        return overzichtGeboektOp;
    }

    public Menu getHelpMenu() {
        return helpMenu;
    }

    public MenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }

    public Label getMessage() {
        return message;
    }

    public MenuItem getStartschermMenuItem() {
        return startschermMenuItem;
    }

    public Menu getInformatieMenu() {
        return informatieMenu;
    }
}
