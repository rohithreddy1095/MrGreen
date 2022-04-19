package com.mrgreen.farm;

import java.time.LocalTime;

// import org.springframework.boot.autoconfigure.domain.EntityScan;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Entry {
    private @Id @GeneratedValue Long id;
    public String orderId;
    public String produce;
    public LocalTime time;
    public int pricePerKg;
    public int quantiy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(int pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public int getQuantiy() {
        return quantiy;
    }

    public void setQuantiy(int quantiy) {
        this.quantiy = quantiy;
    }

    Entry() {
    }

    // A parameterized Constructor for Entry Data
    public Entry(String orderId, String time, String produce, int pricePerKg, int quantiy) {
        this.orderId = orderId;
        this.produce = produce;
        this.time = LocalTime.parse(time);
        this.pricePerKg = pricePerKg;
        this.quantiy = quantiy;
    }

    // Setter to update quantity of specific supply/demand after trade
    public void setQuantity(int updatedQuantity) {
        this.quantiy = updatedQuantity;
    }
}
