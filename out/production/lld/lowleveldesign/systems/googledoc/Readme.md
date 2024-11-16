Requirements
- Multiple Users can open/edit a Document at the same time
- Multiple Users should not be overriding each other changes at the same time (concurrency)
- Multiple Sessions for each user (Nice to Have)
- Versions in Document (Autosave functionality in some X seconds/minutes)
- Maintain Previous 10 versions
- User should be able to see each other changes instantly (low latency)
- Cursors of all the active users
- Limit the number of users



Core Entities & Interface
- User - id, name, concurrentSessions[]
- Document - id, name, activeUsers[], concurrentSessions[], Enum, state(new, saved), versionNumber
- DocumentManager -
- LockManager
- DocumentService
- Session - id, documentId, userId, startDate, endDate




Design Alexa (Low Level Design)
- Alexa Battery Level 
- Alexa Battery Charts
- Alexa Room Temperature 
- Alexa Weather
- Alexa Play Music for Me (User Profile)



// Design Google Doc (1.30)
// Design Alexa (1.30)
// Design Checkout Basket (1.30)

// MMT
// give Customer Credits and Vouchers on basis on what of orders etc (CRED)
// Credit is directly credited to the wallet of the customer and vouchers are stacked 
// Discount Coupon/Vouchers
// When checking out, Show the best vouchers/credit to the customer from its stack in an increasing to decreasing discount
// Permutations of vouchers and credits and vice versa
// Give the options to the customer and calculate final price of checkout



// LLD Expedia
