package entities.santa.elfStrategy;

import entities.child.Child;

public class ElfBudgetStrategyFactory {
    private ElfBudgetStrategyFactory() {
    }

    public static ElfBudgetStrategy createElfBudgetStrategy(final Child child,
                                                            final Double budgetUnit) {
        switch (child.getElf()) {
            case "black": return new BlackElfStrategy(child, budgetUnit);
            case "pink": return new PinkElfStrategy(child, budgetUnit);
            // daca elful copilului este yellow sau white, bugetul nu este afectat
            default: return new BasicBudgetStrategy(child, budgetUnit);
        }
    }
}
