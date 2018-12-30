package adventofcode.day6;

import java.awt.*;

public class Location extends GridElement {
    private ElementType elementType;

    public Location(int x, int y) {
        super(ElementType.LOCATION, x, y);
    }
}
