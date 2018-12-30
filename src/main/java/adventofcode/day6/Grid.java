package adventofcode.day6;

import java.util.List;

public class Grid {
    private GridElement[][] gridElements;
    private List<Centre> centreList;

    public Grid(GridElement[][] gridElements, List<Centre> centreList) {
        this.gridElements = gridElements;
        this.centreList = centreList;
    }

    public GridElement[][] getGridElements() {
        return gridElements;
    }

    public void setGridElements(GridElement[][] gridElements) {
        this.gridElements = gridElements;
    }

    public List<Centre> getCentreList() {
        return centreList;
    }

    public void setCentreList(List<Centre> centreList) {
        this.centreList = centreList;
    }
}
