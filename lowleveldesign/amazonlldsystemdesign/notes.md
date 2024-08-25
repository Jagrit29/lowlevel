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




## Requirements 
1. Users should be able to browse/view products, add them to wishlist/carts, and place orders
2. Users should be able to manage their profile, view order history and track order status 
3. System should provide search functionality and support multiple product categories.
4. System should handle inventory mgmt and handle product quantities accordingly 
5. System should be able to handle users/requests concurrently and maintain data consistency
6. System should be scalable when it comes to products and users 

## Entities 