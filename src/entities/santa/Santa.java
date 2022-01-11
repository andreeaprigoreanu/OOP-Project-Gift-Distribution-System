package entities.santa;

import common.Constants;
import entities.child.Child;
import entities.gift.Gift;
import entities.santa.distributionStrategy.PresentsDistributionStrategy;
import entities.santa.distributionStrategy.PresentsDistributionStrategyFactory;
import enums.Category;
import enums.DistributionStrategyEnum;
import input.*;
import output.AnnualChildren;
import output.ChildOutput;
import output.GiftOutput;
import output.Output;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class Santa {
    private static Santa instance = null;
    private Integer numberOfYears;
    private Double santaBudget;
    private List<Child> children;
    private List<Gift> gifts;
    private DistributionStrategyEnum strategy = DistributionStrategyEnum.ID;

    private Santa() {
    }

    /**
     * @return unique instance of Santa class
     */
    public static Santa getInstance() {
        if (instance == null) {
            instance = new Santa();
        }
        return instance;
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(final List<Child> children) {
        this.children = children;
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(final List<Gift> gifts) {
        this.gifts = gifts;
    }

    /**
     * Sorts the children list by id
     */
    public void sortChildrenById() {
        children = children.stream()
                .sorted(Comparator.comparing(Child::getId))
                .collect(Collectors.toList());
    }

    public void sortChildrenByNiceScore() {
        children = children.stream()
                .sorted(Comparator.comparing(Child::getAverageScore).reversed()
                        .thenComparing(Child::getId))
                .collect(Collectors.toList());
    }

    /**
     * Populates the database of children and gifts with the input data
     * @param inputData
     */
    public void populateDatabase(final InputData inputData) {
        numberOfYears = inputData.getNumberOfYears();
        santaBudget = inputData.getSantaBudget();

        InitialData initialData = inputData.getInitialData();
        for (ChildInput childInput : initialData.getChildren()) {
            if (childInput.getAge() <= Constants.MAX_TEEN_AGE) {
                children.add(new Child(childInput.getId(), childInput.getLastName(),
                        childInput.getFirstName(), childInput.getAge(), childInput.getCity(),
                        childInput.getNiceScore(), childInput.getGiftsPreferences(),
                        childInput.getNiceScoreBonus(), childInput.getElf()));
            }
        }
        for (GiftInput giftInput : initialData.getSantaGiftsList()) {
            gifts.add(new Gift(giftInput.getProductName(), giftInput.getPrice(),
                    giftInput.getCategory(), giftInput.getQuantity()));
        }
    }

    /**
     * Makes the annual changes to Santa's database
     * @param annualChange
     */
    public void updateDatabase(final AnnualChange annualChange) {
        // update Santa's budget
        santaBudget = annualChange.getNewSantaBudget();
        // update children list
        this.updateChildrenList(annualChange);
        // update gifts list
        this.updateGiftsList(annualChange);
        // update strategy
        strategy = annualChange.getStrategy();
    }

    /**
     * Applies the annual changes to children list
     * @param annualChange
     */
    public void updateChildrenList(final AnnualChange annualChange) {
        for (Child child : children) {
            child.updateAge();
        }
        children.removeIf(child -> child.getAge() > Constants.MAX_TEEN_AGE);

        if (annualChange.getNewChildren() != null) {
            for (ChildInput childInput : annualChange.getNewChildren()) {
                if (childInput.getAge() <= Constants.MAX_TEEN_AGE) {
                    children.add(new Child(childInput.getId(), childInput.getLastName(),
                            childInput.getFirstName(), childInput.getAge(), childInput.getCity(),
                            childInput.getNiceScore(), childInput.getGiftsPreferences(),
                            childInput.getNiceScoreBonus(), childInput.getElf()));
                }
            }
        }

        if (annualChange.getChildrenUpdates() != null) {
            for (ChildUpdate childUpdate : annualChange.getChildrenUpdates()) {
                for (Child child : children) {
                    if (Double.compare(child.getId(), childUpdate.getId()) == 0) {
                        if (childUpdate.getNiceScore() != null) {
                            child.addNiceScore(childUpdate.getNiceScore());
                        }

                        if (childUpdate.getGiftsPreferences() != null) {
                            List<Category> newGiftsPreferences = childUpdate.getGiftsPreferences()
                                    .stream()
                                    .distinct()
                                    .collect(Collectors.toList());
                            int index = 0;
                            for (Category category : newGiftsPreferences) {
                                child.getGiftsPreferences().remove(category);
                                child.getGiftsPreferences().add(index, category);
                                index++;
                            }
                        }

                        child.setElf(childUpdate.getElf());
                    }
                }
            }
        }
    }

    /**
     * Applies the annual changes to gifts list
     * @param annualChange
     */
    public void updateGiftsList(final AnnualChange annualChange) {
        if (annualChange.getNewGifts() == null) {
            return;
        }

        for (GiftInput giftInput : annualChange.getNewGifts()) {
            gifts.add(new Gift(giftInput.getProductName(), giftInput.getPrice(),
                    giftInput.getCategory(), giftInput.getQuantity()));
        }
    }

    /**
     * Returns a list of gifts from a given category, sorted by price
     * @param category
     * @return
     */
    public List<Gift> getAllGiftsFromCategory(final Category category) {
        List<Gift> allGiftsFromCategory = new ArrayList<>();
        for (Gift gift : gifts) {
            if (gift.getCategory() == category) {
                allGiftsFromCategory.add(gift);
            }
        }

        if (gifts.size() == 0) {
            return null;
        } else {
            return allGiftsFromCategory.stream()
                    .sorted(Comparator.comparing(Gift::getPrice))
                    .collect(Collectors.toList());
        }
    }

    public List<City> getCitiesList() {
        List<City> cities = new ArrayList<>();

        for (Child child : children) {
            boolean foundCity = false;
            for (City city : cities) {
                if (child.getCity() == city.getCityName()) {
                    city.getChildren().add(child);
                    foundCity = true;
                    break;
                }
            }
            if (!foundCity) {
                City newCity = new City(child.getCity());
                newCity.getChildren().add(child);
                cities.add(newCity);
            }
        }

        return cities.stream().sorted(Comparator.comparing(City::getAverageNiceScore)
                .reversed().thenComparing(City::getCityNameAsString))
                .collect(Collectors.toList());
    }

    /**
     * Computes the annual budget unit
     * @return budget unit
     */
    public Double getBudgetUnit() {
        Double avgScoresSum = 0.0;

        this.sortChildrenById();
        for (Child child : children) {
            Double averageScore = child.getAverageScore();
            avgScoresSum += averageScore;
        }

        return santaBudget / avgScoresSum;
    }

    /**
     * Assigns gifts to children and saves the results in output
     * @param output stores the data that will be written in the output file
     */
    public void distributePresents(final Output output) {
        AnnualChildren annualChildren = new AnnualChildren();
        Double budgetUnit = this.getBudgetUnit();

        this.sortChildrenById();
        for (Child child : children) {
            Double assignedBudget = budgetUnit * child.getAverageScore();
            Double assignedBudgetCopy = assignedBudget;

            List<GiftOutput> receivedGifts = new ArrayList<>();
            for (Category category : child.getGiftsPreferences()) {
                List<Gift> allGiftsFromCategory = this.getAllGiftsFromCategory(category);
                assert allGiftsFromCategory != null;
                if (allGiftsFromCategory.size() > 0) {
                    // se alege cadoul cel mai ieftin din acea categorie
                    // acesta se afla pe pozitia 0 deoarece lista este sortata crescator dupa
                    // preturi
                    Gift gift = allGiftsFromCategory.get(0);
                    if (Double.compare(gift.getPrice(), assignedBudget) <= 0) {
                        assignedBudget -= gift.getPrice();
                        receivedGifts.add(new GiftOutput(gift.getProductName(), gift.getPrice(),
                                gift.getCategory()));
                    }
                }
            }

            // trebuie adaugat copilul in output
            annualChildren.getChildren().add(new ChildOutput(child.getId(),
                    child.getLastName(), child.getFirstName(), child.getCity(), child.getAge(),
                    child.getGiftsPreferences(), child.getAverageScore(), child.getNiceScores(),
                    assignedBudgetCopy, receivedGifts));
        }
        output.getAnnualChildren().add(annualChildren);
    }

    public void distributePresentsToChildren(final Output output) {
        PresentsDistributionStrategy distributionStrategy = PresentsDistributionStrategyFactory
                .createPresentsDistributionStrategy(strategy, output);
        distributionStrategy.distributePresents();
    }

    /**
     * resets the children and gifts lists
     */
    public void resetDatabase() {
        children = new ArrayList<>();
        gifts = new ArrayList<>();
        strategy = DistributionStrategyEnum.ID;
    }
}
