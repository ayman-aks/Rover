package com.rover.command;

import com.rover.model.Plateau;
import com.rover.model.Robot;

import java.util.Set;

public class MoveCommand implements Command {
    @Override
    public void execute(Robot robot, Plateau plateau, Set<String> occupied) {
        int x = robot.getX();
        int y = robot.getY();

        String pos = x + "," + y;
        if (occupied.contains(pos)) {
            throw new IllegalStateException("Collision detected: " + pos);
        }

        switch (robot.getDirection()) {
            case 'N' -> y++;
            case 'S' -> y--;
            case 'E' -> x++;
            case 'W' -> x--;
        }

        if (x < 0 || y < 0 || x > plateau.maxX() || y > plateau.maxY()) {
            throw new IllegalStateException("Robot would move outside plateau bounds: " + x + "," + y);
        }

        pos = x + "," + y;

        if (occupied.contains(pos)) {
            throw new IllegalStateException("Collision detected: " + pos);
        }

        robot.setX(x);
        robot.setY(y);
    }
}
