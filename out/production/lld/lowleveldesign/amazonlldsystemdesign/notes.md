Initial thoughts
When we open Amazon what do we see? me as a user I can see products
and add them to wishlist and place a order so this bcomes a requriements

now what else I can see on amazon, I can search for a product too;
Now amazon also has multiple product categories too like electronics etc etc
Search is there, category is there, user mgmt is there. What else.
Inventory?

// now there could be so many requests, so naturally we need concurrency which is capability of handling multiple orders
// now this all looks good;
// now elet's define our entities, etc

// now what I am thinking is classes
User class, Product Class, Order class
ProductCategory ?


To tackle Payment, This is a good usecase to explain the interviewer your knowledge about interfaces

HEre payament can be a interface will have 1 method called ProcessPayment for a particular amount and then different concrete classes can implement that;

Once we have our whole entites together, Now it's time to put this together in one single class which could be our amazon;

## Requirements

1. Users should be able to browse/view products, add them to wishlist/carts, and place orders
2. Users should be able to manage their profile, view order history and track order status
3. System should provide search functionality and support multiple product categories.
4. System should handle inventory mgmt and handle product quantities accordingly
5. System should be able to handle users/requests concurrently and maintain data consistency
6. System should be scalable when it comes to products and users

## Entities

First thing, that I would need is a User

Each User will have its specfic properties and then will have list of Orders
Now Orders here become another entites. Each Order will have specific details and then will have list of OrderItem in particular order and also a Enum of orderStatus
Now here ORderITem becomes another entite, which will have a spceific product and it's quantity. For example a user might be ordering 2 iphones here OrderItem will have Product Iphone and then it's quantity as 2
Now this gives birth to another Entitt here called Product. Each Product will have it's specific properters and quantity. We should be able to update and change quantity.

So what are our Entities then
User
Order
OrderItem
Product
