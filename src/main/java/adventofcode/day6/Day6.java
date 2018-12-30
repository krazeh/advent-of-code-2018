package adventofcode.day6;

import adventofcode.common.FileUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day6 {

    private final static Pattern COORDINATES_PATTERN = Pattern.compile("(\\d+), (\\d+)");

    public Day6() {
        try {
            List<String> input = FileUtil.readInputFile(this.getClass());
            System.out.println(solvePart1(input));
            System.out.println(solvePart2(input, 10000));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    static int solvePart1(List<String> input) {
        Grid grid = parseInput(input);


        Centre biggestArea = Collections.max(grid.getCentreList(), Comparator.comparing(Centre::getAreaSize));
        return biggestArea.getAreaSize();
    }

    static int solvePart2(List<String> input, int sizeLimit) {
        Grid grid = parseInput(input);

        List<GridElement> closeAreas = new ArrayList<>();
        for (GridElement[] gridElements : grid.getGridElements()) {
            for (GridElement gridElement : gridElements) {
                int distSum = grid.getCentreList().stream().mapToInt(c -> c.getManhattanDistance(gridElement)).sum();

                if (distSum < sizeLimit) {
                    closeAreas.add(gridElement);
                }
            }
        }

        return closeAreas.size();
    }

    private static Grid parseInput(List<String> input) {
        List<Centre> centreList = input.stream().map(c -> {
            Matcher matcher = COORDINATES_PATTERN.matcher(c);

            if (matcher.find()) {
                return new Centre(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
            }

            return new Centre(0, 0);
        }).collect(Collectors.toList());

        int minX = 0;
        int maxX = (int) Collections.max(centreList, Comparator.comparing(Point::getX)).getX()+1;

        int minY = 0;
        int maxY = (int) Collections.max(centreList, Comparator.comparing(Point::getY)).getY()+1;

        GridElement[][] grid = new GridElement[maxX+1][maxY+1];

        centreList.forEach(c -> grid[(int)c.getX()][(int)c.getY()] = c);

        for (int x = minX; x < grid.length; x++) {
            for (int y = minY; y < grid[x].length; y++) {
                if (x == minX || x == maxX || y == minY || y == maxY) {
                    grid[x][y] = new Border(x, y);
                } else if (grid[x][y] == null) {
                    grid[x][y] = new Location(x, y);
                }

                GridElement element = grid[x][y];

                if (element.getElementType() == GridElement.ElementType.LOCATION ||
                        element.getElementType() == GridElement.ElementType.BORDER) {
                    Centre closestCentre = element.getClosestCentre(centreList);

                    if (closestCentre != null) {
                        closestCentre.addClosestElement(element);
                    }
                }
            }
        }

        return new Grid(grid, centreList);
    }

    public static void main(String[] args) {
        new Day6();
    }
}
