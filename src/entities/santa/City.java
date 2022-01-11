package entities.santa;

import entities.child.Child;
import enums.Cities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class City {
    private final Cities cityName;
    private List<Child> children;

    public City(final Cities cityName) {
        this.cityName = cityName;
        this.children = new ArrayList<>();
    }

    public Cities getCityName() {
        return cityName;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(final List<Child> children) {
        this.children = children;
    }

    public String getCityNameAsString() {
        return cityName.toString();
    }

    /**
     * Sorts the children list by children id
     */
    public void sortChildrenById() {
        children = children.stream().sorted(Comparator.comparing(Child::getId))
                .collect(Collectors.toList());
    }

    /**
     * @return average nice score of children from the same city
     */
    public Double getAverageNiceScore() {
        this.sortChildrenById();
        Double avgScoresSum = 0.0;

        for (Child child : children) {
            Double averageScore = child.getAverageScore();
            avgScoresSum += averageScore;
        }

        return avgScoresSum / children.size();
    }
}
