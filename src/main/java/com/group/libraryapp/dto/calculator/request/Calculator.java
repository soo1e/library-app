package com.group.libraryapp.dto.calculator.request;

public class Calculator {
    public int add;
    public int minus;
    public int multiply;

    public Calculator(int num1, int num2) {
        this.add = num1 + num2;
        this.minus = num1 - num2;
        this.multiply = num1 * num2;
    }
}
