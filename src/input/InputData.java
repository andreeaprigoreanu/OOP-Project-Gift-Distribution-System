package input;

import java.util.ArrayList;

public final class InputData {
    private Integer numberOfYears;
    private Double santaBudget;
    private InitialData initialData;
    private ArrayList<AnnualChange> annualChanges;

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

    public InitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public ArrayList<AnnualChange> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(final ArrayList<AnnualChange> annualChanges) {
        this.annualChanges = annualChanges;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "numberOfYears=" + numberOfYears +
                ", santaBudget=" + santaBudget +
                ", initialData=" + initialData +
                ", annualChanges=" + annualChanges +
                '}';
    }
}
