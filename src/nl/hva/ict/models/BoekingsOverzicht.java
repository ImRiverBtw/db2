package nl.hva.ict.models;

import nl.hva.ict.data.Identifable;

/**
 * Model voor BoekingsOverzicht
 * @author HBO-ICT
 */
public class BoekingsOverzicht implements Identifable {

    private Accommodatie accommodatie;
    private Reiziger reiziger;
    private Reservering reservering;


    public BoekingsOverzicht() {
    }

    public BoekingsOverzicht(Accommodatie accommodatie, Reiziger reiziger, Reservering reservering) {
        this.accommodatie = accommodatie;
        this.reiziger = reiziger;
        this.reservering = reservering;
    }

    public Accommodatie getAccommodatie() {
        return accommodatie;
    }

    public void setAccommodatie(Accommodatie accommodatie) {
        this.accommodatie = accommodatie;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public Reservering getReservering() {
        return reservering;
    }

    public void setReservering(Reservering reservering) {
        this.reservering = reservering;
    }

    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append(this.reiziger.getVoornaam());
       stringBuilder.append(" ");
       stringBuilder.append(this.reiziger.getAchternaam());
       stringBuilder.append(" - reist van ");
       stringBuilder.append(reservering.getAankomstDatum());
       stringBuilder.append(" tot ");
       stringBuilder.append(reservering.getVertrekDatum());
       stringBuilder.append(" en verblijft in : ");
       stringBuilder.append(accommodatie.getNaam());
       stringBuilder.append(" in ");
       stringBuilder.append(accommodatie.getStad());
       stringBuilder.append(" - ");
       stringBuilder.append(accommodatie.getLand());
       return stringBuilder.toString();
    }
}
