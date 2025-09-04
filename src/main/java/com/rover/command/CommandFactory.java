package com.rover.command;

public class CommandFactory {

    public static Command fromChar(char c) {
        return switch (c) {
            case 'L' -> new TurnLeftCommand();
            case 'R' -> new TurnRightCommand();
            case 'M' -> new MoveCommand();
            default -> throw new IllegalArgumentException("Unknown command: " + c);
        };
    }
}
