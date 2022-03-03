package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DagligSkaev extends Ordination {
    // composition --> 0..* Person
    private final List<Dosis> dosisArrayList = new ArrayList<>();

    public ArrayList<Dosis> getDosis() {
        return new ArrayList<>(dosisArrayList);
    }

    public DagligSkaev(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
        super(startDen, slutDen, laegemiddel);
    }



    public void opretDosis(LocalTime[] tid, double[] antal) {
        for(int i = 0; i<tid.length;i++){
        Dosis dosis = new Dosis(tid[i],antal[i]);
        dosisArrayList.add(dosis);}
    }

    @Override
    public double samletDosis() {
        double samletAntal = 0;
        for (Dosis s : dosisArrayList){
            samletAntal+=s.getAntal();
        }
        return samletAntal*(ChronoUnit.DAYS.between(getStartDen(),getSlutDen())+1);
    }

    @Override
    public double doegnDosis() {
        double dagligtAntal = 0;
        for (Dosis s : dosisArrayList){
            dagligtAntal+=s.getAntal();
        } return dagligtAntal;
    }

    @Override
    public String getType() {
        return "DagligtSkaev";
    }


}
