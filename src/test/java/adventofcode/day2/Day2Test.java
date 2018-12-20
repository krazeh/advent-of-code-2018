package adventofcode.day2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Day2Test {

    @Test
    public void solvePart1() {
        List<String> input = Arrays.asList(
                "abcdef",
                "bababc",
                "abbcde",
                "abcccd",
                "aabcdd",
                "abcdee",
                "ababab"
        );

        int result = Day2.solvePart1(input);
        assertEquals(12, result);
    }

    @Test
    public void solvePart2() {
        List<String> input = Arrays.asList(
                "abcde",
                "fghij",
                "klmno",
                "pqrst",
                "fguij",
                "axcye",
                "wvxyz"
        );

        String result = Day2.solvePart2(input);
        assertEquals("fgij", result);
    }
}