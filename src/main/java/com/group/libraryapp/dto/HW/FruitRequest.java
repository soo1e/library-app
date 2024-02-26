package com.group.libraryapp.dto.HW;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FruitRequest {
    private String name;
    private LocalDate warehousingDate;
    private long price;
    private String status;

    public FruitRequest(String name, LocalDate warehousingDate, long price, String status) {
        this.name = name;
        this.warehousingDate = warehousingDate;
        this.price = price;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getWarehousingDateAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return warehousingDate.format(formatter);
    }


    public long getPrice() {
        return price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }
}