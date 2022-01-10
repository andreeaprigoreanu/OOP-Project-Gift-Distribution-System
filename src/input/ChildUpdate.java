package input;

import enums.Category;

import java.util.ArrayList;

public final class ChildUpdate {
    private Integer id;
    private Double niceScore;
    private ArrayList<Category> giftsPreferences;
    private String elf;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
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

    public String getElf() {
        return elf;
    }

    public void setElf(String elf) {
        this.elf = elf;
    }

    @Override
    public String toString() {
        return "ChildUpdate{" +
                "id=" + id +
                ", niceScore=" + niceScore +
                ", giftsPreferences=" + giftsPreferences +
                ", elf=" + elf +
                '}';
    }
}
