package lowleveldesign.systems.universitycourseregistration;

import java.util.Date;

public class Registration {
    private String id;
    private Course course;
    private Student student;
    private Date date;

    public Registration(String id, Course course, Student student, Date date) {
        this.id = id;
        this.course = course;
        this.student = student;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public Date getDate() {
        return date;
    }

    public Student getStudent() {
        return student;
    }
}
