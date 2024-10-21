package practice.interviewprep.flipkart;

// Inventory Mgmt System;
// Mainly for inventory first two things that comes to mind will products, inventory, order, etc etc


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Product {
    private int id;
    private String name;
    private double price;

    // constructor, getters and setters
    public Product(){

    }
}

class Inventory {
    // This is concurrent hashmap;
    private Map<Integer, Product> products = new ConcurrentHashMap<>();
}

public class Question1 {
}

// How does Concurrent HashMap works? It helps in multithreading by giving the funcitonality of fine grained locking
// Diving the map into segments and only
// putIfAbset, compute and replace methods o concurrent hashmap are atomic in nature
