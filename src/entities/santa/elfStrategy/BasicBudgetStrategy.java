package entities.santa.elfStrategy;

import entities.child.Child;

public final class BasicBudgetStrategy implements ElfBudgetStrategy {
    private final Child child;
    private final Double budgetUnit;

    public BasicBudgetStrategy(final Child child, final Double budgetUnit) {
        this.child = child;
        this.budgetUnit = budgetUnit;
    }

    @Override
    public Double calculateBudget() {
        return budgetUnit * child.getAverageScore();
    }
}
