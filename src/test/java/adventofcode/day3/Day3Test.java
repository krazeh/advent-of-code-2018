package adventofcode.day3;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Day3Test {

    @Test
    public void solvePart1() {
        List<String> input = Arrays.asList(
                "#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2"
        );

        int result = Day3.solvePart1(input);
        assertEquals(4, result);
    }

    @Test
    public void solvePart2() {
        List<String> input = Arrays.asList(
                "#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2"
        );

        int result = Day3.solvePart2(input);
        assertEquals(3, result);
    }
}