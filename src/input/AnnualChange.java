package input;

import enums.DistributionStrategyEnum;

import java.util.ArrayList;

public final class AnnualChange {
    private Double newSantaBudget;
    private ArrayList<GiftInput> newGifts;
    private ArrayList<ChildInput> newChildren;
    private ArrayList<ChildUpdate> childrenUpdates;
    private DistributionStrategyEnum strategy;

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public ArrayList<GiftInput> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(final ArrayList<GiftInput> newGifts) {
        this.newGifts = newGifts;
    }

    public ArrayList<ChildInput> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(final ArrayList<ChildInput> newChildren) {
        this.newChildren = newChildren;
    }

    public ArrayList<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(final ArrayList<ChildUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    public DistributionStrategyEnum getStrategy() {
        return strategy;
    }

    public void setStrategy(final DistributionStrategyEnum strategy) {
        this.strategy = strategy;
    }
}
