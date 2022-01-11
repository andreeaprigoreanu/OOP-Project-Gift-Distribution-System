package simulation;

import entities.santa.Santa;
import input.AnnualChange;
import input.InputData;
import output.Output;

public final class Simulation {
    private Simulation() {
    }

    /**
     * Simulates the
     * @param inputData
     * @return output data to be written in a json file
     */
    public static Output getSimulation(final InputData inputData) {
        Output output = new Output();

        Santa santa = Santa.getInstance();
        santa.resetDatabase();

        // round 0
        int numRound = 0;
        santa.populateDatabase(inputData);
        santa.distributePresentsToChildren(output);

        // round 1->numberOfYears
        while (numRound < santa.getNumberOfYears()) {
            AnnualChange annualChange = inputData.getAnnualChanges().get(numRound);
            santa.updateDatabase(annualChange);
            santa.distributePresentsToChildren(output);
            numRound++;
        }

        return output;
    }
}
