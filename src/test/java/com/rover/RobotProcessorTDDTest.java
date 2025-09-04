package com.rover;

import com.rover.input.InputContext;
import com.rover.input.InputType;
import com.rover.model.Plateau;
import com.rover.model.Robot;
import com.rover.service.RobotProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.rover.Main.detectInputType;
import static org.junit.jupiter.api.Assertions.*;

class RobotProcessorTDDTest {

    private InputContext parseInput(String[] lines) {
        InputContext context = new InputContext();
        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            if (line.isBlank()) continue;
            InputType type= detectInputType(line, lineNumber==1);

            type.getParser().accept(line, context);
        }
        return context;
    }

    @Test
    @DisplayName("Parsing plateau with valid input should succeed")
    void parsingPlateauValidInput() {
        // given
        String[] input = {"5 5"};

        // when
        InputContext context = parseInput(input);
        Plateau plateau = context.getPlateau();

        // then
        assertNotNull(plateau);
        assertEquals(5, plateau.maxX());
        assertEquals(5, plateau.maxY());
    }

    @Test
    @DisplayName("Parsing plateau with invalid input should throw exception")
    void parsingPlateauInvalidInput() {
        // given
        String[] input = {"5"};

        // when / then
        Exception ex = assertThrows(IllegalArgumentException.class, () -> parseInput(input));
        assertTrue(ex.getMessage().contains("Invalid plateau line"));
    }

    @Test
    @DisplayName("Parsing robot position with valid input should succeed")
    void parsingRoverPositionValid() {
        // given
        String[] input = {
                "5 5",
                "1 2 N"
        };

        // when
        InputContext context = parseInput(input);
        Robot robot = context.getLastRobotInput();

        // then
        assertNotNull(robot);
        assertEquals(1, robot.getX());
        assertEquals(2, robot.getY());
        assertEquals('N', robot.getDirection());
    }

    @Test
    @DisplayName("Parsing robot position with invalid direction should throw exception")
    void parsingRoverPositionInvalidDirection() {
        // given
        String[] input = {
                "5 5",
                "1 2 X"
        };

        // when / then
        Exception ex = assertThrows(IllegalArgumentException.class, () -> parseInput(input));
        assertTrue(ex.getMessage().contains("Unknown line format: 1 2 X"));
    }
    @Test
    @DisplayName("Parsing robot command with invalid command should throw exception")
    void parsingRoverCommandInvalidCommand() {
        // given
        String[] input = {
                "5 5",
                "1 2 E",
                "MAL"
        };

        // when / then
        Exception ex = assertThrows(IllegalArgumentException.class, () -> parseInput(input));
        assertTrue(ex.getMessage().contains("Unknown line format: MAL"));
    }

    @Test
    @DisplayName("Parsing robot commands with valid input should succeed")
    void parsingRoverCommandsValid() {
        // given
        String[] input = {
                "5 5",
                "1 2 N",
                "LMLM"
        };

        // when
        InputContext context = parseInput(input);
        Robot robot = context.getLastRobotInput();

        // then
        assertEquals("LMLM", robot.getCommands());
    }

    @Test
    @DisplayName("Parsing robot commands without position should throw exception")
    void parsingRoverCommandsWithoutPosition() {
        // given
        String[] input = {
                "5 5",
                "LMLM"
        };

        // when / then
        Exception ex = assertThrows(IllegalArgumentException.class, () -> parseInput(input));
        assertTrue(ex.getMessage().contains("Commands without robot position: LMLM"));
    }

    @Test
    @DisplayName("Single move north should increment Y")
    void moveNorthSingleStep() {
        // given
        String[] input = {
                "5 5",
                "1 2 N",
                "M"
        };
        InputContext context = parseInput(input);
        RobotProcessor processor = new RobotProcessor();

        // when
        processor.process(context);
        Robot robot = context.getLastRobotInput();

        // then
        assertEquals(1, robot.getX());
        assertEquals(3, robot.getY());
        assertEquals('N', robot.getDirection());
    }

    @Test
    @DisplayName("Rotate left from north should face west")
    void rotateLeftFromNorth() {
        // given
        String[] input = {
                "5 5",
                "1 2 N",
                "L"
        };
        InputContext context = parseInput(input);
        RobotProcessor processor = new RobotProcessor();

        // when
        processor.process(context);
        Robot robot = context.getLastRobotInput();

        // then
        assertEquals('W', robot.getDirection());
    }

    @Test
    @DisplayName("Full NASA example should produce expected final positions")
    void fullExampleNASA() {
        // given
        String[] input = {
                "5 5",
                "1 2 N",
                "LMLMLMLMM",
                "3 3 E",
                "MMRMMRMRRM"
        };
        InputContext context = parseInput(input);
        RobotProcessor processor = new RobotProcessor();

        // when
        processor.process(context);
        List<Robot> rovers = context.getRobots();

        // then
        assertEquals(2, rovers.size());
        assertEquals(1, rovers.get(0).getX());
        assertEquals(3, rovers.get(0).getY());
        assertEquals('N', rovers.get(0).getDirection());

        assertEquals(5, rovers.get(1).getX());
        assertEquals(1, rovers.get(1).getY());
        assertEquals('E', rovers.get(1).getDirection());
    }

    @Test
    @DisplayName("Moving robot into another robot should throw collision exception")
    void collisionBeginningShouldThrow() {
        // given
        String[] input = {
                "5 5",
                "1 1 N",
                "MM",
                "1 3 S",
                "MMM"
        };
        InputContext context = parseInput(input);
        RobotProcessor processor = new RobotProcessor();

        // when / then
        Exception ex = assertThrows(IllegalStateException.class, () -> processor.process(context));
        assertTrue(ex.getMessage().contains("Collision detected"));
    }

    @Test
    @DisplayName("Moving robot into another robot should throw collision exception")
    void collisionFinishingShouldThrow() {
        // given
        String[] input = {
                "5 5",
                "1 1 N",
                "MM",
                "1 4 S",
                "MMM"
        };
        InputContext context = parseInput(input);
        RobotProcessor processor = new RobotProcessor();

        // when / then
        Exception ex = assertThrows(IllegalStateException.class, () -> processor.process(context));
        assertTrue(ex.getMessage().contains("Collision detected"));
    }



    @Test
    @DisplayName("Moving robot outside plateau should throw overflow exception")
    void overflowShouldThrow() {
        // given
        String[] input = {
                "3 3",
                "0 0 N",
                "MMMM"
        };
        InputContext context = parseInput(input);
        RobotProcessor processor = new RobotProcessor();

        // when / then
        Exception ex = assertThrows(IllegalStateException.class, () -> processor.process(context));
        assertTrue(ex.getMessage().contains("Robot would move outside plateau bounds: 0,4"));
    }

    @Test
    @DisplayName("Multiple sequential rovers should end in correct positions")
    void multipleSequentialRovers() {
        // given
        String[] input = {
                "5 5",
                "0 1 E",
                "MRMRM",
                "2 2 N",
                "LMLM",
                "4 4 W",
                "MMLL"
        };
        InputContext context = parseInput(input);
        RobotProcessor processor = new RobotProcessor();

        // when
        processor.process(context);
        List<Robot> rovers = context.getRobots();

        // then
        assertEquals(3, rovers.size());

        assertEquals(0, rovers.get(0).getX());
        assertEquals(0, rovers.get(0).getY());
        assertEquals('W', rovers.get(0).getDirection());

        assertEquals(1, rovers.get(1).getX());
        assertEquals(1, rovers.get(1).getY());
        assertEquals('S', rovers.get(1).getDirection());

        assertEquals(2, rovers.get(2).getX());
        assertEquals(4, rovers.get(2).getY());
        assertEquals('E', rovers.get(2).getDirection());
    }
}
