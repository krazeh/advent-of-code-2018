package adventofcode.day6;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Day6Test {

    @Test
    public void solvePart1() {
        List<String> input = Arrays.asList(
                "1, 1",
                "1, 6",
                "8, 3",
                "3, 4",
                "5, 5",
                "8, 9"
        );

        int result = Day6.solvePart1(input);
        assertEquals(17, result);
    }

    @Test
    public void solvePart2() {
        List<String> input = Arrays.asList(
                "1, 1",
                "1, 6",
                "8, 3",
                "3, 4",
                "5, 5",
                "8, 9"
        );

        int result = Day6.solvePart2(input, 32);
        assertEquals(16, result);
    }
}