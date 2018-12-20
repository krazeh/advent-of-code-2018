package adventofcode.day1;

import adventofcode.common.FileUtil;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day1 {

    public Day1() {
        try {
            List<String> input = FileUtil.readInputFile(this.getClass());
            System.out.println(solvePart1(input));
            System.out.println(solvePart2(input));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    static int solvePart1(List<String> data) {
        return data.stream().mapToInt(d -> Integer.valueOf(d.trim())).sum();
    }

    static int solvePart2(List<String> data) {
        HashSet<Integer> set = new HashSet<>();

        int frequency = 0;
        int index = 0;
        boolean frequencyFound = false;

        while (!frequencyFound) {
            frequency += Integer.valueOf(data.get(index).trim());

            frequencyFound = set.contains(frequency);
            set.add(frequency);

            index = ++index % data.size();
        }

        return frequency;
    }

    public static void main(String[] args) {
        new Day1();
    }
}