package input;

import enums.Cities;

import java.util.ArrayList;

public final class InitialData {
    private ArrayList<ChildInput> children;
    private ArrayList<GiftInput> santaGiftsList;
    private ArrayList<Cities> cities;

    public ArrayList<ChildInput> getChildren() {
        return children;
    }

    public void setChildren(final ArrayList<ChildInput> children) {
        this.children = children;
    }

    public ArrayList<GiftInput> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(final ArrayList<GiftInput> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }

    public ArrayList<Cities> getCities() {
        return cities;
    }

    public void setCities(final ArrayList<Cities> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "InitialData{" +
                "children=" + children +
                ", santaGiftsList=" + santaGiftsList +
                ", cities=" + cities +
                '}';
    }
}
