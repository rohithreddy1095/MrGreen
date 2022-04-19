package com.mrgreen.farm;

import java.util.Comparator;

class LedgerComparator implements Comparator<Entry> {

    // Original Ledger with entries as per time order
    @Override
    public int compare(Entry e1, Entry e2) {
        if (e1.time.compareTo(e2.time) > 0)
            return 1;
        else if (e1.time.compareTo(e2.time) < 0)
            return -1;
        return 0;
    }
}