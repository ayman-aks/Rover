package com.rover.input;

import com.rover.util.ParsingFunctions;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.BiConsumer;

@AllArgsConstructor
@Getter
public enum InputType {

    PLATEAU(ParsingFunctions::parsePlateau),
    ROVER_POSITION(ParsingFunctions::parseRobotPosition),
    ROVER_COMMANDS(ParsingFunctions::parseRobotCommands);

    private final BiConsumer<String, MissionContext> parser;

}
