package lowleveldesign.designpatterns.creational;


// The requirement is that we want to create the copies of an object. Now one way of doing that would be if we create a new object
// of the same class and then put the values into the new object from the previous. Now this could be costly and repetitive.
// So that's why we say that if there is a need of some objects to be copied again and again why not ask them to give a clone functionality
// now objects that give that functionality are called prototpyes. You can clone them. basically it's class and then object you can clone;
// so first we need is Prorotpye Instance;


// Now this is like creating a blueprint for the classes/object saying that every shape will have clone functionality and can draw
interface Shape {
    Shape clone(); // you can clone any shape
    void draw(); // some more functionality of shape;
}

// now we want concrete functionalities of this shape too;
class Circle implements Shape {
    // now this circle should have a clone functionality;
    private String color;

    // first construct comes as usually;
    public Circle(String color) {
        this.color = color;
    }

    public void draw() {
        System.out.println("I am circle drawing"+" "+color);
    }

    // now comes the important part, How do we clone?
    public Circle clone() {
        return new Circle(this.color); // this is the piece where the class itself supports cloning;
    }

    public void setColor(String color) {
        this.color = color;
    }
}


// This is how client works just putting what you would do into another client class;
class ShapeClient {
    private Shape shapePrototype;

    public ShapeClient(Shape shape) {
        this.shapePrototype = shape;
    }

    public Shape createShape() {
        return shapePrototype.clone();
    }
}


public class PrototypePattern {
    public static void main(String args[]) {
        Circle c1 = new Circle("red");

        // now let's say I have c1 right I want to clone it;
        // now as circle supports it so it is prototype;
        Circle c2 = c1.clone();

        // and after coloing I changed the color
        c2.setColor("blue");

        c1.draw();
        c2.draw(); // this I cloned and changed its color just. Imagine having 100 more properties that would have cloned along with color. So that's where it is usef;

        // you can put client code too;
        // but this is good fornow;

    }
}
