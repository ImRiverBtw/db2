package nl.hva.ict.data.MySQL;

import nl.hva.ict.models.Reiziger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao voor reizigers
 * @author HBO-ICT
 */
public class MySQLReizigers extends MySQL<Reiziger> {

    private final List<Reiziger> reizigers;


    /**
     * Constructor
     */
    public MySQLReizigers() {
        // init arraylist
        reizigers = new ArrayList<>();

        // Laad bij startup
        load();
    }

    /**
     * Doe dit bij het maken van dit object
     */
    private void load() {

        // Voer hier je SQL code in
        String sql = "";

        // Als je nog geen query hebt ingevuld breek dan af om een error te voorkomen.
        if (sql.equals(""))
            return;

        try {
            // Roep de methode aan in de parent class en geen je SQL door
            PreparedStatement ps = getStatement(sql);

            //Voer je query uit en stop het antwoord in een result set
            ResultSet rs = executeSelectPreparedStatement(ps);

            // Loop net zolang als er records zijn
            while (rs.next()) {
                String reizigersCode = rs.getString("reizigerCode");
                String voornaam = rs.getString("voornaam");
                String achternaam = rs.getString("achternaam");
                String adres = rs.getString("adres");
                String postcode = rs.getString("postcode");
                String plaats = rs.getString("plaats");
                String land = rs.getString("land");
                String hoofdreiziger = rs.getString("hoofdreiziger");

                // Maak model aan en voeg toe aan arraylist
                reizigers.add(new Reiziger(reizigersCode, voornaam, achternaam, adres, postcode, plaats, land, hoofdreiziger));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Geen alle reizigers in de arraylist terug
     * @return arraylist met reizigers
     */
    @Override
    public List<Reiziger> getAll() {
        return reizigers;
    }

    /**
     * Haal 1 reiziger op
     * @return reiziger object
     */
    @Override
    public Reiziger get() {
        return null;
    }

    /**
     * Voeg reiziger toe
     * @param reiziger reiziger
     */
    @Override
    public void add(Reiziger reiziger) {

    }

    /**
     * Update reiziger
     * @param reiziger reiziger
     */
    @Override
    public void update(Reiziger reiziger) {

    }

    /**
     * Verwijder reiziger
     * @param object reiziger
     */
    @Override
    public void remove(Reiziger object) {

    }

    /**
     * Refresh het scherm
     */
    public void reload() {
        reizigers.clear();
        load();
    }

}
