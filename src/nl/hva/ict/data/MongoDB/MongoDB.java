package nl.hva.ict.data.MongoDB;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import nl.hva.ict.MainApplication;
import nl.hva.ict.data.Data;
import org.bson.Document;

/**
 * MongoDB class die de verbinding maakt met de Mongo server
 * @author Pieter Leek
 */
public abstract class MongoDB implements Data {

    protected MongoCollection<Document> collection;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;


    /**
     * Verbind direct met de server als dit object wordt aangemaakt
     */
    public MongoDB() {
        connect();
    }

    // connect database
    private void connect() {

        // Heb je geen gegevens in de MainApplication staan slaat hij het maken van de verbinding over
        if (MainApplication.getNosqlHost().equals(""))
            return;

        // Verbind alleen als er nog geen actieve verbinding is.
        if (this.mongoClient == null) {
            try {
                // Open pijpleiding
                this.mongoClient = MongoClients.create(MainApplication.getNosqlHost());
                // Selecteer de juiste database
                this.mongoDatabase = mongoClient.getDatabase(MainApplication.getNosqlDatabase());
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Selecteer de juiste collection
     * @param collection
     */
    public void selectedCollection(String collection) {
        this.collection = mongoDatabase.getCollection(collection);
    }


}
