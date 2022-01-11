package entities.santa.distributionStrategy;

import entities.child.Child;
import entities.gift.Gift;
import entities.santa.Santa;
import entities.santa.elfStrategy.ElfBudgetStrategyFactory;
import enums.Category;
import output.AnnualChildren;
import output.ChildOutput;
import output.GiftOutput;
import output.Output;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IdStrategy implements PresentsDistributionStrategy {
    private final Output output;

    public IdStrategy(Output output) {
        this.output = output;
    }

    @Override
    public void distributePresents() {
        AnnualChildren annualChildren = new AnnualChildren();
        Santa santa = Santa.getInstance();
        Double budgetUnit = santa.getBudgetUnit();

        santa.sortChildrenById();
        List<Child> childrenWithNoGifts = new ArrayList<>();
        for (Child child : santa.getChildren()) {
            Double budget = ElfBudgetStrategyFactory.createElfBudgetStrategy(child, budgetUnit)
                    .calculateBudget();
            Double budgetCopy = budget;

            List<GiftOutput> receivedGifts = new ArrayList<>();
            for (Category category : child.getGiftsPreferences()) {
                List<Gift> allGiftsFromCategory = santa.getAllGiftsFromCategory(category);
                assert allGiftsFromCategory != null;
                if (allGiftsFromCategory.size() > 0) {
                    // se alege cadoul cel mai ieftin din categorie
                    // acesta se afla pe pozitia 0 deoarece lista este sortata crescator dupa
                    // preturi
                    for (Gift gift : allGiftsFromCategory) {
                        if (Double.compare(gift.getPrice(), budget) <= 0
                                && gift.getQuantity() > 0) {
                            // cadoul trebuie sa se incadreze in buget si sa existe in baza de date
                            budget -= gift.getPrice();
                            gift.decrementQuantity();
                            receivedGifts.add(new GiftOutput(gift.getProductName(), gift.getPrice(),
                                    gift.getCategory()));
                            break;
                        }
                    }
                }
            }
            if (receivedGifts.size() == 0) {
                childrenWithNoGifts.add(child);
            } else {
                // se adauga copilul la output
                annualChildren.getChildren().add(new ChildOutput(child.getId(),
                        child.getLastName(), child.getFirstName(), child.getCity(), child.getAge(),
                        child.getGiftsPreferences(), child.getAverageScore(),
                        child.getNiceScores(), budgetCopy, receivedGifts));
            }
        }

        // trebuie verificat daca pot fi asignate cadouri copiilor cu elful yellow
        for (Child child : childrenWithNoGifts) {
            List<GiftOutput> receivedGifts = new ArrayList<>();
            if (child.getElf().equals("yellow")) {
                List<Gift> giftsFromCategory = santa.getAllGiftsFromCategory(child
                        .getGiftsPreferences().get(0));
                assert giftsFromCategory != null;
                // se incearca asignarea celui mai ieftin cadou din categoria preferata, daca
                // acesta exista
                Gift gift = giftsFromCategory.get(0);
                if (gift.getQuantity() > 0) {
                    gift.decrementQuantity();
                    receivedGifts.add(new GiftOutput(gift.getProductName(), gift.getPrice(),
                            gift.getCategory()));
                }
            }
            Double budget = ElfBudgetStrategyFactory.createElfBudgetStrategy(child, budgetUnit)
                    .calculateBudget();
            // se adauga copilul la output
            annualChildren.getChildren().add(new ChildOutput(child.getId(),
                    child.getLastName(), child.getFirstName(), child.getCity(), child.getAge(),
                    child.getGiftsPreferences(), child.getAverageScore(),
                    child.getNiceScores(), budget, receivedGifts));
        }
        annualChildren.setChildren(annualChildren.getChildren().stream()
                .sorted(Comparator.comparing(ChildOutput::getId))
                .collect(Collectors.toList()));
        output.getAnnualChildren().add(annualChildren);
    }
}
