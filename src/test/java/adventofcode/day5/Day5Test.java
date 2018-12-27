package adventofcode.day5;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day5Test {

    @Test
    public void solvePart1() {
        String input = "dabAcCaCBAcCcaDA";
        int result = Day5.solvePart1(input);
        assertEquals(10, result);
    }

    @Test
    public void solvePart2() {
        String input = "dabAcCaCBAcCcaDA";
        int result = Day5.solvePart2(input);
        assertEquals(4, result);
    }
}