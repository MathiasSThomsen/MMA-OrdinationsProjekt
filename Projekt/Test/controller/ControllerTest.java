package controller;

import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Patient patient;
    private Laegemiddel laegemiddel;
    private LocalDate startdato;
    private Controller controller = Controller.getController();


    @BeforeEach
    public void setUpBeforeEach() {

        this.patient = new Patient("280264-0963", "Hans Jensen", 69);
        this.laegemiddel = new Laegemiddel("Paracetamol",1,1.5,2,"ML");
        this.startdato = LocalDate.of(2022,3,4);
    }

    @org.junit.jupiter.api.Test
    void opretPNOrdination_slutdato_efter_startdato() {

        // Arrange
        LocalDate slutdato = LocalDate.of(2022,3,5);
        int antalEnheder = 4;
        // Act
        controller.opretPNOrdination(startdato,slutdato,patient,laegemiddel,antalEnheder);
        // Assert
        assertEquals(1,this.patient.getOrdinationer().size());
    }

    @org.junit.jupiter.api.Test
    void opretPNOrdination_slutdato_samme_som_startdato() {

        // Arrange
        LocalDate slutdato = LocalDate.of(2022,3,4);
        int antalEnheder = 4;
        // Act
        controller.opretPNOrdination(startdato,slutdato,patient,laegemiddel,antalEnheder);
        // Assert
        assertEquals(1,this.patient.getOrdinationer().size());
    }

    @org.junit.jupiter.api.Test
    void opretPNOrdination_slutdato_før_startdato() {

        // Arrange
        LocalDate slutdato = LocalDate.of(2022,3,3);
        int antalEnheder = 4;

        // Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.opretPNOrdination(startdato,slutdato,patient,laegemiddel,antalEnheder);
        });
        assertTrue(exception.getMessage().contains("Din startdato er efter din slutdato"));
    }

    @org.junit.jupiter.api.Test
    void opretPNOrdination_slutdato_efter_startdato_Laegemiddel_null() {

        // Arrange
        LocalDate slutdato = LocalDate.of(2022,3,5);
        int antalEnheder = 4;
        // Act
        controller.opretPNOrdination(startdato,slutdato,patient,null,antalEnheder);
        // Assert
        assertEquals(1,this.patient.getOrdinationer().size());
    }

    @org.junit.jupiter.api.Test
    void opretDagligSkaevOrdination() {
    }

    @org.junit.jupiter.api.Test
    void opretDagligFastOrdination() {

    }

    @org.junit.jupiter.api.Test
    void ordinationPNAnvendt() {
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn() {
    }

    @org.junit.jupiter.api.Test
    void antalOrdinationerPrVægtPrLægemiddel() {
    }

    @org.junit.jupiter.api.Test
    void opretPatient() {
    }

    @org.junit.jupiter.api.Test
    void opretLaegemiddel() {
    }
}