package entities.child;

public class KidScoreStrategy extends ScoreStrategy {
    private final Child child;

    public KidScoreStrategy(final Child child) {
        this.child = child;
    }

    /**
     * @return average score of a kid
     */
    @Override
    public Double getAverageScore() {
        Double sum = 0.0;
        for (Double niceScore : child.getNiceScores()) {
            sum += niceScore;
        }
        return sum / child.getNiceScores().size();
    }
}
