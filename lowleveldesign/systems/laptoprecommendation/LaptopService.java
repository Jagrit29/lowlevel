package lowleveldesign.systems.laptoprecommendation;
/*
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

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Laptop
class Laptop {
    String ram;
    String storage;
    String cpu;
    double price;

    public Laptop(String ram, String storage, String cpu, double price) {
        this.ram = ram;
        this.storage = storage;
        this.cpu = cpu;
        this.price = price;
    }
}

interface ComparatorStrategy {

}



//

/*
L1 specs -> Ram 8 GB, SSD 512, CPU - i5 8th gen, Price - 35K
L2 specs -> Ram 16 GB, SSD 512, CPU - i5 8th gen, Price - 55K
L3 specs -> Ram 32 GB, SSD 256, CPU - i3 8th gen, Price - 30K
L4 specs -> Ram 4 GB, SSD 512, CPU - i5 8th gen, Price - 35K
L5 specs -> Ram 32 GB, SSD 512, CPU - i5 8th gen, Price - 35K

// L5, L3, L2, L1, L4 - Ram & SDD all in oneSorted
//



 */
class Attribute {
    String name;
    int weight;

    public Attribute(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
}

public class LaptopService {
    public static void main(String args[]) {

        // we need to include ordering so that our 1 compare works for everything
        List<Attribute> attributes = new ArrayList<>();
        List<Laptop> laptops = new ArrayList<>();

        Laptop l1 = new Laptop("8gb", "512", "i5", 35000);

        Attribute price = new Attribute("PRICE", 20);
        Attribute ram = new Attribute("RAM", 10);
        Attribute ssd = new Attribute("SSD", 5);
        Attribute cpu = new Attribute("CPU", 2);

        attributes.add(ram);
        attributes.add(ssd);
        attributes.add(cpu);

        attributes.sort((a, b) -> b.weight - a.weight); // sorted in decreasing order of weightage;


//        public int compare(Laptop l1, Laptop l2) {
//            for(Attribute attribute: attributes) {
//                if (l1.attribute.name != l2.attribute.name)
//                    return l2.attribute.name. = l1.attribute.name;
//            }
//        }

        // Given a laptop, Show closest laptops to this;

        // 10 Lakhs

        /*
        // Use user's weightage;
         */

        /*
        L1 specs -> Ram 8 GB, SSD 512, CPU - i5 8th gen, Price - 20K
        L2 specs -> Ram 16 GB, SSD 512, CPU - i5 8th gen, Price - 30K
        L3 specs -> Ram 32 GB, SSD 256, CPU - i3 8th gen, Price - 40k
        L4 specs -> Ram 4 GB, SSD 512, CPU - i5 8th gen, Price - 50k
        L5 specs -> Ram 32 GB, SSD 512, CPU - i5 8th gen, Price - 60k
        L6 specs -> Ram 8 GB, SSD 512, CPU - i5 8th gen, Price - 70k
        L7 specs -> Ram 16 GB, SSD 512, CPU - i5 8th gen, Price - 80k
        L8 specs -> Ram 32 GB, SSD 256, CPU - i3 8th gen, Price - 90k
        L9 specs -> Ram 4 GB, SSD 512, CPU - i5 8th gen, Price - 100k
        L10 specs -> Ram 32 GB, SSD 512, CPU - i5 8th gen, Price - 110k

         -> laptop db sorted on price
         laptop db sorted on ram
         user1 -> price 50k, 2 - easy O(log(n)
         user2 -> ram 4gb, 2 - use sorted by logn(n)
         logs indexer
         */

        // L5, L3, L2, L1, L4 - Ram & SDD all in oneSorted
        //


    }
}
