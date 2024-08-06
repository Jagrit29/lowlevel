package lowleveldesign.designpatterns;
import java.util.*;

// Goals to create a directory structure for a company which has a manager at the top and he has 2 employees and 1 managaer and that manager also has employees
// Team is of 6;

interface Employee {
    int getSalary();
    void getInfo();
}

// Leaf element
class Developer implements Employee {
    String name;
    int salary;

    public Developer(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public int getSalary() {
        return this.salary;
    }

    public void getInfo() {
        System.out.println("Developer"+" "+name);
    }
}

// Non Leaf Element;
class Manager implements Employee {
    String name;
    int salary;
    public List<Employee> reportees;

    // Whenever I create a new manager, It will have empty list of repoorts
    public Manager(String name, int salary) {
        this.name = name;
        this.salary = salary;
        reportees = new ArrayList<>(); // empty list;
    }

    void addReportee(Employee reportee) {
        this.reportees.add(reportee);
    }

    void removeReportee(Employee reportee) {
        this.reportees.remove(reportee);
    }

    public int getSalary() {
        return this.salary;
    }

    public void getInfo() {
        System.out.println("Manager: "+this.name);
        // I am the manager, I have something more info of all my reportes;
        // In brute force way, I would be checking all kind of objects and what not, but now my all reportees fall under eMployee category whenter htye manager or developer;
        for(Employee reportee: reportees) {
            reportee.getInfo();
        }
    }

}

public class composite {

    // now I implement the directory of the company;
    public static void main(String args[]) {
        // Create the first employe or manager;
        Manager director = new Manager("Jagrit", 10);
        Developer seniorDeveloper = new Developer("John", 5);
        Manager manager = new Manager("Jack", 7);
        Developer juniorDeveloper = new Developer("Jackie", 3);

        // I am able to use all the managers and developers as employee
        director.addReportee(seniorDeveloper);
        director.addReportee(manager);
        manager.addReportee(juniorDeveloper);

        director.getInfo();
    }
}
