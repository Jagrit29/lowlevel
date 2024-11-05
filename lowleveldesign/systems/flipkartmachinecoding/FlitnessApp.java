package lowleveldesign.systems.flipkartmachinecoding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Center
enum CenterLocation {
    KORAMANGALA,
    BELLANDUR
}

enum WorkoutType {
    WEIGHTS,
    CARDIO,
    YOGA,
    SWIMMING
}

// Center has n slots of an hour each
// TODO - add name for center
// Center
class Center {
    private String id;
    // TODO - add name for center
    private CenterLocation centerLocation;
    private List<Slot> slots;

    public Center(String id, CenterLocation centerLocation) {
        this.id = id;
        this.centerLocation = centerLocation;
        this.slots = new ArrayList<>();
    }

    public void addSlot(Slot slot) {
        slots.add(slot);
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public CenterLocation getCenterLocation() {
        return centerLocation;
    }
}


// For each slot, We have K workouts
// 5th Nov, 9-10, K Classes
class Slot {
    private String id;
    private String date;
    private CenterLocation centerLocation;
    private int startTime;
    private int endTime;

    Map<String, WorkoutClass> workoutClassList;

    public Slot(String id, String date, int startTime, int endTime, CenterLocation centerLocation) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.centerLocation = centerLocation;
        workoutClassList = new ConcurrentHashMap<>();
    }

//    public List<WorkoutClass> getWorkoutClassList() {
//        return workoutClassList.;
//    }

    public void addWorkOutClass(WorkoutClass workoutClass) {
        workoutClassList.put(workoutClass.getWorkoutType().name(), workoutClass);
    }

    public String getDate() {
        return date;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    // TODO add a workCloutClass
}


class WorkoutClass {
    private String id;
    private Slot slotId;
    private String day;
    private WorkoutType workoutType;
    private int seats;

    // second way;
    public int startTime;
    public int endTime;
    public CenterLocation centerLocation;

    public WorkoutClass(String id, WorkoutType workoutType, int seats, String day, int startTime, int endTime, CenterLocation centerLocation) {
        this.id = id;
        this.workoutType = workoutType;
        this.seats = seats;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.centerLocation = centerLocation;

    }

    public WorkoutType getWorkoutType() {
        return workoutType;
    }

    public int getSeats() {
        return seats;
    }

    public Slot getSlotId() {
        return slotId;
    }

    public synchronized boolean isFull() {
        return seats == 0;
    }

    public synchronized boolean bookSeat() {
        seats = seats - 1;
        return true;
    }

    public String getDay() {
        return day;
    }
}

// Add a booking class;


class User {
    String id;
    String name;
    String email;
    String location;

    private List<WorkoutClass> workoutClasses;

    public User(String id, String name, String email, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.location = location;
        this.workoutClasses = new ArrayList<>();
    }

    public void addWorkoutClass(WorkoutClass workoutClass) {
        workoutClasses.add(workoutClass);
    }

    public List<WorkoutClass> getWorkoutClasses() {
        return workoutClasses;
    }

}

class FitnessService {
    Map<String, User> users;
    Map<String, Center> centers;
    Map<String, Slot> slots;
    Map<String, WorkoutClass> workoutClasses;
    public static FitnessService fitnessService;


    // Singleton pattern to make sure there is only 1 instance of Fitness Service
    public static synchronized FitnessService getFitnessService() {
        if(fitnessService == null) {
            fitnessService = new FitnessService();
        }

        return fitnessService;
    }

    private FitnessService() {
        users = new ConcurrentHashMap<>();
        centers = new ConcurrentHashMap<>();
        slots = new ConcurrentHashMap<>();
        workoutClasses = new ConcurrentHashMap<>();
    }


    public void registerUser(String name, String email, String location) {
        // TODO - add a validation to check if email is unique or not, For now use email as unique identifier, it would be uuid
        if(users.containsKey(email)) {
            System.out.println("User is already registered");
            return;
        }

        User user = new User(email, name, email, location);
        users.put(email, user);
        System.out.println("User is registered" +" : "+ name);
    }

