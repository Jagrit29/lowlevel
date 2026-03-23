With around 60 mins to spare interviewer asked me the LLD of a laptop recommendation/sorting system where we are given a laptop specs
lets say


Laptop Selected (LS) - specs -> Ram 8 GB, SSD - 512 GB, CPU - i7 8th generation, Price - 39K
and we have list of laptops stored in our system for now we can assume all data is present in memory
for example we have following laptops->
L1 specs -> Ram 8 GB, SSD 512, CPU - i5 8th gen, Price - 35K
L2 specs -> Ram 16 GB, SSD 512, CPU - i5 8th gen, Price - 55K
L3 specs -> Ram 8 GB, SSD 256, CPU - i3 8th gen, Price - 30K
L4 specs -> Ram 4 GB, SSD 512, CPU - i5 8th gen, Price - 35K


return the list of laptops in order of number of attributes being closest where there could be weightage possible for a particular attribute - in decreasing order and select top n
for example lets say weightage of price is heighest. so for above case if we are given LS laptop so the order list of top 2 closest laptops is
L1 -> L2


Interviewer asked me to design a solution which will be extendable for lets say in future we have another line of products and new attribute can be added to given product and will come handy in manipulating the recommendation logic.


I was able to come up with different entities and how it will be scable and easily extendable.


Still 15 mins to spare recuiter asked me the HLD of above problem and how I will be able to scale and steps I will take to authenticate requests etc.