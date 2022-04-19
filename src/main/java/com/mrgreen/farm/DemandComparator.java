package com.mrgreen.farm;

import java.util.Comparator;

class DemandComparator implements Comparator<Entry> {

    // Overriding compare()method of Comparator
    // for DECENDING order of price
    // if same price then ascending order of time
    @Override
    public int compare(Entry e1, Entry e2) {
        if (e1.pricePerKg < e2.pricePerKg)
            return 1;
        else if (e1.pricePerKg > e2.pricePerKg)
            return -1;
        else {
            if (e1.time.compareTo(e2.time) > 0)
                return 1;
            else if (e1.time.compareTo(e2.time) < 0)
                return -1;
            return 0;
        }
    }
}
