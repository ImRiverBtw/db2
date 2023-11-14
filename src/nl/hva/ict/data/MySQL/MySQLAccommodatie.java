package nl.hva.ict.data.MySQL;

import nl.hva.ict.models.Accommodatie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO voor de accommodatie
 * @author HBO-ICT
 */
public class MySQLAccommodatie extends MySQL<Accommodatie> {

    private final List<Accommodatie> accommodaties;

    /**
     * Constructor
     */
    public MySQLAccommodatie() {
        // init arraylist
        accommodaties = new ArrayList<>();

        // Laad bij startup
        load();
    }

    /**
     * Doe dit bij het maken van dit object
     */
    private void load() {

        // Vul hier je SQL code in
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

                // Haal alle velden op. Heb je een database ontwerp met andere velden? Pas dan hier de veldnamen aan
                String accommodatieCode = rs.getString("accommodatie_code");
                String naam = rs.getString("naam");
                String stad = rs.getString("stad");
                String land = rs.getString("land");
                String kamer = rs.getString("kamer");
                int personen = rs.getInt("personen");
                // Maak een object en voeg die toe aan de arraylist
                accommodaties.add(new Accommodatie(accommodatieCode, naam, stad, land, kamer, personen));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Haal alle accommodaties op door de gehele arraylist terug te geven
     * @return Een arraylist van accommodaties
     */
    @Override
    public List<Accommodatie> getAll() {
        return accommodaties;
    }

    /**
     * Haal 1 accommodatie op
     * @return Een arraylist van accommodaties
     */
    @Override
    public Accommodatie get() {
        return null;
    }

    /**
     * Voeg een accommodatie toe
     * @param accommodatie de accommodatie
     */
    @Override
    public void add(Accommodatie accommodatie) {

    }

    /**
     * Update de accommodatie
     * @param accommodatie Het accommodatie object
     */
    @Override
    public void update(Accommodatie accommodatie) {

    }

    /**
     * Verwijder de accommodatie
     * @param object accommodatie object
     */
    @Override
    public void remove(Accommodatie object) {

    }
}
