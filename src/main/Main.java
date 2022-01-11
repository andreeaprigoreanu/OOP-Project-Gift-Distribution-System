package main;

import checker.Checker;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import input.InputData;
import output.JsonWriter;
import output.Output;
import simulation.Simulation;

import java.io.File;
import java.io.IOException;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        for (int numTest = 1; numTest <= Constants.TESTS_NUMBER; numTest++) {
            String inputFile = Constants.INPUT_PATH + numTest + Constants.FILE_EXTENSION;
            String outputFile = Constants.OUTPUT_PATH + numTest + Constants.FILE_EXTENSION;

            ObjectMapper objectMapper = new ObjectMapper();
            InputData inputData = objectMapper.readValue(new File(inputFile), InputData.class);

            Output output = Simulation.getSimulation(inputData);

            new File("output").mkdir();
            JsonWriter jsonWriter = new JsonWriter(outputFile);
            jsonWriter.writeOutput(output);
        }
        Checker.calculateScore();
    }
}
