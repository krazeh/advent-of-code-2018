package adventofcode.day4;

public class GuardEvent {
    private int eventStartedMinute;
    private int eventFinishedMinute;
    private GuardState guardState;

    public enum GuardState {
        AWAKE,
        ASLEEP
    }

    public int getEventStartedMinute() {
        return eventStartedMinute;
    }

    public int getEventFinishedMinute() {
        return eventFinishedMinute;
    }

    public GuardState getGuardState() {
        return guardState;
    }

    public GuardEvent(int eventStartedMinute, int eventFinishedMinute, GuardState state) {
        if (eventStartedMinute == -1) {
            this.eventStartedMinute = 0;
        } else {
            this.eventStartedMinute = eventStartedMinute;
        }

        if (eventFinishedMinute == -1) {
            this.eventFinishedMinute = 60;
        } else {
            this.eventFinishedMinute = eventFinishedMinute;
        }

        this.guardState =  state;
    }

    public int[] getEventAsArray() {
        int[] eventArray = new int[eventFinishedMinute-eventStartedMinute];

        for (int i = 0; i < eventArray.length; i++) {
            eventArray[i] = getGuardState() == GuardState.ASLEEP ? 1 : 0;
        }

        return eventArray;
    }

    @Override
    public String toString() {
        return String.format("I have been %s for %d minutes!", guardState.name(), eventFinishedMinute-eventStartedMinute);
    }
}