    public void addCenter(CenterLocation centerLocation) {
        // To be replaced with uuid;
        // Add a check for duplicate centers
        String id = centerLocation.name();
        Center center = new Center(id, centerLocation);
        centers.put(id, center);
        System.out.println("Center is added" +" : "+ id);
    }

    public void addWorkout(CenterLocation centerLocation, WorkoutType workoutType, int startTime, int endTime, int capacity, String startDate, String endDate) {
        // get the center;
        Center center = centers.get(centerLocation.name());

        if(startTime >= endTime) {
            System.out.println("Invalid Workout sLot");
            return;
        }

        if(startDate.compareTo(endDate)>0) {
            System.out.println("Invalid Workout slot due to wrong range");
            return;
        }

        // 01-10-24
        // 20-10-24

        int startDay = Integer.parseInt(startDate.split("-")[0]);
        int endDay = Integer.parseInt(endDate.split("-")[0]);

        while(startDay<=endDay) {
            String day = startDay + startDate.substring(2);
//            System.out.println(day);

            // need to add slot for this day;

            // TODO: check if slot is already there for the given day;
            // TODO: StartTime and EndTime;
            // UUID;

            String slotId = centerLocation.name() + day + startTime;
//            System.out.println(slotId);
            Slot slot = null;
            if(slots.containsKey(slotId)) {
                System.out.println("Slot exists, continue");
                startDay++;
                slot = slots.get(slotId);
            } else {
                slot = new Slot(slotId, day, startTime, endTime, centerLocation);
            }

            String workoutClassId = slotId + workoutType.name();

            if(workoutClasses.containsKey((workoutClassId))) {
                System.out.println("Workout exists, continue");
                startDay++;
                continue;
            }

//            Slot slot = new Slot(slotId, day, startTime, endTime, centerLocation);
            slots.put(slotId, slot);
            // add slot to center
            center.addSlot(slot);

            // TODO: Two WorkClasses Duplication
            WorkoutClass workoutClass = new WorkoutClass(workoutClassId, workoutType, capacity, day, startTime, endTime, centerLocation);
            workoutClasses.put(workoutClassId, workoutClass);
            // add workout to class to slot
            slot.workoutClassList.put(workoutType.name(), workoutClass);
//            slot.addWorkOutClass(workoutClass);
            startDay++;

            workoutClasses.put(workoutClassId, workoutClass);
        }

        System.out.println("Workout is added" +":"+ centerLocation.name());
    }

    public void viewWorkoutSlotAvailability(WorkoutType workoutType, String date) {
        System.out.println("Inside viewWorkoutSlotAvailability");
        for(Center center : centers.values()) {
            List<Slot> slots = center.getSlots();
//            System.out.println(slots.toString());
            for(Slot slot: slots) {
//                System.out.println(slot.getDate()+" "+date);
                if(slot.getDate().equals(date)) {
//                    System.out.println("here");
                    if(slot.workoutClassList.containsKey(workoutType.name())) {
                        WorkoutClass workoutClass = slot.workoutClassList.get(workoutType.name());
                        System.out.println( center.getCenterLocation().name() + "," + workoutType.name() + "," + slot.getStartTime() + "," +
                                slot.getEndTime() + "," +  workoutClass.getSeats());
                    }
                }
            }
        }
    }


    public void bookSession(String email, CenterLocation centerLocation, WorkoutType workoutType, int startTime, int endTime, String day) {
        // book slot for a given user;
        Center center = centers.get(centerLocation.name());
        List<Slot> slots = center.getSlots();

        for(Slot slot: slots) {
            if(slot.getDate().equals(day) && slot.getStartTime() == startTime) {
                if(slot.workoutClassList.containsKey(workoutType.name())) {
                    WorkoutClass workoutClass = slot.workoutClassList.get(workoutType.name());

                    if(!workoutClass.isFull()) {
                        workoutClass.bookSeat();

                        // TODO: For given day, startime and endtime, center, User should have unique session
                        // TODO:
                        System.out.println("HEre");
                        User user = users.get(email);
                        user.addWorkoutClass(workoutClass);
                        System.out.println("Session booked");
                        return;
                    }

//                    User user = users.get(email);
//                    user.addWorkoutClass(workoutClass);
                }
            }
        }

        System.out.println("no Session avl");
    }

