package nl.hva.ict.models;

/**
 * Model voor Landen
 * @author HBO-ICT
 */
public class Landen {

    private final String naam;
    private final String hoofdstad;


    public Landen(String naam, String hoofdstad) {
        this.naam = naam;
        this.hoofdstad = hoofdstad;
    }

    @Override
    public String toString() {
        return naam + ". - De hoofdstad van " + naam + " is " + hoofdstad;
    }
}
