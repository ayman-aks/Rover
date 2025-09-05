package com.rover;

import com.rover.input.MissionContext;
import com.rover.input.InputType;
import com.rover.model.Robot;
import com.rover.service.MissionControl;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) throw new IllegalArgumentException("Missing input file");

        MissionContext context = new MissionContext();

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) continue;

                InputType type = detectInputType(line,lineNumber==1);

                type.getParser().accept(line, context);
            }
        }

        new MissionControl().process(context);

        for (Robot r : context.getRobots()) {
            System.out.println(r.getX() + " " + r.getY() + " " + r.getDirection());
        }
    }

    public static InputType detectInputType(String line, boolean firstLine) {
        if (firstLine) return InputType.PLATEAU;
        if (line.matches("\\d+ \\d+ [NEWS]")) return InputType.ROVER_POSITION;
        if (line.matches("[LRM]+")) return InputType.ROVER_COMMANDS;
        throw new IllegalArgumentException("Unknown line format: " + line);
    }

}