package com.rover;

import com.rover.model.Rover;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RoverTest {

    @Test
    @DisplayName("Setting invalid direction should throw IllegalArgumentException")
    void invalidDirectionShouldThrow() {
        // given
        Rover rover = new Rover(0, 0, "N");

        // when & then
        assertThrows(IllegalArgumentException.class, () -> rover.setDirection("X"));
        assertThrows(IllegalArgumentException.class, () -> rover.setDirection(null));
    }

    @Test
    @DisplayName("Setting invalid commands should throw IllegalArgumentException")
    void invalidCommandsShouldThrow() {
        // given
        Rover rover = new Rover(0, 0, "N");

        // when & then
        assertThrows(IllegalArgumentException.class, () -> rover.setCommands("ABC"));
        assertThrows(IllegalArgumentException.class, () -> rover.setCommands(null));
    }
}
