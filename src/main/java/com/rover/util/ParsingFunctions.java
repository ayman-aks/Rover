package com.rover.util;

import com.rover.input.InputContext;
import com.rover.model.Plateau;
import com.rover.model.Robot;
import com.rover.model.Rover;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ParsingFunctions {

    public static void parsePlateau(String line, InputContext context) {
        String[] parts = line.trim().split(" ");
        if (parts.length != 2) throw new IllegalArgumentException("Invalid plateau line: " + line);
        int maxX = Integer.parseInt(parts[0]);
        int maxY = Integer.parseInt(parts[1]);
        Plateau plateau = new Plateau(maxX, maxY);
        context.setPlateau(plateau);
    }

    public static void parseRobotPosition(String line, InputContext context) {
        String[] parts = line.trim().split(" ");
        if (parts.length != 3) throw new IllegalArgumentException("Invalid robot position: " + line);
        Robot robot = new Rover(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),parts[2]);
        context.addRobotInput(robot);
    }

    public static void parseRobotCommands(String line, InputContext context) {
        Robot lastRobot = context.getLastRobotInput();
        if (lastRobot == null) throw new IllegalArgumentException("Commands without robot position: " + line);
        lastRobot.setCommands(line);
        context.replaceLastRobot(lastRobot);
    }
}
