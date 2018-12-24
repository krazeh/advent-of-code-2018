package adventofcode.day4;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GuardShift {
    private LocalDate shiftDate;
    private List<GuardEvent> eventList;
    private List<LogEntry> logEntries;

    public GuardShift(LocalDate shiftDate) {
        this.shiftDate = shiftDate;

        eventList = new ArrayList<>();
        logEntries = new ArrayList<>();
    }

    public void addEvent(int eventStart, int eventFinish, GuardEvent.GuardState guardState) {
        eventList.add(new GuardEvent(eventStart, eventFinish, guardState));
    }

    public void addLogEntry(LogEntry logEntry) {
        logEntries.add(logEntry);
    }

    private static int getMinute(LocalDateTime logEntryTime) {
        return logEntryTime.getMinute();
    }

    private static boolean dateIsBetween(LocalDateTime left, LocalDateTime right, LocalDateTime comparison) {
        return !(comparison.isBefore(left) || comparison.isAfter(right));
    }

    public void parseLogEntries() {
        LocalDateTime shiftStart = shiftDate.atTime(LogEntry.DEFAULT_SHIFT_START);
        LocalDateTime shiftEnd = shiftDate.atTime(LogEntry.DEFAULT_SHIFT_END);
        for (int i = 0; i < logEntries.size(); i++) {
            LogEntry logEntry = logEntries.get(i);
            int eventStart = getMinute(logEntry.getEntryDateTime());
            int eventEnd = 60;

            if (i == 0 && !dateIsBetween(shiftStart, shiftEnd, logEntry.getEntryDateTime())) {
                eventStart = 0;
            }

            if (i < logEntries.size()-1) {
                eventEnd = getMinute(logEntries.get(i+1).getEntryDateTime());
            }

            GuardEvent.GuardState guardState = logEntry.getEventType() == LogEntry.EventType.ASLEEP ?
                    GuardEvent.GuardState.ASLEEP : GuardEvent.GuardState.AWAKE;

            addEvent(eventStart, eventEnd, guardState);
        }
    }

    public int getTimeInState(GuardEvent.GuardState state) {
        return eventList.stream().filter(e -> e.getGuardState() == state).mapToInt(e -> e.getEventFinishedMinute()-e.getEventStartedMinute()).sum();
    }

    public int[] getShiftAsArray() {
        int[] shiftArray = new int[60];
        int index = 0;

        for (GuardEvent event : eventList) {
            int[] eventArray = event.getEventAsArray();

            for (int i : eventArray) {
                shiftArray[index++] = i;
            }
        }

        System.arraycopy(shiftArray, 0, shiftArray, 60-index, index);

        return shiftArray;
    }

    @Override
    public String toString() {
        return "" + eventList;
    }
}
