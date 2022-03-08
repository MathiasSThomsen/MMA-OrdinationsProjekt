package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

    @Test
    void DagligSkaev() {
        // Arrange
        LocalDate startDen = LocalDate.of(2022,3,4);
        LocalDate slutDen = LocalDate.of(2022,3,5);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol",1,1.5,2,"ML");
        // Act
        DagligSkaev ds = new DagligSkaev(startDen,slutDen,laegemiddel);
        // Assert
        assertEquals(startDen,ds.getStartDen());
        assertEquals(slutDen,ds.getSlutDen());
        assertEquals(laegemiddel,ds.getLaegemiddel());
    }

    @Test
    void opretDosis_2_Dosis() {
        // Arrange
        LocalDate startDen = LocalDate.of(2022,3,4);
        LocalDate slutDen = LocalDate.of(2022,3,5);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol",1,1.5,2,"ML");
        DagligSkaev ds = new DagligSkaev(startDen,slutDen,laegemiddel);
        LocalTime[] til = {LocalTime.of(9,30), LocalTime.of(9,45)};
        double[] antal = {1,2};
        // Act
        ds.opretDosis(til,antal);
        // Assert
        assertEquals(2,ds.getDosis().size());
        assertEquals(1, ds.getDosis().get(0).getAntal());
    }

    @Test
    void opretDosis_Negativ_Antal() {
        // Arrange
        LocalDate startDen = LocalDate.of(2022,3,4);
        LocalDate slutDen = LocalDate.of(2022,3,5);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol",1,1.5,2,"ML");
        DagligSkaev ds = new DagligSkaev(startDen,slutDen,laegemiddel);
        LocalTime[] til = {LocalTime.of(9,30)};
        double[] antal = {-1};
        // Act
        ds.opretDosis(til,antal);
        // Assert
        assertEquals(1,ds.getDosis().size());
        assertEquals(-1, ds.getDosis().get(0).getAntal());
    }

    @Test
    void opretDosis_0_Dosis() {
        // Arrange
        LocalDate startDen = LocalDate.of(2022,3,4);
        LocalDate slutDen = LocalDate.of(2022,3,5);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol",1,1.5,2,"ML");
        DagligSkaev ds = new DagligSkaev(startDen,slutDen,laegemiddel);
        LocalTime[] til = {LocalTime.of(9,30)};
        double[] antal = {0};
        // Act
        ds.opretDosis(til,antal);
        // Assert
        assertEquals(1,ds.getDosis().size());
        assertEquals(0, ds.getDosis().get(0).getAntal());
    }

    @Test
    void samletDosis() {
        // Arrange
        LocalDate startDen = LocalDate.of(2022,3,4);
        LocalDate slutDen = LocalDate.of(2022,3,14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol",1,1.5,2,"ML");
        DagligSkaev ds = new DagligSkaev(startDen,slutDen,laegemiddel);
        LocalTime[] til = {LocalTime.of(9,30),LocalTime.of(12,30),LocalTime.of(17,30)};
        double[] antal = {1, 4, 3};
        ds.opretDosis(til,antal);
        // Act and Assert
        assertEquals(88,ds.samletDosis());
    }

    @Test
    void doegnDosis() {
        // Arrange
        LocalDate startDen = LocalDate.of(2022,3,4);
        LocalDate slutDen = LocalDate.of(2022,3,14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol",1,1.5,2,"ML");
        DagligSkaev ds = new DagligSkaev(startDen,slutDen,laegemiddel);
        LocalTime[] til = {LocalTime.of(9,30),LocalTime.of(12,30),LocalTime.of(17,30)};
        double[] antal = {1, 4, 3};
        ds.opretDosis(til,antal);
        // Act and Assert
        assertEquals(8,ds.doegnDosis());
    }

    @Test
    void getDosis() {
        // Arrange
        LocalDate startDen = LocalDate.of(2022,3,4);
        LocalDate slutDen = LocalDate.of(2022,3,14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol",1,1.5,2,"ML");
        DagligSkaev ds = new DagligSkaev(startDen,slutDen,laegemiddel);
        LocalTime[] til = {LocalTime.of(9,30),LocalTime.of(12,30),LocalTime.of(17,30)};
        double[] antal = {1, 4, 3};
        ds.opretDosis(til,antal);
        // Act and
        Dosis d1 = ds.getDosis().get(0);
        Dosis d2 = ds.getDosis().get(1);
        Dosis d3 = ds.getDosis().get(2);

        // Assert
        assertEquals(d1,ds.getDosis().get(0));
        assertEquals(d2,ds.getDosis().get(1));
        assertEquals(d3,ds.getDosis().get(2));
    }

    @Test
    void getType() {
        // Arrange
        LocalDate startDen = LocalDate.of(2022,3,4);
        LocalDate slutDen = LocalDate.of(2022,3,14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol",1,1.5,2,"ML");
        DagligSkaev ds = new DagligSkaev(startDen,slutDen,laegemiddel);
        String expected = "DagligtSkaev";
        // Act
        String actual = ds.getType();
        // Assert
        assertEquals(expected,actual);
    }
}