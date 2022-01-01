package entities.child;

public class TeenScoreStrategy extends ScoreStrategy {
    private final Child child;

    public TeenScoreStrategy(final Child child) {
        this.child = child;
    }

    /**
     * @return average score of a teen
     */
    @Override
    public Double getAverageScore() {
        double niceScoresSum = 0.0;
        int sum = 0;

        for (int index = 0; index < child.getNiceScores().size(); index++) {
            niceScoresSum += child.getNiceScores().get(index) * (index + 1);
            sum += index + 1;
        }

        return niceScoresSum / sum;
    }
}
