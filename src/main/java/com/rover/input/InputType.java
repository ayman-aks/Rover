package com.rover.input;

import com.rover.util.ParsingFunctions;
import lombok.AllArgsConstructor;

import java.util.function.BiConsumer;

@AllArgsConstructor
public enum InputType {

    PLATEAU(ParsingFunctions::parsePlateau),
    ROVER_POSITION(ParsingFunctions::parseRobotPosition),
    ROVER_COMMANDS(ParsingFunctions::parseRobotCommands);

    private final BiConsumer<String, InputContext> parser;

    public void parse(String line, InputContext context) {
        parser.accept(line, context);
    }
}
