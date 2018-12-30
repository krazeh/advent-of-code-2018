package adventofcode.day6;


import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class GridElement extends Point {
    private ElementType elementType;

    public GridElement(ElementType elementType, int x, int y) {
        super(x, y);
        this.elementType = elementType;
    }

    public Centre getClosestCentre(List<Centre> centreList) {
        Centre closestCentre = Collections.min(centreList, Comparator.comparing(c -> c.getManhattanDistance(this)));
        boolean closestIsUnique = centreList.stream()
                .filter(c -> !c.equals(this))
                .filter(c -> !c.equals(closestCentre))
                .noneMatch(c -> getManhattanDistance(c) == getManhattanDistance(closestCentre));

        return closestIsUnique ? closestCentre : null;
    }

    public int getManhattanDistance(GridElement other) {
        return (int) (Math.abs(other.getX() - this.getX()) + Math.abs(other.getY() - this.getY()));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GridElement)) {
            return false;
        }

        GridElement other = (GridElement) obj;

        return this.x == other.getX() && this.y == other.getY();
    }

    public ElementType getElementType() {
        return elementType;
    }

    public enum ElementType {
        LOCATION,
        CENTRE,
        BORDER
    }
}
