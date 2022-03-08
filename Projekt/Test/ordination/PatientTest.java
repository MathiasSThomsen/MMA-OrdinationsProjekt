package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void Patient_Constructor() {
        // Arrange
        String cprnr = "280264-0963";
        String navn = "Hans Jensen";
        double vaegt = 69;

        // Act
        Patient patient = new Patient(cprnr,navn,vaegt);

        // Assert
        assertEquals(cprnr,patient.getCprnr());
        assertEquals(navn,patient.getNavn());
        assertEquals(vaegt,patient.getVaegt());
    }

    @Test
    void addOrdination() {
        // Arrange
        String cprnr = "280264-0963";
        String navn = "Hans Jensen";
        double vaegt = 69;
        LocalDate startden = LocalDate.of(2022,3,4);
        LocalDate slutden = LocalDate.of(2022,3,14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol",1,1.5,2,"ML");
        Patient patient = new Patient(cprnr,navn,vaegt);
        DagligFast df = new DagligFast(startden,slutden,laegemiddel);

        // Act
        patient.addOrdination(df);

        // Assert
        assertTrue(patient.getOrdinationer().contains(df));
    }

    @Test
    void removeOrdination() {
        // Arrange
        String cprnr = "280264-0963";
        String navn = "Hans Jensen";
        double vaegt = 69;
        LocalDate startden = LocalDate.of(2022,3,4);
        LocalDate slutden = LocalDate.of(2022,3,14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol",1,1.5,2,"ML");
        Patient patient = new Patient(cprnr,navn,vaegt);
        DagligFast df = new DagligFast(startden,slutden,laegemiddel);
        patient.addOrdination(df);
        // Act
        patient.removeOrdination(df);

        // Assert
        assertFalse(patient.getOrdinationer().contains(df));
    }
}