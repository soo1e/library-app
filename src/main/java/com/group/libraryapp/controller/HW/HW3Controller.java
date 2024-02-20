package com.group.libraryapp.controller.HW;

import com.group.libraryapp.dto.HW.DayOfTheWeekResponse;
import com.group.libraryapp.dto.HW.NumbersRequest;
import com.group.libraryapp.dto.calculator.request.Calculator;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class HW3Controller {
    @GetMapping("/api/v1/calc")
    public Calculator Numbers (@RequestParam("num1") int num1, @RequestParam("num2") int num2) {
        return new Calculator(num1, num2);
    }

    @GetMapping("/api/v1/day-of-the-week")
    public DayOfTheWeekResponse getDayOfTheWeek(@RequestParam("date") String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        return new DayOfTheWeekResponse(dayOfWeek.toString().substring(0, 3));
    }

    @PostMapping("/api/v1/sum")
    public int calculateSum(@RequestBody NumbersRequest numbersRequest) {
        int sum = 0;
        for (Integer number : numbersRequest.getNumbers()) {
            sum += number;
        }
        return sum;
    }
}
