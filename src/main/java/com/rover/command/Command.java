package com.rover.command;

import com.rover.model.Plateau;
import com.rover.model.Robot;

import java.util.Set;

public interface Command {
    void execute(Robot robot, Plateau plateau, Set<String> occupied);
}
