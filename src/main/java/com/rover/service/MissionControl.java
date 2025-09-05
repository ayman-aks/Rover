package com.rover.service;

import com.rover.command.Command;
import com.rover.command.CommandFactory;
import com.rover.input.MissionContext;
import com.rover.model.Plateau;
import com.rover.model.Robot;

import java.util.HashSet;
import java.util.Set;

public class MissionControl {

    public void process(MissionContext context) {
        Plateau plateau = context.getPlateau();
        Set<String> occupied = new HashSet<>();

        for (Robot robot : context.getRobots()) {
            executeCommands(robot, plateau, occupied);
        }
    }

    private void executeCommands(Robot robot, Plateau plateau, Set<String> occupied) {
        for (char commandChar : robot.getCommands().toCharArray()) {
            Command command = CommandFactory.fromChar(commandChar);
            command.execute(robot, plateau, occupied);
        }
        String finalPos = robot.getX() + "," + robot.getY();
        occupied.add(finalPos);
    }
}
