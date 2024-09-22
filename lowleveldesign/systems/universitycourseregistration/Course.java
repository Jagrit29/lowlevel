package lowleveldesign.systems.universitycourseregistration;

public class Course {
    private String id;
    private String name;
    private final int capacity;
    private int enrolledStudentCount;


    public Course(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.enrolledStudentCount = 0;
    }

    public synchronized boolean enroll() {
        if(enrolledStudentCount < capacity) {
            enrolledStudentCount = enrolledStudentCount + 1;

            return true;
        }

        return false;
    }

    public int getCapacity() {
        return capacity;
    }

    public synchronized int getEnrolledStudentCount() {
        return enrolledStudentCount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name; // Return the name of the course
    }

}
