package lowleveldesign.systems.universitycourseregistration;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CourseRegistrationSystem {
    private static CourseRegistrationSystem instance;
    private Map<String, Course> courses;
    private Map<String, Student> students;
    private Map<String, Registration> registrations;

    private CourseRegistrationSystem() {
        courses = new ConcurrentHashMap<>();
        students = new ConcurrentHashMap<>();
        registrations = new ConcurrentHashMap<>();
    }

    public synchronized static CourseRegistrationSystem getInstance() {
        if(instance == null) {
            instance = new CourseRegistrationSystem();
        }

        return instance;
    }

    // now what I can do this this;
    // I can add student
    // I can add course;
    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public void addCourse(Course course) {
        courses.put(course.getId(), course);
    }

    // now I can enroll particular student to a particular course; which is basically register
    public void register(Student student, Course course) {
        if(course.getEnrolledStudentCount() >= course.getCapacity()) {
            System.out.println("No more slots left");

            return;
        }
        Date date = new Date();
        course.enroll();
        Registration registration = new Registration("r1", course, student, date);
        registrations.put(registration.getId(), registration);
        student.getCourses().add(course);
    }

    public List<Course> getRegisteredCourses(Student student) {
        return student.getCourses();
    }

    // now I want to search for courses;
    public List<Course> searchCourses(String query) {
        // if this query is in the name, then return, let's sue streams;
        return courses.values().stream().filter(course -> course.getName().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
    }



}
