package entities.child;

import common.Constants;

public final class BabyScoreStrategy extends ScoreStrategy {
    /**
     * @return average score of a baby
     */
    @Override
    public Double getAverageScore() {
        return Constants.BABY_SCORE;
    }
}
