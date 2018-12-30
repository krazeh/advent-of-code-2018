package adventofcode.day6;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Centre extends GridElement {
    private static ElementType elementType = ElementType.CENTRE;
    private List<GridElement> closestElements;

    public Centre(int x, int y) {
        super(elementType, x, y);

        closestElements = new ArrayList<>();
    }

    public void addClosestElement(GridElement element) {
        this.closestElements.add(element);
    }

    public int getAreaSize() {
        boolean isInfinite = closestElements.stream().anyMatch(e -> e.getElementType() == ElementType.BORDER);

        return isInfinite ? 0 : closestElements.size()+1;
    }
}
