package input;

import enums.Category;
import enums.Cities;

import java.util.ArrayList;

public final class ChildInput {
    private Integer id;
    private String lastName;
    private String firstName;
    private Integer age;
    private Cities city;
    private Double niceScore;
    private ArrayList<Category> giftsPreferences;
    private Integer niceScoreBonus;
    private String elf;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
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

    public void setCity(final Cities city) {
        this.city = city;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public Integer getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public void setNiceScoreBonus(Integer niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public String getElf() {
        return elf;
    }

    public void setElf(String elf) {
        this.elf = elf;
    }

    @Override
    public String toString() {
        return "ChildInput{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", niceScore=" + niceScore +
                ", giftsPreferences=" + giftsPreferences +
                ", niceScoreBonus=" + niceScoreBonus +
                ", elf=" + elf +
                '}';
    }
}
