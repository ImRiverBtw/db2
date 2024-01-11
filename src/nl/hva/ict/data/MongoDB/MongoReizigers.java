package nl.hva.ict.data.MongoDB;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCursor;
import nl.hva.ict.MainApplication;
import nl.hva.ict.controllers.ReizigerController;
import nl.hva.ict.models.Reiziger;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * Haal alle reizigers van Big Five Safari op uit de NoSQL database
 */
public class MongoReizigers extends MongoDB {

    private final List<Reiziger> reizigers;

    /**
     * Construtor
     */
    public MongoReizigers() {

        // init de arraylist
        reizigers = new ArrayList<>();

        // startup methode
        System.out.println("load mongoreizigers");
        load();
    }

    /**
     * Haal alle objecten op. Niet gebruikt in deze class maar door de interface data wel verplicht
     * @return een lijst
     */
    @Override
    public List getAll() {
        return reizigers;
    }

    /**
     * Haal 1 object op. Niet gebruikt in deze class maar door de interface data wel verplicht
     * @return een object
     */
    @Override
    public Object get() {
        return null;
    }

    /**
     * Voeg een object toe aan de arraylist. Niet gebruikt in deze class maar door de interface data wel verplicht
     */
    @Override
    public void add(Object reiziger) {

    }

    /**
     * Update een object toe aan de arraylist. Niet gebruikt in deze class maar door de interface data wel verplicht
     */
    @Override
    public void update(Object object) {

    }

    /**
     * Update een object toe aan de arraylist. Niet gebruikt in deze class maar door de interface data wel verplicht
     */
    @Override
    public void remove(Object object) {
    }

    /**
     * Laad bij startup
     */
    public void load() {

        // Als je geen NoSQL server hebt opgegeven gaat de methode niet verder anders zou je een nullpointer krijgen
        if (MainApplication.getNosqlHost().equals("")) {
            System.out.println("geen mongodb connectie");
            return;
        }
        // Selecteer de juiste collecton in de NoSQL server
        this.selectedCollection("reiziger");

        // Haal alles op uit deze collection en loop er 1 voor 1 doorheen
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            System.out.println(cursor.hasNext());
            // Zolang er data is
            while (cursor.hasNext()) {
                // warning Java is case sensitive
                // Haal alle velden per record
                Document tempReiziger = cursor.next();
                String reizigerCode = (String) tempReiziger.get("reizigers_code");
                String voornaam = (String) tempReiziger.get("voornaam");
                String achternaam = (String) tempReiziger.get("achternaam");
                String adres = (String) tempReiziger.get("adres");
                String postcode = (String) tempReiziger.get("postcode");
                String plaats = (String) tempReiziger.get("plaats");
                String land = (String) tempReiziger.get("land");
                String hoofdreiziger = (String) tempReiziger.get("hoofdreiziger");

                // Maak een nieuw object en voeg deze toe aan de arraylist
                reizigers.add(new Reiziger(reizigerCode, voornaam, achternaam, adres, postcode, plaats, land, hoofdreiziger));
            }
        } finally {
            // Sluit de stream
            cursor.close();
        }
    }
}
