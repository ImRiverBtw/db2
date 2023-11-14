package nl.hva.ict.data.MySQL;

import nl.hva.ict.models.Lodge;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao voor lodges
 * @author HBO-ICT
 */
public class MySQLLodge extends MySQL<Lodge> {

    private final List<Lodge> lodges;

    /**
     * Constructor
     */
    public MySQLLodge() {

        // init arraylist
        lodges = new ArrayList<>();

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
                String accommodatieCode = rs.getString("accommodatie_code");
                String naam = rs.getString("naam");
                String stad = rs.getString("stad");
                String land = rs.getString("land");
                String kamer = rs.getString("kamer");
                int personen = rs.getInt("personen");
                double prijsPerWeek = rs.getDouble("prijsPerWeek");
                boolean autohuur = rs.getBoolean("autohuur");

                // Maak model aan en voeg toe aan arraylist
                lodges.add(new Lodge(accommodatieCode, naam, stad, land, kamer, personen,prijsPerWeek, autohuur));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Geen alle lodges in de arraylist terug
     * @return arraylist met lodges
     */
    @Override
    public List<Lodge> getAll() {
        return lodges;
    }

    /**
     * Haal 1 lodge op
     * @return lodge object
     */
    @Override
    public Lodge get() {
        return null;
    }


    /**
     * Voeg een lodge toe
     * @param lodge Lodge
     */
    @Override
    public void add(Lodge lodge) {

    }

    /**
     * Update ee lodge
     * @param lodge Lodge
     */
    @Override
    public void update(Lodge lodge) {

    }

    /**
     * Verwijder Lodge
     * @param object Lodge
     */
    @Override
    public void remove(Lodge object) {

    }

    /**
     * Refresh het scherm
     */
    public void reload() {
        lodges.clear();
        load();
    }
}
