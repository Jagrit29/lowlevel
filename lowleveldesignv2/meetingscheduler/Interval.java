package lowleveldesignv2.meetingscheduler;

import java.util.Date;

public class Interval {
    private Date startTime;
    private Date endTime;

    public Interval(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public boolean overlaps(Interval other) {
        boolean noOverlap = startTime.after(other.endTime) || endTime.before(other.startTime);

        return !noOverlap;
    }
}
