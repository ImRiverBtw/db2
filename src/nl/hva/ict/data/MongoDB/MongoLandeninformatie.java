package nl.hva.ict.data.MongoDB;

import nl.hva.ict.MainApplication;
import nl.hva.ict.models.Landen;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.eq;


/**
 * Landen informatie ophalen van de MongoDB
 */
public class MongoLandeninformatie extends MongoDB {

    private final List<Landen> landen;

    /**
     * Constructor
     */
    public MongoLandeninformatie() {
        // Init arraylist
        landen = new ArrayList<>();
    }

    /**
     * Haal alle landen op die in de arraylijst zitten
     * @return arraylijst met landen
     */
    @Override
    public List getAll() {
        return landen;
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
    public void add(Object object) {

    }

    /**
     * Update een object toe aan de arraylist. Niet gebruikt in deze class maar door de interface data wel verplicht
     */
    @Override
    public void update(Object object) {

    }

    /**
     * Verwijder een object toe aan de arraylist. Niet gebruikt in deze class maar door de interface data wel verplicht
     */
    @Override
    public void remove(Object object) {

    }

    /**
     * Haal alle informatie op uit de NoSQL server over welke landen een bepaalde taal spreken. Gebruik hiervoor aggregation.
     * Zet het resultaat in de arraylist
     * @param taal Welke taal wil je weten
     * @param alleenAfrika filter het resultaat zodat wel of niet alleen afrikaanse landen terug komen
     */
    public void wieSpreekt(String taal, boolean alleenAfrika) {

       // Als je geen NoSQL server hebt opgegeven gaat de methode niet verder anders zou je een nullpointer krijgen
        if (MainApplication.getNosqlHost().equals(""))
            return;

        // reset arraylist
        this.landen.clear();

        // selecteer collection
        this.selectedCollection("landen");

        // Aggregation functie in Mongo
        Bson match = match(eq("languages.name", taal));

        List<Document> results = collection.aggregate(Arrays.asList(match))
                .into(new ArrayList<>());

        // Maak models en voeg resultaat toe aan arraylist
        for (Document land : results) {
            this.landen.add(new Landen(land.get("name").toString(), land.get("capital").toString()));

        }
    }

    /**
     * Haal alle informatie op uit de NoSQL server in welke landen je met een bepaalde valuta kan betalen. Gebruik hiervoor aggregation.
     * Zet het resultaat in de arraylist
     * @param valuta Welke valuta wil je weten
     * @param alleenAfrika filter het resultaat zodat wel of niet alleen afrikaanse landen terug komen
     */
    public void waarBetaalJeMet(String valuta, boolean alleenAfrika) {
    }

    /**
     * Welke landen zijn er in welk werelddeel. Haal deze informatie uit de database
     * . Gebruik hiervoor aggregation.
     * Zet het resultaat in de arraylist
     * @param werelddeel Welke valuta wil je weten
     */
    public void welkeLandenZijnErIn(String werelddeel) {
    }

    /**
     * Hoeveel inwoners heeft Oost-Afrika?. Haal deze informatie uit de database en gebruik hiervoor aggregation.
     */
    public int hoeveelInwonersOostAfrika() {
        // reset arraylist
        this.landen.clear();

        // Om geen compile error te krijgen wordt tijdelijk 0 teruggegeven.
        return 0;
    }
}
