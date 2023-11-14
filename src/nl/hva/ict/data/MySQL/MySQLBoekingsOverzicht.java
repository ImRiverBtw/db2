package nl.hva.ict.data.MySQL;

import nl.hva.ict.models.Accommodatie;
import nl.hva.ict.models.BoekingsOverzicht;
import nl.hva.ict.models.Reiziger;
import nl.hva.ict.models.Reservering;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * DAO voor de accommodatie
 * @author HBO-ICT
 */
public class MySQLBoekingsOverzicht extends MySQL<BoekingsOverzicht> {

    private final List<BoekingsOverzicht> boekingsOverzicht;

    /**
     * Constructor
     */
    public MySQLBoekingsOverzicht() {

        // init arraylist
        boekingsOverzicht = new ArrayList<>();

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
                int idReservering = 0;
                Date aankomstDatum = rs.getDate("aankomstDatum");
                Date vertrekDatum = rs.getDate("vertrekDatum");
                boolean betaald = rs.getBoolean("betaald");
                String accommodatieCode = rs.getString("accommodatieCode");
                String reizerCode = rs.getString("reizigerCode");
                String voornaam = ""; // not in use
                String achternaam = rs.getString("reiziger"); // combine voor en achternaam
                String adres = ""; // not in use
                String postcode = "";
                String plaats = "";
                String land = "";
                String hoofdreiziger = "";
                String accommodatieNaam = rs.getString("naam");
                String accommodatieStad = rs.getString("stad");
                String accommodatieLand = rs.getString("land");

                // Maak models aan
                Reservering reservering = new Reservering(idReservering, aankomstDatum, vertrekDatum, betaald, accommodatieCode, reizerCode);
                Reiziger reiziger = new Reiziger(reizerCode, voornaam, achternaam, adres, postcode, plaats, land, hoofdreiziger);
                Accommodatie accommodatie = new Accommodatie();
                accommodatie.setNaam(accommodatieNaam);
                accommodatie.setStad(accommodatieStad);
                accommodatie.setLand(accommodatieLand);

                //voeg die toe aan de arraylist
                boekingsOverzicht.add(new BoekingsOverzicht(accommodatie, reiziger, reservering));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Haal de boekingen op voor 1 reiziger
     * @param reizigerscode Welke reiziger wil je de boekingen voor?
     * @return Een list van boekingen
     */
    public List<BoekingsOverzicht> getBoekingVoor(String reizigerscode) {

        // Maak een arraylist
        List<BoekingsOverzicht> reserveringVoor = new ArrayList<>();

        // Voer hier je query in
        String sql = "";


        try {
            // Maak je statement
            PreparedStatement ps = getStatement(sql);

            // Vervang het eerste vraagteken met de reizigerscode. Pas dit eventueel aan voor jou eigen query
            ps.setString(1, reizigerscode);

            // Voer het uit
            ResultSet rs = executeSelectPreparedStatement(ps);


            // Loop net zolang als er records zijn
            while (rs.next()) {
                int idReservering = 0;
                Date aankomstDatum = rs.getDate("aankomstDatum");
                Date vertrekDatum = rs.getDate("vertrekDatum");
                boolean betaald = rs.getBoolean("betaald");
                String accommodatieCode = rs.getString("accommodatieCode");

                String reizigerVoornaam = rs.getString("voornaam");
                String reizigerAchternaam = rs.getString("achternaam");
                String reizigerPlaats = rs.getString("plaats");
                String accommodatieNaam = rs.getString("naam");
                String accommodatieStad = rs.getString("stad");
                String accommodatieLand = rs.getString("land");

                // Maak model objecten
                Reservering reservering = new Reservering(idReservering, aankomstDatum, vertrekDatum, betaald, accommodatieCode, reizigerscode);
                Accommodatie accommodatie = new Accommodatie();
                accommodatie.setNaam(accommodatieNaam);
                accommodatie.setStad(accommodatieStad);
                accommodatie.setLand(accommodatieLand);

                Reiziger reiziger = new Reiziger();
                reiziger.setVoornaam(reizigerVoornaam);
                reiziger.setAchternaam(reizigerAchternaam);
                reiziger.setPlaats(reizigerPlaats);

                // Voeg de reservering toe aan de arraylist
                reserveringVoor.add(new BoekingsOverzicht(accommodatie, reiziger, reservering));
            }
        } catch (SQLException throwables) {
            // Oeps probleem
            throwables.printStackTrace();
        }

        // Geef de arrayList terug met de juiste reserveringen
        return reserveringVoor;
    }


    /**
     * Haal de reizigerscode op voor een bepaalde boeking per accommodate en datum
     * @param pCode de accommodatecode
     * @param pDatum de datum van verblijf
     * @return De reizigerscode
     */
    private String getReizigerscode(String pCode, LocalDate pDatum) {

       // Voer hier je eigen query in
        String sql = "";

        // default waarde
        String reizigerCode = "";

        // convert datum naar ander formaat
        Date date = Date.valueOf(pDatum);

        try {
            // query voorbereiden
            PreparedStatement ps = getStatement(sql);

            // Vervang de vraagtekens met de juiste waarde. Pas eventueel aan je eigen query
            ps.setString(1, pCode);
            ps.setDate(2, date);

            // Voer het uit
            ResultSet rs = executeSelectPreparedStatement(ps);

            // Loop net zolang als er records zijn
            while (rs.next()) {
                reizigerCode = rs.getString("reizigerCode");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Geef de reizigercode terug
        return reizigerCode;
    }


    /**
     * Haal een lijst met reserveringen op voor een bepaalde boeking per accommodate en datum
     * @param pCode de accommodate code
     * @param pDatum de datum van verblijf
     * @return Lijst met reserveringen
     */
    public List<Reiziger> GeboektOp(String pCode, LocalDate pDatum) {

        // Init arraylist
        List<Reiziger> geboektOp = new ArrayList<>();

        //Stop null pointer error als datum nog niet is ingevuld.
        if (pDatum == null)
            return geboektOp;

        // Spring naar de methode hierboven om de reizigerscode op te halen voor deze boeking
        String reizigerscode = getReizigerscode(pCode, pDatum);

        if (reizigerscode != null) {

            // Haal alle reserveringen op
            String sql = "";

            // Als je nog geen query hebt ingevuld breek dan af om een error te voorkomen.
            if (sql.equals(""))
                return geboektOp;

            try {
                // Roep de methode aan in de parent class en geen je SQL door
                PreparedStatement ps = getStatement(sql);

                // vervang de eerste vraagteken met de reizigerscode
                ps.setString(1, reizigerscode);

                // Voer je query uit
                ResultSet rs = executeSelectPreparedStatement(ps);

                // Loop net zolang als er records zijn
                while (rs.next()) {
                    String voornaam = rs.getString("voornaam");
                    String achternaam = rs.getString("achternaam");
                    String adres = rs.getString("adres");
                    String postcode = rs.getString("postcode");
                    String plaats = rs.getString("plaats");
                    String land = rs.getString("land");
                    String hoofdreiziger = rs.getString("hoofdreiziger");

                    // Maak reserveringsobject en voeg direct toe aan arraylist
                    geboektOp.add(new Reiziger(reizigerscode, voornaam, achternaam, adres, postcode, plaats, land, hoofdreiziger));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        // Geef de array terug met reserveringen
        return geboektOp;
    }

    /**
     * Haal alle boekingen op door de gehele arraylist terug te geven
     * @return Een arraylist van accommodaties
     */
    @Override
    public List<BoekingsOverzicht> getAll() {
        return boekingsOverzicht;
    }

    /**
     * Haal 1 boeking op
     * @return Een arraylist van accommodaties
     */
    @Override
    public BoekingsOverzicht get() {
        // nog niet uitgewerkt geeft nu even null terug
        return null;
    }

    /**
     * Voeg een boeking toe
     * @param boekingsOverzicht de boeking
     */
    @Override
    public void add(BoekingsOverzicht boekingsOverzicht) {
        // nog niet uitgewerkt
    }

    /**
     * Update de boeking
     * @param boekingsOverzicht de boeking
     */
    @Override
    public void update(BoekingsOverzicht boekingsOverzicht) {
        // nog niet uitgewerkt

    }

    /**
     * Verwijder een boeking
     * @param object de boeking
     */
    @Override
    public void remove(BoekingsOverzicht object) {
        // nog niet uitgewerkt

    }
}
