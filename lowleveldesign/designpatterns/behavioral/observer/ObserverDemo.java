package lowleveldesign.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();
}

interface Observer {
    void sendNotify();
}

class Topic implements Subject {
    List<Observer> observerList;
    int state;

    public Topic() {
        observerList = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    public  void setState(int x) {
        this.state = x;
        notifyObserver();
    }

    public void notifyObserver() {
        for(Observer ob: observerList) {
            ob.sendNotify();
        }
    }
}

class Subscriber implements Observer {
    public void sendNotify() {
        System.out.println("Topic said hello");
    }
}


public class ObserverDemo {
    public static  void main(String args[]) {
        Topic topic1 = new Topic();

        Subscriber subscriber1 = new Subscriber();
        Subscriber subscriber2 = new Subscriber();

        topic1.addObserver(subscriber1);
        topic1.addObserver(subscriber2);

        topic1.setState(10);
    }
}
