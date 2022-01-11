package entities.santa.elfStrategy;

import entities.child.Child;

public class BasicBudgetStrategy implements ElfBudgetStrategy {
    private final Child child;
    private final Double budgetUnit;

    public BasicBudgetStrategy(Child child, Double budgetUnit) {
        this.child = child;
        this.budgetUnit = budgetUnit;
    }

    @Override
    public Double calculateBudget() {
        return budgetUnit * child.getAverageScore();
    }
}
