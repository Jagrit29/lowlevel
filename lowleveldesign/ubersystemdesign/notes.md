Okay, time for another low level design for system like Uber ride sharing app;
You can think of Rapido, Uber, Ola, etc

Now Let's see what are the commons things or requirements

1. User/Passengers who wants to book a ride from point A to point B and he wants to book a ride of paritcular type
2. Now from the above only, I can think of Passenger and Ride being the separate entitites
3. Then apart from Passengers there is a Driver too who takes the user from A to B
4. Once the ride is complete, The payment also comes.

// now I have my requirements definied, now lets' sread through the requirement and see what all entties I can think of


1. First will be the Passengers, What all he will have. Passenger will have id, name and all his previous rides, Passenger will also have it's location lets
2. Driver will also have id name and all his previous ride driver will also have it's location, Driver will have a status wehther he is free or not
3. There will be a entity for Ride as well like it could be ongoing ride or completed ride. This can be have RideStatus as enum it will have proeprs like who is the passengers who is the driver pick location drop location
4. Then we will have payment as well
5. Now hat we need something that will create the Ride, mayb
6. now Location also will be determined by a class called Location Class
7. RideService is just liek AmazonService like singleton, we can think of this as actual uber app


// as usual we will start will the smallest entity that one can understand;



## Requirements

1. Passengers/Users should be able to request ride from location A to B, and drivers to accept the ride and fullfil those requests
2. Passengers should be able to select Pickup Location, Drop Location and the Ride Type
3. Driver should be able to see multiple available ride requests and choose to accept or decline them
4. System should match the ride requests based on some factors like proximity, etc.
5. System should calculate the ride fare based on the distance, ride type and time.
6. System should be able to handle payment once the ride is complete
7. System should provide real-time location tracking while the ride is ongoing and notify the riders too
8. System should be able to handle concurrent users and maintain data consistency with high availability.


## Entities

1. Passenger
2. Driver
3. Ride
4. Location
5. Payment
6. RideService
7. RideServiceDemo
