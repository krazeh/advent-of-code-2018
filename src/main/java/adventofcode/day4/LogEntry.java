package adventofcode.day4;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogEntry {
    private static int entryId = 0;
    private static int getEntryId() {
        return entryId++;
    }

    private final static Pattern pattern = Pattern.compile("\\[(.*?)] (Guard #(\\d+))?(falls asleep|wakes up)?");
    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private int logEntryId = getEntryId();
    private int guardId = -1;
    private LocalDateTime entryDateTime;
    private EventType eventType;

    public final static LocalTime DEFAULT_SHIFT_START = LocalTime.MIDNIGHT;
    public final static LocalTime DEFAULT_SHIFT_END = LocalTime.MIDNIGHT.plus(1, ChronoUnit.HOURS);
    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public LocalDate getShiftDate() {
        if (DEFAULT_SHIFT_END.isBefore(entryDateTime.toLocalTime())) {
            return entryDateTime.toLocalDate().plusDays(1);
        }
        return entryDateTime.toLocalDate();
    }

    public enum EventType {
        START, AWAKE, ASLEEP
    }

    public LogEntry(String entry) {
        Matcher matcher = pattern.matcher(entry);

        if (matcher.find()) {
            entryDateTime = LocalDateTime.parse(matcher.group(1), DATE_TIME_FORMATTER);

            if (matcher.group(2) != null) {
                guardId = Integer.valueOf(matcher.group(3));
                eventType = EventType.START;
            } else {
                eventType = matcher.group(4).charAt(0) == 'f' ? EventType.ASLEEP : EventType.AWAKE;
            }
        }
    }

    public int getLogEntryId() {
        return logEntryId;
    }

    public int getGuardId() {
        return guardId;
    }

    public void setGuardId(int guardId) {
        this.guardId = guardId;
    }

    public LocalDateTime getEntryDateTime() {
        return entryDateTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return String.format("Day %s: %s", DATE_FORMATTER.format(getShiftDate()), eventType.name());
    }
}
