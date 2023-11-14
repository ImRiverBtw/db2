package nl.hva.ict.models;

import nl.hva.ict.data.Identifable;

import java.io.Serializable;

/**
 * Model voor Reiziger
 * @author HBO-ICT
 */
public class Reiziger implements Identifable, Serializable {

    private String reizigersCode;
    private String voornaam;
    private String achternaam;
    private String adres;
    private String postcode;
    private String plaats;
    private String land;
    private String hoofdreiziger;

    public Reiziger(String reizigersCode, String voornaam, String achternaam, String adres, String postcode, String plaats, String land, String hoofdreiziger) {
        this.reizigersCode = reizigersCode;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.adres = adres;
        this.postcode = postcode;
        this.plaats = plaats;
        this.land = land;
        this.hoofdreiziger = hoofdreiziger;
    }

    public Reiziger() {
    }

    public String getReizigersCode() {
        return reizigersCode;
    }

    public void setReizigersCode(String reizigersCode) {
        this.reizigersCode = reizigersCode;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getHoofdreiziger() {
        return hoofdreiziger;
    }

    public void setHoofdreiziger(String hoofdreiziger) {
        this.hoofdreiziger = hoofdreiziger;
    }

    @Override
    public String toString() {
        return voornaam + " " + achternaam + " woont in " + plaats;
    }
}
