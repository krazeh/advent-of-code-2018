package adventofcode.day3;

import adventofcode.common.FileUtil;

import java.util.List;
import java.util.stream.Collectors;

public class Day3 {

    public Day3() {
        try {
            List<String> input = FileUtil.readInputFile(this.getClass());
            System.out.println(solvePart1(input));
            System.out.println(solvePart2(input));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    static int solvePart1(List<String> input) {
        List<Claim> claims = input.stream().map(Claim::new).collect(Collectors.toList());
        int[][] fabric = new int[1000][1000];

        for (Claim claim : claims) {
            updateFabric(fabric, claim);
        }

        int overlappingSquares = 0;
        for (int x = 0; x < fabric.length; x++) {
            for (int y = 0; y < fabric[0].length; y++) {
                if (fabric[x][y] > 1) {
                    overlappingSquares++;
                }
            }
        }

        return overlappingSquares;
    }

    static int solvePart2(List<String> input) {
        List<Claim> claims = input.stream().map(Claim::new).collect(Collectors.toList());
        boolean overlaps = false;
        int result = 0;

        for (Claim left : claims) {
            overlaps = false;
            for (Claim right: claims) {
                if (left.getId() == right.getId()) {
                    continue;
                }

                overlaps = left.overlaps(right);

                if (overlaps) {
                    break;
                }
            }

            if (!overlaps) {
                result = left.getId();
            }
        }

        return result;
    }

    private static void updateFabric(int[][] fabric, Claim claim) {
        for (int x = claim.getX(); x < claim.getX()+claim.getWidth(); x++) {
            for (int y = claim.getY(); y < claim.getY()+claim.getHeight(); y++) {
                fabric[x][y]++;
            }
        }
    }

    public static void main(String[] args) {
        new Day3();
    }
}
