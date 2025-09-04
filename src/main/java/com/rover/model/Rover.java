package com.rover.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Rover implements Robot{
    private int x;
    private int y;
    private Character direction;

    private String commands;

    public Rover(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        setDirection(direction);
    }

    @Override
    public void setDirection(String direction) {
        if (direction == null || direction.matches("[^NEWS]")) {
            throw new IllegalArgumentException("Invalid robot direction: " + direction);
        }
        this.direction = direction.charAt(0);
    }

    @Override
    public void setCommands(String commands) {
        if (commands == null || !commands.matches("[LRM]*")) {
            throw new IllegalArgumentException("Invalid commands: " + commands);
        }
        this.commands = commands;
    }

}
