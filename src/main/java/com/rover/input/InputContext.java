package com.rover.input;

import com.rover.model.Plateau;
import com.rover.model.Robot;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InputContext {
    private Plateau plateau;
    private final List<Robot> robots = new ArrayList<>();

    public void addRobotInput(Robot rover) { robots.add(rover); }

    public Robot getLastRobotInput() {
        if (robots.isEmpty()) return null;
        return robots.get(robots.size() - 1);
    }

    public void replaceLastRobot(Robot robot) {
        if (!robots.isEmpty()) robots.set(robots.size() - 1, robot);
    }

}
