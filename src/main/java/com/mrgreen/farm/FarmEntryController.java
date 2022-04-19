package com.mrgreen.farm;

import java.util.List;
import java.util.PriorityQueue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FarmEntryController {

    private final EntryInterface repository;

    FarmEntryController(EntryInterface repository) {
        this.repository = repository;
    }

    public static LedgerOperations ledgerOperations = new LedgerOperations();

    @GetMapping("/home")
    public String sayHello() {
        return "Hello From home";
    }

    @GetMapping("/entry")
    public List<Entry> all() {
        return repository.findAll();
    }

    @PostMapping("/addNewEntry")
    Entry newEntry(@RequestBody Entry newEntry) {
        return repository.save(newEntry);
    }

    @PostMapping("/addEntry")
    public PriorityQueue<Entry>[] addEntry(@RequestBody Entry newEntry) {
        return ledgerOperations.processEntry(newEntry);

    }
}
