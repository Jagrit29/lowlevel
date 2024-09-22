package lowleveldesign.systems.universitycourseregistration;

import java.util.List;

public class Demo {
    public static void main(String args[]) {
        CourseRegistrationSystem jagritUniversity = CourseRegistrationSystem.getInstance();

        Student s1 = new Student("1", "Michael Jordan");
        Student s2 = new Student("2", "Lionel Messi");
        Student s3 = new Student("3", "Di Maria");
        Student s4 = new Student("4", "Neymar");

        Course c1 = new Course("c1", "Nba", 60);
        Course c2 = new Course("c1", "Football", 2);

        jagritUniversity.addCourse(c1);
        jagritUniversity.addCourse(c2);

        jagritUniversity.addStudent(s1);
        jagritUniversity.addStudent(s2);
        jagritUniversity.addStudent(s3);


        // now Search for courses for football
        System.out.println("Searching");
        List<Course> courses = jagritUniversity.searchCourses("foot");
        System.out.println(courses.toString());

        // now enroll messi di maria ana dall;
        jagritUniversity.register(s2, c2);
        jagritUniversity.register(s3, c2);
        jagritUniversity.register(s4, c2); // this should fail;

        jagritUniversity.register(s1, c1);

        System.out.println(jagritUniversity.getRegisteredCourses(s1));
        System.out.println(jagritUniversity.getRegisteredCourses(s2));
        System.out.println(jagritUniversity.getRegisteredCourses(s4));

    }
}
