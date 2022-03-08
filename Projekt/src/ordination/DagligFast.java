package ordination;



import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DagligFast extends Ordination {


    // composition --> 0..* Person
    private Dosis[] doser = new Dosis[4];

    public DagligFast(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
        super(startDen, slutDen, laegemiddel);
    }

    public Dosis[] getDoser() {
        return doser;
    }

    @Override
    public double samletDosis() {
        double samletAntal = 0;
        for (Dosis s : doser){
            samletAntal+=s.getAntal();
        }
        return samletAntal*(ChronoUnit.DAYS.between(getStartDen(),getSlutDen())+1);
    }

    @Override
    public double doegnDosis() {
        double dagligtAntal = 0;
        for (Dosis s : doser){
            dagligtAntal+=s.getAntal();
        }
        return dagligtAntal;
    }

    @Override
    public String getType() {
        return "DagligFast";
    }

    public void opretDosis(double antalMorgen, double antalMiddag, double antalAften, double antalNat){
       LocalTime morgen =  LocalTime.of(6,0);
        Dosis dosermorgen = new Dosis(morgen,antalMorgen);
       doser[0] = dosermorgen;
        LocalTime middag =  LocalTime.of(12,0);
        Dosis dosermiddag = new Dosis(middag,antalMiddag);
        doser[1] = dosermiddag;
        LocalTime aften =  LocalTime.of(18,0);
        Dosis doseraften = new Dosis(aften,antalAften);
        doser[2] = doseraften;
        LocalTime nat =  LocalTime.of(0, 0);
        Dosis dosernat = new Dosis(nat,antalNat);
        doser[3] = dosernat;
    }

}
