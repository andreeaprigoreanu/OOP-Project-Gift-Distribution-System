package entities.child;

public final class ScoreStrategyFactory {
    private ScoreStrategyFactory() {
    }

    /**
     * instantiates a ScoreStrategy for a given child
     * @param child
     * @return ScoreStrategy
     */
    public static ScoreStrategy createScoreStrategy(final Child child) {
        switch (child.getAgeCategory()) {
            case Baby: return new BabyScoreStrategy();
            case Kid: return new KidScoreStrategy(child);
            case Teen: return new TeenScoreStrategy(child);
        }
        throw new IllegalArgumentException("Age category doesn't exist");
    }
}
