package ordination;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {


    @Test
    void DagligFast() {
        //arrange
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 5);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");

        //act
        DagligFast df = new DagligFast(startDato, slutDato, laegemiddel);

        //assert
        assertEquals(startDato,df.getStartDen());
        assertEquals(slutDato, df.getSlutDen());
        assertEquals(laegemiddel, df.getLaegemiddel());
    }

    @Test
    void samletDosis() {
        //arrange
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        DagligFast df = new DagligFast(startDato, slutDato, laegemiddel);
        df.opretDosis(4, 5, 9, 10);

        //act and assert
        assertEquals(308, df.samletDosis());

    }

    @Test
    void doegnDosis() {
        //arrange
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        DagligFast df = new DagligFast(startDato, slutDato, laegemiddel);
        df.opretDosis(4, 5, 9, 10);

        //act and assert
        assertEquals(28, df.doegnDosis());

    }

    @Test
    void opretDosis() {
        //arrange
        double antalMorgen = 4;
        double antalMiddag = 5;
        double antalAften = 9;
        double antalNat = 10;
        Dosis[] expectedvalues = {new Dosis(LocalTime.of(6, 0),4),new Dosis(LocalTime.of(12, 0), 5),new Dosis(LocalTime.of(18, 0),9), new Dosis(LocalTime.of(0, 0),10)};
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 5);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        DagligFast df = new DagligFast(startDato, slutDato, laegemiddel);

        //act
        df.opretDosis(antalMorgen, antalMiddag, antalAften, antalNat);

        //assert
        assertEquals(expectedvalues[0].getAntal(), df.getDoser()[0].getAntal());
        assertEquals(expectedvalues[0].getTid(), df.getDoser()[0].getTid());
        assertEquals(expectedvalues[1].getAntal(), df.getDoser()[1].getAntal());
        assertEquals(expectedvalues[1].getTid(), df.getDoser()[1].getTid());
        assertEquals(expectedvalues[2].getAntal(), df.getDoser()[2].getAntal());
        assertEquals(expectedvalues[2].getTid(), df.getDoser()[2].getTid());
        assertEquals(expectedvalues[3].getAntal(), df.getDoser()[3].getAntal());
        assertEquals(expectedvalues[3].getTid(), df.getDoser()[3].getTid());

    }
}