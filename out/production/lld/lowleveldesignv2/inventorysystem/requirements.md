## Inventory Management System
- Track inventory for products across multiple warehouses
- Add stock to specific warehouse
- Remove stock
- Check availability - Given product and quanity return which warehouse can fullfit it
- transfer stocks
- Low stock alerts - these could be notifications, etc which happens when the stock is low (these are for admins that's why low stock alerts)
- One thing is firing alerts, second thing is listening/doing something to those alerts


### When to Alert or How to Alert (Approach)
1. Alert whenever product quantity changes. This is the naive way because let's say alert threshold is that if the quantity is less than 10, send an alert to admin. Now if the quanitty is 9, 8, 7, again 8 everytime the alert will be send.
2. Fire alert just only when the stock goes below threshold. For example is T is 10 and stock went from 11 to 9 and not from 9 to 7.
3. As we will go with coarse locking on warehouse, we don't need to fire alert within syncronised block, we can just get the alert and fire it later;


### Concurrency 
1. Whenever we do write operations, we need to think of concurrency. As multiple addStock could happen on same product within same warehouse, so sync is needed;
2. Approaches: 
- Lock the whole warehouse on single add so that no other warehouse can do write operation on it. Single lock per warehouse
- Lock per product: 