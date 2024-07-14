package lowleveldesign;
import java.util.*;

// Encapsulation
class Encapsulation {
    private String name;

    public Encapsulation(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void setName(String newName) {
        this.name = newName;
    }
}

// Inheritance
class Vehicle {
    private String brand;
    private int year;

    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }
}
class Car extends Vehicle {
    private String model;
    private int maxSpeed;

    public Car(String brand, int year, String model, int maxSpeed) {
        // Call the constructor of the superclass
        super(brand, year);
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    // Overriding the displayInfo method
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call the superclass method
        System.out.println("Model: " + model + ", Max Speed: " + maxSpeed + " km/h");
    }
}

// Polymorphism
class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}


// Abstraction
// Abstract class
abstract class Shape {
    // Abstract method (no implementation)
    public abstract void draw();

    // Concrete method
    public void display() {
        System.out.println("Displaying shape.");
    }
}
// Subclass
class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle.");
    }
}
class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle.");
    }
}


// Abstraction using interfaces
interface Payment {
    void processPayment(double amount); // Abstract method
}

class CreditCardPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
        // Additional implementation details (e.g., transaction processing)
    }
}

class PayPalPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        // Additional implementation details (e.g., API calls)
    }
}

public class Learn {
    public static void main() {
        Encapsulation test = new Encapsulation("Jagrit");
        System.out.println(test.getName());

        Payment payment1 = new CreditCardPayment();
        payment1.processPayment(100.0); // Output: Processing credit card payment of $100.0

        Payment payment2 = new PayPalPayment();
        payment2.processPayment(200.0); // Output: Processing PayPal payment of $200.0
    }
}


/*
Sure! Here’s a concise summary of the main Object-Oriented Programming (OOP) principles along with their advantages:

### 1. Encapsulation
- **Definition:** Bundles data and methods into a single unit (class) and restricts access to some of the object's components.
- **Advantages:**
  - Protects the internal state of an object.
  - Provides controlled access through public methods (getters/setters).
  - Enhances maintainability and reduces complexity.

### 2. Abstraction
- **Definition:** Hides complex implementation details and exposes only essential features.
- **Advantages:**
  - Simplifies the interface for users.
  - Reduces complexity by focusing on "what" an object does rather than "how" it does it.
  - Facilitates easier code management and flexibility.

### 3. Inheritance
- **Definition:** Allows one class (subclass) to inherit fields and methods from another class (superclass).
- **Advantages:**
  - Promotes code reusability and reduces redundancy.
  - Supports method overriding for specific behaviors in subclasses.
  - Establishes a natural hierarchy among classes.

### 4. Polymorphism
- **Definition:** Allows methods to do different things based on the object it is acting upon, using method overloading and overriding.
- **Advantages:**
  - Enhances flexibility and allows for dynamic method resolution.
  - Facilitates code that can work with objects of different classes through a common interface or superclass.
  - Reduces complexity in code maintenance and enhancement.

These principles together enable developers to create more organized, modular, and reusable code, making software development more efficient and scalable. If you have any more questions or need further clarification on any principle, feel free to ask!

 */



