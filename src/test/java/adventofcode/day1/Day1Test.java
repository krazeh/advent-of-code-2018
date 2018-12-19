package adventofcode.day1;


import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Day1Test {

    @Test
    public void solveString() {
        int sum = Day1.solvePart1(Arrays.asList("+1", "-2", "+3", "+1"));
        assertEquals(3, sum);
    }

    @Test
    public void solvePart2() {
        int frequency = Day1.solvePart2(Arrays.asList("+3", "+3", "+4", "-2", "-4"));
        assertEquals(10, frequency);
    }
}