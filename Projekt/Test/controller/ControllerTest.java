package controller;

import ordination.Laegemiddel;

import ordination.Ordination;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;


import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Patient patient;
    private Laegemiddel laegemiddel;
    private LocalDate startDato;
    private Controller controller = Controller.getController();

    @BeforeEach
        public void setupBeforeEach() {
        this.patient = new Patient("280264-0963", "Hans Jensen", 69);
        this.laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        this.startDato = LocalDate.of(2022,3,4);
    }

    private Patient patient;
    private Laegemiddel laegemiddel;
    private LocalDate startdato;
    private Controller controller = Controller.getController();



    @BeforeEach
    public void setUpBeforeEach() {
        this.startdato = LocalDate.of(2022,03,04);
        this.patient = new Patient("280264-0963","Hans Jensen",69);
        this.laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");

    }

    @org.junit.jupiter.api.Test
    void opretPNOrdination_SLutDato_efter_Startdato() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022,03,05);
        double antalEnheder = 4;

        //Act
        controller.opretPNOrdination(startdato,slutDato,patient,laegemiddel,4);

        //Assert
        assertEquals(1, this.patient.getOrdinationer().size());
    }
    @org.junit.jupiter.api.Test
    void opretPNOrdination_Slutdato_Samme_som_StartDato() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022,03,04);
        double antalEnheder = 4;

        //Act
        controller.opretPNOrdination(startdato,slutDato,patient,laegemiddel,4);

        //Assert
        assertEquals(1, this.patient.getOrdinationer().size());
    }

    @org.junit.jupiter.api.Test
    void opretPNOrdination_Slutdato_Før_StartDato() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022, 03, 03);
        double antalEnheder = 4;

        //Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.opretPNOrdination(startdato,slutDato,patient,laegemiddel,4);;
        });
        assertTrue(exception.getMessage().contains("Din startdato er efter din slutdato"));
    }
    @org.junit.jupiter.api.Test
    void opretPNOrdination_SLutDato_efter_Startdato_Lægemiddel_Null() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022,03,05);
        double antalEnheder = 4;

        //Act
        controller.opretPNOrdination(startdato,slutDato,patient,null,4);

        //Assert
        assertEquals(1, this.patient.getOrdinationer().size());
    }


    @org.junit.jupiter.api.Test
    void opretDagligSkaevOrdination_Slutdato_Efter_Startdato() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022,03,05);
        double[] antalEnheder = {1,2,1};
        LocalTime[] klokkeslaet = {LocalTime.of(11,30),LocalTime.of(13,30),LocalTime.of(17,15)};

        //Act
        controller.opretDagligSkaevOrdination(startdato,slutDato,patient,laegemiddel,klokkeslaet,antalEnheder);

        //Assert
        assertEquals(1, this.patient.getOrdinationer().size());

    }
    @org.junit.jupiter.api.Test
    void opretDagligSkaevOrdination_Slutdato_Samme_Som_Startdato() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022,03,04);
        double[] antalEnheder = {1,2,1};
        LocalTime[] klokkeslaet = {LocalTime.of(11,30),LocalTime.of(13,30),LocalTime.of(17,15)};

        //Act
        controller.opretDagligSkaevOrdination(startdato,slutDato,patient,laegemiddel,klokkeslaet,antalEnheder);

        //Assert
        assertEquals(1, this.patient.getOrdinationer().size());

    }
    @org.junit.jupiter.api.Test
    void opretDagligSkaevOrdination_Slutdato_Før_Startdato() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022,03,03);
        double[] antalEnheder = {1,2,1};
        LocalTime[] klokkeslaet = {LocalTime.of(11,30),LocalTime.of(13,30),LocalTime.of(17,15)};

        //Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.opretDagligSkaevOrdination(startdato,slutDato,patient,laegemiddel,klokkeslaet,antalEnheder);
        });
        assertTrue(exception.getMessage().contains("Din startdato er efter din slutdato"));

    }

    @org.junit.jupiter.api.Test
    void opretDagligSkaevOrdination_Slutdato_Efter_Startdato_Lægemiddel_null() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022,03,05);
        double[] antalEnheder = {1,2,1};
        LocalTime[] klokkeslaet = {LocalTime.of(11,30),LocalTime.of(13,30),LocalTime.of(17,15)};

        //Act
        controller.opretDagligSkaevOrdination(startdato,slutDato,patient,null,klokkeslaet,antalEnheder);

        //Assert
        assertEquals(1, this.patient.getOrdinationer().size());

    }


    @org.junit.jupiter.api.Test
    void opretDagligFastOrdination_Slutdato_Efter_Startdato() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022,03,05);
        int morgenAntal =1;
        int middagAntal=2;
        int aftenAntal =3;
        int natAntal=4;
        //Act
        controller.opretDagligFastOrdination(startdato,slutDato,patient,laegemiddel,morgenAntal,middagAntal,aftenAntal,natAntal);

        //Assert
        assertEquals(1, this.patient.getOrdinationer().size());
    }
    @org.junit.jupiter.api.Test
    void opretDagligFastOrdination_Slutdato_Samme_Som_Startdato() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022,03,04);
        int morgenAntal =1;
        int middagAntal=2;
        int aftenAntal =3;
        int natAntal=4;
        //Act
        controller.opretDagligFastOrdination(startdato,slutDato,patient,laegemiddel,morgenAntal,middagAntal,aftenAntal,natAntal);

        //Assert
        assertEquals(1, this.patient.getOrdinationer().size());
    }

    @org.junit.jupiter.api.Test
    void opretDagligFastOrdination_Slutdato_Før_Startdato() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022,03,03);
        int morgenAntal =1;
        int middagAntal=2;
        int aftenAntal =3;
        int natAntal=4;
        //Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.opretDagligFastOrdination(startdato,slutDato,patient,laegemiddel,morgenAntal,middagAntal,aftenAntal,natAntal);
        });
        assertTrue(exception.getMessage().contains("Din startdato er efter din slutdato"));
    }

    @org.junit.jupiter.api.Test
    void opretDagligFastOrdination_Slutdato_Efter_Startdato_Lægemiddel_Null() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022, 03, 05);
        int morgenAntal = 1;
        int middagAntal = 2;
        int aftenAntal = 3;
        int natAntal = 4;
        //Act
        controller.opretDagligFastOrdination(startdato, slutDato, patient, null, morgenAntal, middagAntal, aftenAntal, natAntal);

        //Assert
        assertEquals(1, this.patient.getOrdinationer().size());
    }



    @org.junit.jupiter.api.Test
    void ordinationPNAnvendt_Dato_I_Midten() {
        //Arrange
        LocalDate ordinationsDato = LocalDate.of(2022,03,05);
        LocalDate slutDato = LocalDate.of(2022, 03, 14);
        PN nyOrdination = controller.opretPNOrdination(startdato,slutDato,patient,laegemiddel,4);
        //Act
        controller.ordinationPNAnvendt(nyOrdination,ordinationsDato);
        //Assert
        assertTrue(patient.getOrdinationer().contains(nyOrdination));
    }
    @org.junit.jupiter.api.Test
    void ordinationPNAnvendt_Dato_samme_som_Start() {
        //Arrange
        LocalDate ordinationsDato = LocalDate.of(2022,03,04);
        LocalDate slutDato = LocalDate.of(2022, 03, 14);
        PN nyOrdination = controller.opretPNOrdination(startdato,slutDato,patient,laegemiddel,4);
        //Act
        controller.ordinationPNAnvendt(nyOrdination,ordinationsDato);
        //Assert
        assertTrue(patient.getOrdinationer().contains(nyOrdination));
    }

    @org.junit.jupiter.api.Test
    void ordinationPNAnvendt_Dato_Før_Start() {
        //Arrange
        LocalDate ordinationsDato = LocalDate.of(2022,03,03);
        LocalDate slutDato = LocalDate.of(2022, 03, 14);
        PN nyOrdination = controller.opretPNOrdination(startdato,slutDato,patient,laegemiddel,4);
        //Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.ordinationPNAnvendt(nyOrdination,ordinationsDato);
        });
        assertTrue(exception.getMessage().contains("Din dato er ikke indenfor ordinationen"));
    }

    @org.junit.jupiter.api.Test
    void ordinationPNAnvendt_Dato_Efter_Slut() {
        //Arrange
        LocalDate ordinationsDato = LocalDate.of(2022,03,23);
        LocalDate slutDato = LocalDate.of(2022, 03, 14);
        PN nyOrdination = controller.opretPNOrdination(startdato,slutDato,patient,laegemiddel,4);
        //Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.ordinationPNAnvendt(nyOrdination,ordinationsDato);
        });
        assertTrue(exception.getMessage().contains("Din dato er ikke indenfor ordinationen"));
    }

    @org.junit.jupiter.api.Test
    void ordinationPNAnvendt_Dato_samme_som_Slut() {
        //Arrange
        LocalDate ordinationsDato = LocalDate.of(2022,03,14);
        LocalDate slutDato = LocalDate.of(2022, 03, 14);
        PN nyOrdination = controller.opretPNOrdination(startdato,slutDato,patient,laegemiddel,4);
        //Act
        controller.ordinationPNAnvendt(nyOrdination,ordinationsDato);
        //Assert
        assertTrue(patient.getOrdinationer().contains(nyOrdination));
    }


    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn_Vægt_Under_25() {
        //Arrange
        Patient peter = new Patient ("310387-0578","Peter Hansen",11);
        double expectedresult = peter.getVaegt()*1;

        //Act
        double actualResult = controller.anbefaletDosisPrDoegn(peter,laegemiddel);

        //Assert
        assertEquals(expectedresult,actualResult);
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn_Vægt_Mellem_25_Og_120() {
        //Arrange
        Patient peter = new Patient ("310387-0578","Peter Hansen",70);
        double expectedresult = peter.getVaegt()*1.5;

        //Act
        double actualResult = controller.anbefaletDosisPrDoegn(peter,laegemiddel);

        //Assert
        assertEquals(expectedresult,actualResult);
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn_Vægt_Over_120() {
        //Arrange
        Patient peter = new Patient ("310387-0578","Peter Hansen",130);
        double expectedresult = peter.getVaegt()*2;

        //Act
        double actualResult = controller.anbefaletDosisPrDoegn(peter,laegemiddel);

        //Assert
        assertEquals(expectedresult,actualResult);
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn_Vægt_25() {
        //Arrange
        Patient peter = new Patient ("310387-0578","Peter Hansen",25);
        double expectedresult = peter.getVaegt()*1.5;

        //Act
        double actualResult = controller.anbefaletDosisPrDoegn(peter,laegemiddel);

        //Assert
        assertEquals(expectedresult,actualResult);
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn_Vægt_120() {
        //Arrange
        Patient peter = new Patient ("310387-0578","Peter Hansen",120);
        double expectedresult = peter.getVaegt()*1.5;

        //Act
        double actualResult = controller.anbefaletDosisPrDoegn(peter,laegemiddel);

        //Assert
        assertEquals(expectedresult,actualResult);
    }

    @org.junit.jupiter.api.Test

    void antalOrdinationerPrVægtPrLægemiddel_0_Til_300() {
        //Arrange
        LocalDate slutdato1 = LocalDate.of(2022,03,14);
        LocalDate startdato2 = LocalDate.of(2022,03,24);
        LocalDate slutdato2 = LocalDate.of(2022,03,29);
        Patient patient1 = controller.opretPatient ("310387-0575","Peter Hansen",11);
        controller.opretDagligFastOrdination(startdato,slutdato1,patient1,laegemiddel,4,4,4,4);
        Patient patient2= controller.opretPatient ("310387-0277","Hans Petersen",75);
        controller.opretPNOrdination(startdato,slutdato1,patient2,laegemiddel,4);
        controller.opretPNOrdination(startdato2,slutdato2,patient2,laegemiddel,4);
        Patient patient3= controller.opretPatient("310387-0678", "Susan Hansen", 64);
        controller.opretPNOrdination(startdato,slutdato1,patient3,laegemiddel,4);
        Patient patient4= controller.opretPatient("310387-0277", "Hans Petersen", 25);
        controller.opretPNOrdination(startdato,slutdato1,patient4,laegemiddel,4);
        Patient patient5= controller.opretPatient("310387-0678", "Susan Hansen", 120);
        controller.opretPNOrdination(startdato,slutdato1,patient5,laegemiddel,4);

        //Act
        int actualResult = controller.antalOrdinationerPrVægtPrLægemiddel(0,300,laegemiddel);

        //Assert
        assertEquals(6,actualResult);

    }

    @org.junit.jupiter.api.Test
    void antalOrdinationerPrVægtPrLægemiddel_64_Til_300() {
        //Arrange
        LocalDate slutdato1 = LocalDate.of(2022,03,14);
        LocalDate startdato2 = LocalDate.of(2022,03,24);
        LocalDate slutdato2 = LocalDate.of(2022,03,29);
        Patient patient1 = controller.opretPatient ("310387-0575","Peter Hansen",11);
        controller.opretDagligFastOrdination(startdato,slutdato1,patient1,laegemiddel,4,4,4,4);
        Patient patient2= controller.opretPatient ("310387-0277","Hans Petersen",75);
        controller.opretPNOrdination(startdato,slutdato1,patient2,laegemiddel,4);
        controller.opretPNOrdination(startdato2,slutdato2,patient2,laegemiddel,4);
        Patient patient3= controller.opretPatient("310387-0678", "Susan Hansen", 64);
        controller.opretPNOrdination(startdato,slutdato1,patient3,laegemiddel,4);
        Patient patient4= controller.opretPatient("310387-0277", "Hans Petersen", 25);
        controller.opretPNOrdination(startdato,slutdato1,patient4,laegemiddel,4);
        Patient patient5= controller.opretPatient("310387-0678", "Susan Hansen", 120);
        controller.opretPNOrdination(startdato,slutdato1,patient5,laegemiddel,4);

        //Act
        int actualResult = controller.antalOrdinationerPrVægtPrLægemiddel(64,300,laegemiddel);

        //Assert
        assertEquals(4,actualResult);
    }

    @org.junit.jupiter.api.Test
    void antalOrdinationerPrVægtPrLægemiddel_10_Til_64() {
        //Arrange
        LocalDate slutdato1 = LocalDate.of(2022,03,14);
        LocalDate startdato2 = LocalDate.of(2022,03,24);
        LocalDate slutdato2 = LocalDate.of(2022,03,29);
        Patient patient1 = controller.opretPatient ("310387-0575","Peter Hansen",11);
        controller.opretDagligFastOrdination(startdato,slutdato1,patient1,laegemiddel,4,4,4,4);
        Patient patient2= controller.opretPatient ("310387-0277","Hans Petersen",75);
        controller.opretPNOrdination(startdato,slutdato1,patient2,laegemiddel,4);
        controller.opretPNOrdination(startdato2,slutdato2,patient2,laegemiddel,4);
        Patient patient3= controller.opretPatient("310387-0678", "Susan Hansen", 64);
        controller.opretPNOrdination(startdato,slutdato1,patient3,laegemiddel,4);
        Patient patient4= controller.opretPatient("310387-0277", "Hans Petersen", 25);
        controller.opretPNOrdination(startdato,slutdato1,patient4,laegemiddel,4);
        Patient patient5= controller.opretPatient("310387-0678", "Susan Hansen", 120);
        controller.opretPNOrdination(startdato,slutdato1,patient5,laegemiddel,4);

        //Act
        int actualResult = controller.antalOrdinationerPrVægtPrLægemiddel(10,64,laegemiddel);

        //Assert
        assertEquals(3,actualResult);
    }

    @org.junit.jupiter.api.Test
    void antalOrdinationerPrVægtPrLægemiddel_64_Til_10() {
        //Arrange
        LocalDate slutdato1 = LocalDate.of(2022,03,14);
        LocalDate startdato2 = LocalDate.of(2022,03,24);
        LocalDate slutdato2 = LocalDate.of(2022,03,29);
        Patient patient1 = controller.opretPatient ("310387-0575","Peter Hansen",11);
        controller.opretDagligFastOrdination(startdato,slutdato1,patient1,laegemiddel,4,4,4,4);
        Patient patient2= controller.opretPatient ("310387-0277","Hans Petersen",75);
        controller.opretPNOrdination(startdato,slutdato1,patient2,laegemiddel,4);
        controller.opretPNOrdination(startdato2,slutdato2,patient2,laegemiddel,4);
        Patient patient3= controller.opretPatient("310387-0678", "Susan Hansen", 64);
        controller.opretPNOrdination(startdato,slutdato1,patient3,laegemiddel,4);
        Patient patient4= controller.opretPatient("310387-0277", "Hans Petersen", 25);
        controller.opretPNOrdination(startdato,slutdato1,patient4,laegemiddel,4);
        Patient patient5= controller.opretPatient("310387-0678", "Susan Hansen", 120);
        controller.opretPNOrdination(startdato,slutdato1,patient5,laegemiddel,4);

        //Act
        int actualResult = controller.antalOrdinationerPrVægtPrLægemiddel(64,10,laegemiddel);

        //Assert
        assertEquals(0,actualResult);
    }



    @org.junit.jupiter.api.Test

    void opretPatient() {
        //Arrange
        String cpr = "280264-0963";
        String navn = "Hans Jensen";
        double vaegt = 69;


        //Act
        Patient actualResult = controller.opretPatient(cpr,navn,vaegt);

        //Assert
        assertTrue(controller.getAllPatienter().contains(actualResult));
    }

    @org.junit.jupiter.api.Test
    void opretLaegemiddel() {
        //Arrange
        String navn = "Paracetamol";
        double enhedPrKgDoegnLet = 0.1;
        double enhedPrKgDoegnNormal = 0.5;
        double enhedPrKgDoegnLTung = 1;
        String enhed = "ML";



        //Act
        Laegemiddel actualResult = controller.opretLaegemiddel(navn,enhedPrKgDoegnLet,enhedPrKgDoegnNormal,enhedPrKgDoegnLTung,enhed);

        //Assert
        assertTrue(controller.getAllLaegemidler().contains(actualResult));
    }


}