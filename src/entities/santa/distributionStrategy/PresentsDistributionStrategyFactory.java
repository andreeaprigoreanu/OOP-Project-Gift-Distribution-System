package entities.santa.distributionStrategy;

import enums.DistributionStrategyEnum;
import output.Output;

public final class PresentsDistributionStrategyFactory {
    private PresentsDistributionStrategyFactory() {
    }

    /**
     * Returns a new PresentsDistributionStrategy
     * @param strategy
     * @param output
     * @return
     */
    public static PresentsDistributionStrategy createPresentsDistributionStrategy(
            final DistributionStrategyEnum strategy, final Output output) {
        switch (strategy) {
            case ID: return new IdStrategy(output);
            case NICE_SCORE: return new NiceScoreStrategy(output);
            case NICE_SCORE_CITY: return new NiceScoreCityStrategy(output);
        }
        throw new IllegalArgumentException("Distribution strategy doesn't exist");
    }
}
