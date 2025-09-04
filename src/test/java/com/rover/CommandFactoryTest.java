package com.rover;

import com.rover.command.CommandFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {
    @Test
    @DisplayName("Throw Exception in command Factory")
    void inputShouldThrowExceptionInCommandFactory() {
        // given / when / then
        Exception ex = assertThrows(IllegalArgumentException.class, () -> CommandFactory.fromChar('O'));
        assertTrue(ex.getMessage().contains("Unknown command: O"));
    }
}