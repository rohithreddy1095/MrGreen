package com.mrgreen.farm;

import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.jpa.repository.JpaRepository;
public interface EntryInterface extends JpaRepository<Entry, Long> {
    
}
