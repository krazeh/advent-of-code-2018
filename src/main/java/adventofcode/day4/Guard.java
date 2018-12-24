package adventofcode.day4;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Guard {
    private int guardId;
    private List<LogEntry> logEntries;
    private Map<String, GuardShift> shifts;


    public Guard(int guardId) {
        this.guardId = guardId;
        shifts = new HashMap<>();
        logEntries = new ArrayList<>();
    }

    public int getGuardId() {
        return guardId;
    }

    public void addLogEntry(LogEntry logEntry) {
        logEntries.add(logEntry);
    }

    public void parseLogEntries() {
        for (LogEntry logEntry : logEntries) {
            LocalDate shiftDate = logEntry.getShiftDate();
            if (!shifts.containsKey(LogEntry.DATE_FORMATTER.format(shiftDate))) {
                shifts.put(LogEntry.DATE_FORMATTER.format(shiftDate), new GuardShift(shiftDate));
            }

            shifts.get(LogEntry.DATE_FORMATTER.format(shiftDate)).addLogEntry(logEntry);
        }

        shifts.values().forEach(GuardShift::parseLogEntries);
    }

    public int[][] getShiftsAsGrid() {
        int[][] grid = new int[shifts.size()][60];
        List<String> shiftList = getShifts().keySet().stream().sorted().collect(Collectors.toList());

        int row = 0;
        for (String shift : shiftList) {
            GuardShift guardShift = getShifts().get(shift);

            int[] shiftArray = guardShift.getShiftAsArray();
            System.arraycopy(shiftArray, 0, grid[row], 0, shiftArray.length);

            row++;
        }

        return grid;
    }

    public List<Integer> getSleepFrequency() {
        List<Integer> sleptMinutes = new ArrayList<>();
        int[][] grid = getShiftsAsGrid();
        int rowSum;

        for (int col = 0; col < grid[0].length; col++) {
            int colSum = 0;

            for (int row = 0; row < grid.length; row++) {
                colSum += grid[row][col];
            }

            sleptMinutes.add(colSum);
        }

        return sleptMinutes;
    }

    public int getSleepiestMinute() {
        List<Integer> sleptMinutes = getSleepFrequency();

//        for (int x = 0; x < grid[0].length; x++) {
//            rowSum = 0;
//
//            for (int y = 0; y < grid.length; y++) {
//                rowSum += grid[x][y];
//            }
//
//            sleptMinutes.add(x, rowSum);
//        }

        int maxVal = Collections.max(sleptMinutes);
        return sleptMinutes.indexOf(maxVal);
    }

    public Map<String, GuardShift> getShifts() {
        return shifts;
    }

    public int getTotalSleep() {
        return shifts.values().stream().mapToInt(e -> e.getTimeInState(GuardEvent.GuardState.ASLEEP)).sum();
    }

    @Override
    public String toString() {
        return getGuardId() + "\n" + shifts.values();
    }
}
