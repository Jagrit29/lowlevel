package lowleveldesign.designpatterns.creational;

// Now this is also about object creation
// Now generally when we create a object, We pass some parameters which are propetries of a class. In some cases it could be 2-3 but in some it could be more.
// Now in this scenario, object creation becomes trickky so we need to break it down into smaller pieces or maybe do step by step;

// Product - This is the actual complex object that we want to construct but in steps
// Builder - This is the interface that declares the steps inorder to build a very complex object above which is Product;
// ConcreteBuilder - Class that implements interface builder, providing specfici implementations for different product
// Director - Responsible for managing the construction process.
// Client - Initiates or Requests the construction of that complex object; It creates builder object and then pass to director which returns the answer;


// Example - Let's say we want to build computers and each computer can have a different type and all CPUs, RAMs what not; Now our job is to make the creation fo
// various kinds of Computers easy;

// now first we have the product - Computer
// Product - Computer
// Builder - Tells you the steps you need to build Computer
// ConcreteBuilder - Implements Builder steps but for specific purposes;

// now first of all LEt's build the product;
// now this is my product;
class Computer {
    private String cpu;
    private String ram;
    private String storage;

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void displayInfo() {
        System.out.println("HEllo I am a computer with"+cpu+" "+ram+" "+storage);
    }
}

// now we have got the product w can see it has different paramters; now to contsutct object of this now we need a buidler;
// builder will tell what steps need to be there;
interface Builder {
    void buildCpu();
    void buildRAM();
    void buildStorage();
    Computer getComputer(); // to get the computer;
}

// now there needs to concreteBuilder for this for different purposes;
// now this will have a computer referece because that's where we will add right;
class GamingComputerBuilder implements Builder {
    Computer gameComp = new Computer();

    public void buildCpu() {
        gameComp.setCpu("Game");
    }

    public void buildRAM() {
        gameComp.setRam("Game");
    }

    public void buildStorage() {
        gameComp.setStorage("Game");
    }

    public Computer getComputer() {
        return gameComp;
    }
}

// now I have my gaming computer build too that does the things 1 by 1 but who calls this? That's where director comes; It will ask to call this steps;
class ComputerDirector {
    public void construct(Builder builder) {
        builder.buildCpu();
        builder.buildStorage();
        builder.buildRAM();
    }
}

public class BuilderPattern {
    public static void main(String args[]) {
        // first you need gaming computerbuilder called via interface; till this part you have followed factor kind oftoyy
        Builder gc = new GamingComputerBuilder();
        // now
        ComputerDirector cd = new ComputerDirector();

        cd.construct(gc); // construct me
        Computer gamingComputer = gc.getComputer();

        gamingComputer.displayInfo();

    }
}
