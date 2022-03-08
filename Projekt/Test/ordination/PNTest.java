package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {

    @Test
    void PN() {
        //arrange
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 5);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        double antalEnheder = 2;

        //act
        PN pn = new PN(startDato, slutDato, laegemiddel, antalEnheder);

        //assert
        assertEquals(startDato, pn.getStartDen());
        assertEquals(slutDato, pn.getSlutDen());
        assertEquals(laegemiddel, pn.getLaegemiddel());
        assertEquals(antalEnheder, pn.getAntalEnheder());

    }

    @Test
    void givDosis_givesDen_i_midten() {
        //arrange
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        double antalEnheder = 2;
        PN pn = new PN(startDato, slutDato, laegemiddel, antalEnheder);

        //act
        pn.givDosis(LocalDate.of(2022, 3, 6));

        //assert
        assertEquals(1, pn.getGivesDatoer().size());

    }

    @Test
    void givDosis_givesDen_minus_dag() {
        //arrange
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        double antalEnheder = 2;
        PN pn = new PN(startDato, slutDato, laegemiddel, antalEnheder);

        //act
        pn.givDosis(LocalDate.of(2022, 3, 1));

        //assert
        assertEquals(0, pn.getGivesDatoer().size());

    }

    @Test
    void givDosis_givesDen_plus_dag() {
        //arrange
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        double antalEnheder = 2;
        PN pn = new PN(startDato, slutDato, laegemiddel, antalEnheder);

        //act
        pn.givDosis(LocalDate.of(2022, 3, 22));

        //assert
        assertEquals(0, pn.getGivesDatoer().size());

    }

    @Test
    void givDosis_givesDen_grænseværdi1() {
        //arrange
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        double antalEnheder = 2;
        PN pn = new PN(startDato, slutDato, laegemiddel, antalEnheder);

        //act
        pn.givDosis(LocalDate.of(2022, 3, 4));

        //assert
        assertEquals(1, pn.getGivesDatoer().size());

    }

    @Test
    void givDosis_givesDen_grænseværdi2() {
        //arrange
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        double antalEnheder = 2;
        PN pn = new PN(startDato, slutDato, laegemiddel, antalEnheder);

        //act
        pn.givDosis(LocalDate.of(2022, 3, 14));

        //assert
        assertEquals(1, pn.getGivesDatoer().size());

    }

    @Test
    void DoegnDosis_passer_2_datoer() {

        //arrange
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        double antalEnheder = 4;
        PN pn = new PN(startDato, slutDato, laegemiddel, antalEnheder);


        //act
        pn.givDosis(LocalDate.of(2022, 3, 5));
        pn.givDosis(LocalDate.of(2022, 3, 7));

        //assert
        assertEquals(2.66, pn.doegnDosis(), 0.01);
    }

    @Test
    void DoegnDosis_passer_1_dato() {

        //arrange
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        double antalEnheder = 4;
        PN pn = new PN(startDato, slutDato, laegemiddel, antalEnheder);


        //act
        pn.givDosis(LocalDate.of(2022, 3, 5));

        //assert
        assertEquals(4, pn.doegnDosis(), 0.01);
    }

    @Test
    void DoegnDosis_passer_2_af_samme_dato() {

        //arrange
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        double antalEnheder = 4;
        PN pn = new PN(startDato, slutDato, laegemiddel, antalEnheder);


        //act
        pn.givDosis(LocalDate.of(2022, 3, 5));
        pn.givDosis(LocalDate.of(2022, 3, 5));

        //assert
        assertEquals(8, pn.doegnDosis(), 0.01);
    }
    @Test
    void DoegnDosis_passer_3_datoer() {

        //arrange
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        double antalEnheder = 4;
        PN pn = new PN(startDato, slutDato, laegemiddel, antalEnheder);


        //act
        pn.givDosis(LocalDate.of(2022, 3, 5));
        pn.givDosis(LocalDate.of(2022, 3, 7));
        pn.givDosis(LocalDate.of(2022, 3, 9));

        //assert
        assertEquals(2.4, pn.doegnDosis(), 0.01);
    }

    @Test
    void samletDosis() {

        //arrange
        LocalDate startDato = LocalDate.of(2022, 3, 4);
        LocalDate slutDato = LocalDate.of(2022, 3, 14);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        double antalEnheder = 4;
        PN pn = new PN(startDato, slutDato, laegemiddel, antalEnheder);
        pn.givDosis(LocalDate.of(2022, 3, 5));
        pn.givDosis(LocalDate.of(2022, 3, 7));

        //act and assert
        assertEquals(8, pn.samletDosis());
    }
}