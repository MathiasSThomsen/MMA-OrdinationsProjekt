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
        if((givesDen.isBefore(getSlutDen()) || givesDen.isEqual(getSlutDen())) && (givesDen.isAfter(getStartDen()) || givesDen.isEqual(getStartDen()))){
            givesdatoer.add(givesDen);
            antalGangeGivet++;
            return true;
        }
        return false;   
    }

    public double doegnDosis() {
        Collections.sort(givesdatoer);
        LocalDate ordineringStart = givesdatoer.get(0);
        LocalDate ordineringsSlut = givesdatoer.get(givesdatoer.size()-1);
        return samletDosis()/(ChronoUnit.DAYS.between(ordineringStart,ordineringsSlut)+1);
    }

    @Override
    public String getType() {
        return "PN";
    }


    public double samletDosis() {
        double samletAntal=0 ;
        samletAntal=antalGangeGivet* antalEnheder;
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

    public ArrayList getGivesDatoer() {
        return new ArrayList<>(givesdatoer);
    }

}
