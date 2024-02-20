package com.group.libraryapp.dto.HW;

public class DayOfTheWeekResponse {
    private String dayOfTheWeek;

    public DayOfTheWeekResponse(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }
}
