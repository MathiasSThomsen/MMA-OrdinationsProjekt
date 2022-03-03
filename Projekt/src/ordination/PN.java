package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PN extends Ordination {

    private double antalEnheder;
    private List<LocalDate> givesdatoer = new ArrayList<>();
    private int antalGangeGivet = 0;
    private double samletAntal = 0;

    public PN(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel, double antalEnheder) {
        super(startDen, slutDen, laegemiddel);
        this.antalEnheder = antalEnheder;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */

    public boolean givDosis(LocalDate givesDen) {
        if(givesDen.isBefore(getSlutDen()) && givesDen.isAfter(getStartDen())){
            givesdatoer.add(givesDen);
            antalGangeGivet++;
            return true;
        }
        return false;   
    }

    public double doegnDosis() {
        Collections.sort(givesdatoer);
        return (antalGangeGivet*antalEnheder)/(ChronoUnit.DAYS.between(givesdatoer.get(givesdatoer.size()-1),givesdatoer.get(0))+1);
    }

    @Override
    public String getType() {
        return "PN";
    }


    public double samletDosis() {
        samletAntal+=antalEnheder;
        return samletAntal;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return antalGangeGivet;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
