package entities.santa.elfStrategy;

import entities.child.Child;

public class PinkElfStrategy implements ElfBudgetStrategy {
    private final Child child;
    private final Double budgetUnit;

    public PinkElfStrategy(Child child, Double budgetUnit) {
        this.child = child;
        this.budgetUnit = budgetUnit;
    }

    @Override
    public Double calculateBudget() {
        Double budget = budgetUnit * child.getAverageScore();
        budget = budget + budget * 30 / 100;
        return budget;
    }
}
