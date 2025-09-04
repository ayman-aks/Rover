package com.rover;

import com.rover.model.Plateau;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    @Test
    @DisplayName("Creating Plateau with positive dimensions should succeed")
    void createPlateauValid() {
        // given / when
        Plateau plateau = new Plateau(5, 5);

        // then
        assertEquals(5, plateau.maxY());
        assertEquals(5, plateau.maxY());
    }

    @Test
    @DisplayName("Creating Plateau with zero dimensions should succeed")
    void createPlateauZero() {
        // given / when
        Plateau plateau = new Plateau(0, 0);

        // then
        assertEquals(0, plateau.maxX());
        assertEquals(0, plateau.maxX());
    }

    @Test
    @DisplayName("Creating Plateau with negative maxX should throw exception")
    void createPlateauNegativeX() {
        // given / when / then
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Plateau(-1, 5));
        assertTrue(ex.getMessage().contains("Plateau size must be positive"));
    }

    @Test
    @DisplayName("Creating Plateau with negative maxY should throw exception")
    void createPlateauNegativeY() {
        // given / when / then
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Plateau(5, -2));
        assertTrue(ex.getMessage().contains("Plateau size must be positive"));
    }

    @Test
    @DisplayName("Creating Plateau with both dimensions negative should throw exception")
    void createPlateauNegativeBoth() {
        // given / when / then
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Plateau(-3, -3));
        assertTrue(ex.getMessage().contains("Plateau size must be positive"));
    }
}
