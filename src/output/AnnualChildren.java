package output;

import java.util.ArrayList;
import java.util.List;

public final class AnnualChildren {
    private List<ChildOutput> children;

    public AnnualChildren() {
        children = new ArrayList<>();
    }

    public List<ChildOutput> getChildren() {
        return children;
    }

    public void setChildren(final List<ChildOutput> children) {
        this.children = children;
    }
}
