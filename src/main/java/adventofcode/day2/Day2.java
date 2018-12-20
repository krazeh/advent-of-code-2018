package adventofcode.day2;

import adventofcode.common.FileUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day2 {

    public Day2() {
        try {
            List<String> input = FileUtil.readInputFile(this.getClass());
            System.out.println(solvePart1(input));
            System.out.println(solvePart2(input));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    static int solvePart1(List<String> data) {
        int twoOccurrences = 0;
        int threeOccurrences = 0;

        for (String id : data) {
            List<String> characters = Arrays.asList(id.split(""));
            Collections.sort(characters);

            if (containsExactOccurence(characters, 2)) {
                twoOccurrences++;
            }

            if (containsExactOccurence(characters, 3)) {
                threeOccurrences++;
            }
        }

        return twoOccurrences*threeOccurrences;
    }

    static String solvePart2(List<String> ids) {
        String result = null;

        for (int i = 0; i < ids.size()-1 && result == null; i++) {
            String left = ids.get(i);
            for (int j = i+1; j < ids.size() && result == null; j++) {
                String right = ids.get(j);
                int differingChars = 0;
                int differencePosition = 0;

                for (int pos = 0; pos < left.length() && differingChars < 2; pos++) {
                    if (left.charAt(pos) != right.charAt(pos)) {
                        differingChars++;
                        differencePosition = pos;
                    }
                }

                if (differingChars == 1) {
                    result = new StringBuilder(left).deleteCharAt(differencePosition).toString();
                }
            }
        }

        return result;
    }

    private static boolean containsExactOccurence(List<String> characters, int occurrence) {
        int currCount = 0;
        String currCharacter = null;
        boolean result = false;

        for (String character : characters) {
            if (!character.equals(currCharacter)) {
                if (currCount == occurrence) {
                    result = true;
                    break;
                }
                currCount = 0;
            }

            currCount++;
            currCharacter = character;
        }

        if (!result && currCount == occurrence) {
            result = true;
        }

        return result;
    }

    public static void main(String[] args) {
        new Day2();
    }
}
