package ordination;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaegemiddelTest {

    @Test
    void LaegemiddelTest_Constructor() {
        // Arrange
        String navn = "Paracetamol";
        double enhedPrKgPrDoegnLet = 1;
        double enhedPrKgPrDoegnNormal = 1.5;
        double enhedPrKgPrDoegnTung = 2;
        String enhed = "ML";
        // Act
        Laegemiddel laegemiddel = new Laegemiddel(navn,enhedPrKgPrDoegnLet,enhedPrKgPrDoegnNormal,enhedPrKgPrDoegnTung,enhed);
        // Assert
        assertEquals(navn,laegemiddel.getNavn());
        assertEquals(enhedPrKgPrDoegnLet,laegemiddel.getEnhedPrKgPrDoegnLet());
        assertEquals(enhedPrKgPrDoegnNormal,laegemiddel.getEnhedPrKgPrDoegnNormal());
        assertEquals(enhedPrKgPrDoegnTung,laegemiddel.getEnhedPrKgPrDoegnTung());
        assertEquals(enhed,laegemiddel.getEnhed());
    }

}