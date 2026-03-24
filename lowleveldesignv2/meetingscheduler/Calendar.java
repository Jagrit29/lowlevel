package lowleveldesignv2.meetingscheduler;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    // Note: We can leverage hashmap as well if we need O(1) lookup
    private List<Meeting> meetings;

    public Calendar() {
        this.meetings = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
    }
}
