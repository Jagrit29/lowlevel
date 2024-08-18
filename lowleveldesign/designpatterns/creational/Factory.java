package lowleveldesign.designpatterns.creational;
// creational

// so generally we create objects from class right, but here via Factory we create objects from interface but the subclasses that implements that interface
// can alter the type of object that will be created;
// Object via interface
// ObjectTypes determined by subclasses;
// Instead of calling constructor, We call factory method;

/*
Key Concepts:
Interface for Creation: The pattern provides an interface for creating objects but leaves the decision of which class to instantiate to the subclasses.
Flexibility: It allows for flexibility in the types of objects that are created, making the code more modular and easier to extend. It helps to avoid tight coupling
b/w the client and the concreate classes

Creator, Concrete Creator, Product, Concrete Product
 */


// first normally do the stuff;
// abstract because only child will implement nothing to do with factor;
abstract class Vehicle {
    public abstract void printInfo(); // abstract
}

class TwoWheeler extends Vehicle {
    public void printInfo() {
        System.out.println("Hello, I am TwoWheeler created by Jagrit ");
    }
}

class FourWheeler extends Vehicle {
    public void printInfo() {
        System.out.println("Hello, I am FourWheeler created by Jagrit ");
    }
}

// now tight coupling would be if I direcrrly use FourWheeler and TwoWheeler in my code but I don't want to do that I wan to add a layer in b/w;

// now for that comes interface; // this interface will let me create object of type vehicle so should basiclal have vechile int it; with functionality of creating vechle;
interface VehicleFactory {
    Vehicle createVehicle(); // now this right here is what interface is giing me mechanisim to create Vechile object via interface;
}

// now I need factors for my above two contrcre class;
class TwoWheelerFactory implements VehicleFactory {
    public Vehicle createVehicle() {
        return new TwoWheeler(); // so here is the layer that I instablished. Instead of going directly do this I do it via Factory;
    }
}

class FourWheelerFactory implements VehicleFactory {
    public Vehicle createVehicle() {
        return new FourWheeler(); // now the logic is not couple in the client but separated out;
    }
}


// This is the whole sue hwere;
class Client {
    private Vehicle pVehicle;

    // now this client instead of using the direct logic can dynamic use whatever factor we sent it;
    public Client(VehicleFactory factory) {
        pVehicle = factory.createVehicle(); // see here is the magic that we can create our clients now based on the factory object. so our client side is clean
        System.out.println("coming from factory");
        pVehicle.printInfo();
    }
}

public class Factory {
    public static void main(String args[]) {
        // now without Factory method I would do is;
        Vehicle twoW = new TwoWheeler(); // here I am titlye coupled;
        twoW.printInfo(); // this title coupled;

        VehicleFactory twoWf = new TwoWheelerFactory();
        Client c = new Client(twoWf);

    }
}
