package entities.child;

import common.Constants;
import enums.Category;
import enums.ChildAgeCategory;
import enums.Cities;

import java.util.ArrayList;
import java.util.List;

public final class Child {
    private final Integer id;
    private final String lastName;
    private final String firstName;
    private Integer age;
    private final Cities city;
    // lista cu toate scorurile de cumintenie:
    private final List<Double> niceScores;
    // lista cu categoriile de cadouri preferate:
    private final List<Category> giftsPreferences;

    public Child(final Integer id, final String lastName, final String firstName,
                 final Integer age, final Cities city, final Double niceScore,
                 final List<Category> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScores = new ArrayList<>();
        this.niceScores.add(niceScore);
        this.giftsPreferences = new ArrayList<>(giftsPreferences);
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public Cities getCity() {
        return city;
    }

    public List<Double> getNiceScores() {
        return niceScores;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * increments the age
     */
    public void updateAge() {
        age++;
    }

    /**
     * Adds a new niceScore to niceScores list
     * @param niceScore
     */
    public void addNiceScore(final Double niceScore) {
        niceScores.add(niceScore);
    }

    /**
     * Returns the age category of the child
     * @return ChildAgeCategory
     */
    public ChildAgeCategory getAgeCategory() {
        if (age < Constants.MIN_KID_AGE) {
            return ChildAgeCategory.Baby;
        } else {
            if (age < Constants.MIN_TEEN_AGE) {
                return ChildAgeCategory.Kid;
            } else {
                return ChildAgeCategory.Teen;
            }
        }
    }

    /**
     * Creates a ScoreStrategy based on the age category of the child and returns the average
     * score computed with the strategy
     * @return average score
     */
    public Double getAverageScore() {
        ScoreStrategy scoreStrategy =
                ScoreStrategyFactory.createScoreStrategy(this);
        return scoreStrategy.getAverageScore();
    }
}
