package com.rover.command;

import com.rover.model.Plateau;
import com.rover.model.Robot;

import java.util.Set;

public class TurnRightCommand implements Command {
    @Override
    public void execute(Robot robot, Plateau plateau, Set<String> occupied) {
        switch (robot.getDirection()) {
            case 'N' -> robot.setDirection("E");
            case 'E' -> robot.setDirection("S");
            case 'S' -> robot.setDirection("W");
            case 'W' -> robot.setDirection("N");
        }
    }
}
