package output;

import java.util.ArrayList;
import java.util.List;

public final class Output {
    private List<AnnualChildren> annualChildren;

    public Output() {
        annualChildren = new ArrayList<>();
    }

    public List<AnnualChildren> getAnnualChildren() {
        return annualChildren;
    }

    public void setAnnualChildren(final List<AnnualChildren> annualChildren) {
        this.annualChildren = annualChildren;
    }
}
