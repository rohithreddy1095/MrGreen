package com.mrgreen.farm;

import java.util.Iterator;
import java.util.PriorityQueue;

class LedgerOperations {
    private PriorityQueue<Entry> ledger = new PriorityQueue<Entry>(new LedgerComparator());
    private PriorityQueue<Entry> supplyTable = new PriorityQueue<Entry>(new SupplyComparator());
    private PriorityQueue<Entry> demandTable = new PriorityQueue<Entry>(new DemandComparator());
    private Iterator demandLoop;

    public PriorityQueue<Entry>[] processEntry(Entry e1) {
        try {
            dataEntry(e1);
        } catch (IllegalArgumentException e) {
            System.out.println("The order Id must start with One of s or d");
        }
        return checkTrade(getSupplyTable(), getDemandTable());
    }

    public PriorityQueue<Entry> addEntryIntoTable(PriorityQueue<Entry> table, Entry entry) {
        table.add(entry);
        return table;
    }

    // Cummilative method to enter data into Original Ledger and into supply/demand
    // tables
    private PriorityQueue<Entry>[] dataEntry(Entry entry) throws IllegalArgumentException {

        ledger = addEntryIntoTable(getLedger(), entry);
        if (entry.orderId.contains("s")) {
            supplyTable = addEntryIntoTable(getSupplyTable(), entry);
        } else if (entry.orderId.contains("d")) {
            demandTable = addEntryIntoTable(getDemandTable(), entry);
        } else {
            throw new IllegalArgumentException();
        }
        setLedger(ledger);
        setSupplyTable(supplyTable);
        setDemandTable(demandTable);
        return (new PriorityQueue[] { ledger, supplyTable, demandTable });
    }

    public PriorityQueue<Entry>[] removeDemandTableEntryAndUpdateSupplyTableEntryQuantity(
            PriorityQueue<Entry> supplyTable,
            PriorityQueue<Entry> demandTable) {
        System.out.println(demandTable.peek().orderId + " " + supplyTable.peek().orderId + " "
                + supplyTable.peek().pricePerKg + " " + demandTable.peek().quantiy);
        supplyTable.element().setQuantity(supplyTable.peek().quantiy - demandTable.peek().quantiy);
        demandTable.remove();
        return (new PriorityQueue[] { supplyTable, demandTable });

    }

    public PriorityQueue<Entry>[] removeSupplyTableAndDemandTableEntries(PriorityQueue<Entry> supplyTable,
            PriorityQueue<Entry> demandTable) {
        System.out.println(demandTable.peek().orderId + " " + supplyTable.peek().orderId + " "
                + supplyTable.peek().pricePerKg + " " + demandTable.peek().quantiy);
        supplyTable.remove();
        demandTable.remove();
        return (new PriorityQueue[] { supplyTable, demandTable });
    }

    public PriorityQueue<Entry>[] updateDemandTableEntryAndRemoveSupplyTableEntry(PriorityQueue<Entry> supplyTable,
            PriorityQueue<Entry> demandTable) {
        System.out.println(demandTable.peek().orderId + " " + supplyTable.peek().orderId + " "
                + supplyTable.peek().pricePerKg + " " + supplyTable.peek().quantiy);
        demandTable.element().setQuantity(demandTable.peek().quantiy - supplyTable.peek().quantiy);
        supplyTable.remove();
        return (new PriorityQueue[] { supplyTable, demandTable });
    }

    public boolean isAnyTableEmpty(PriorityQueue<Entry> supplyTable,
            PriorityQueue<Entry> demandTable) {
        if (!(supplyTable.isEmpty() || demandTable.isEmpty())) {
            setDemandLoop(demandTable.iterator());
            return false;
        } else {
            return true;
        }
    }

    // Mehotd to verify the conditions and to print the successsful trades
    private PriorityQueue<Entry>[] checkTrade(PriorityQueue<Entry> supplyTable,
            PriorityQueue<Entry> demandTable) {
        PriorityQueue<Entry>[] updatedSupplyDemmandTables = new PriorityQueue[] { supplyTable, demandTable };
        if (!(supplyTable.isEmpty() || demandTable.isEmpty())) {
            setDemandLoop(demandTable.iterator());
            while (getDemandLoop().hasNext()) {
                if (demandTable.peek().pricePerKg >= supplyTable.peek().pricePerKg) {
                    if (demandTable.peek().quantiy < supplyTable.peek().quantiy) {
                        updatedSupplyDemmandTables = removeDemandTableEntryAndUpdateSupplyTableEntryQuantity(
                                supplyTable, demandTable);
                        if (!isAnyTableEmpty(updatedSupplyDemmandTables[0], updatedSupplyDemmandTables[1]))
                            continue;
                        else
                            break;

                    } else if (demandTable.peek().quantiy == supplyTable.peek().quantiy) {
                        updatedSupplyDemmandTables = removeSupplyTableAndDemandTableEntries(supplyTable, demandTable);
                        if (!isAnyTableEmpty(updatedSupplyDemmandTables[0], updatedSupplyDemmandTables[1]))
                            continue;
                        else
                            break;

                    } else if (demandTable.peek().quantiy > supplyTable.peek().quantiy) {
                        updatedSupplyDemmandTables = updateDemandTableEntryAndRemoveSupplyTableEntry(supplyTable,
                                demandTable);
                        if (!isAnyTableEmpty(updatedSupplyDemmandTables[0], updatedSupplyDemmandTables[1]))
                            continue;
                        else
                            break;

                    }

                } else {
                    break;
                }
            }
        }
        setSupplyTable(supplyTable);
        setDemandTable(demandTable);
        return updatedSupplyDemmandTables;
    }

    public PriorityQueue<Entry> getLedger() {
        return ledger;
    }

    public void setLedger(PriorityQueue<Entry> ledger) {
        this.ledger = ledger;
    }

    public PriorityQueue<Entry> getSupplyTable() {
        return supplyTable;
    }

    public void setSupplyTable(PriorityQueue<Entry> supplyTable) {
        this.supplyTable = supplyTable;
    }

    public PriorityQueue<Entry> getDemandTable() {
        return demandTable;
    }

    public void setDemandTable(PriorityQueue<Entry> demandTable) {
        this.demandTable = demandTable;
    }

    public Iterator getDemandLoop() {
        return demandLoop;
    }

    public void setDemandLoop(Iterator demandLoop) {
        this.demandLoop = demandLoop;
    }

}