    // bookinID, slotId, workoutType


    // viewScheduleByDay
    public void viewSchedule(String name, String day) {
        User user = users.get(name);

        List<WorkoutClass> classes = user.getWorkoutClasses();
        for(WorkoutClass wc: classes) {
            if(wc.getDay().equals(day)) {
                System.out.println(wc.centerLocation.name()+" "+wc.getWorkoutType()+" "+wc.getDay()+" "+wc.startTime+" "+wc.endTime);
            }
        }

        // TODO - Create helpers method based on the optional parameter
    }
}




public class FlitnessApp {
    public static void main(String args[]) {

        FitnessService fitnessService = FitnessService.getFitnessService();

        // Feature 1
//        System.out.println("Running Register U")
        fitnessService.registerUser("Sourabh", "sourabh@gmail.com", "HSR");
        fitnessService.registerUser("Jagrit", "jagrit@gmail.com", "HSR");
        fitnessService.registerUser("Jagrit2", "jagrit2@gmail.com", "HSR");

        // Feature 2
        fitnessService.addCenter(CenterLocation.KORAMANGALA);
        fitnessService.addCenter(CenterLocation.BELLANDUR);

        // Feature 3
//        fitnessService.addWorkout(CenterLocation.KORAMANGALA, WorkoutType.WEIGHTS, 7, 6, 100, "01-06-24", "30-06-24");
        fitnessService.addWorkout(CenterLocation.KORAMANGALA, WorkoutType.WEIGHTS, 6, 7, 2, "1-06-24", "30-06-24");
//        fitnessService.addWorkout(CenterLocation.KORAMANGALA, WorkoutType.WEIGHTS, 6, 7, 100, "10-06-24", "25-06-24");
//        fitnessService.addWorkout(CenterLocation.KORAMANGALA, WorkoutType.WEIGHTS, 6, 7, 100, "01-06-24", "30-06-24");
        fitnessService.addWorkout(CenterLocation.KORAMANGALA, WorkoutType.CARDIO, 7, 8, 150, "01-06-24", "30-06-24");
        fitnessService.addWorkout(CenterLocation.KORAMANGALA, WorkoutType.YOGA, 8, 9, 200, "01-06-24", "30-06-24");
        fitnessService.addWorkout(CenterLocation.KORAMANGALA, WorkoutType.SWIMMING, 6, 7, 200, "01-06-24", "30-06-24");
//
        fitnessService.addWorkout(CenterLocation.BELLANDUR, WorkoutType.WEIGHTS, 18, 19, 100, "01-06-24", "30-06-24");
        fitnessService.addWorkout(CenterLocation.BELLANDUR, WorkoutType.CARDIO, 18, 19, 20, "01-06-24", "30-06-24");

        // Feature 4
        System.out.println("Printing Feature44444");
        fitnessService.viewWorkoutSlotAvailability(WorkoutType.WEIGHTS, "20-06-24");

        // Feature 5
        fitnessService.bookSession("sourabh@gmail.com", CenterLocation.KORAMANGALA, WorkoutType.WEIGHTS, 6, 7, "20-06-24");
//        fitnessService.bookSession("jagrit@gmail.com", CenterLocation.KORAMANGALA, WorkoutType.WEIGHTS, 6, 7, "20-06-24");
//        fitnessService.bookSession("jagrit2@gmail.com", CenterLocation.KORAMANGALA, WorkoutType.WEIGHTS, 6, 7, "20-06-24");
//        fitnessService.bookSession("sourabh@gmail.com", CenterLocation.KORAMANGALA, WorkoutType.WEIGHTS, 6, 7, "20-06-24");

        // Feature 4 A
        fitnessService.viewWorkoutSlotAvailability(WorkoutType.WEIGHTS, "20-06-24");

        fitnessService.viewSchedule("sourabh@gmail.com", "20-06-24");
        fitnessService.viewSchedule("jagrit@gmail.com", "20-06-24");



    }
}
