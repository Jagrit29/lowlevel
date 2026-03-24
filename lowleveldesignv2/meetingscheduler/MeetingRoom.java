package lowleveldesignv2.meetingscheduler;

import java.util.List;

public class MeetingRoom {
    private String name;
    private int capacity;
    private List<String> scheduledMeetings;


    public boolean isAvailable(Interval interval, int capacity) {

        return true;
    }

    public synchronized boolean bookRoom(String meetingId, Interval interval) {

        return true;
    }

    public boolean releaseRoom(String meetingId, Interval interval) {

        return true;
    }
}
