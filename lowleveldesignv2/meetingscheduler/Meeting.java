package lowleveldesignv2.meetingscheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Meeting {
    private String id;
    private String subject;
    private Interval interval;
    private MeetingRoom meetingRoom;
    private Map<String, RSVPStatus> participants;
    private String organizer;

    public Meeting(String id, String organizer, String subject, Interval interval, MeetingRoom meetingRoom, List<String> participants) {
        this.id = id; // ideally, this should be system generated
        this.organizer = organizer;
        this.subject = subject;
        this.interval = interval;
        this.meetingRoom = meetingRoom;
        this.participants = new HashMap<>();

        for(String participant: participants) this.participants.put(participant, RSVPStatus.UNKNOWN);
    }


    // addParticipants()
    // removeParticipants()
    // addRoom()
    // cancelMeeting()

}
