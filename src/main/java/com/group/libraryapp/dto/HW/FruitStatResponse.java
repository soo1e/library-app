package com.group.libraryapp.dto.HW;

public class FruitStatResponse {
    private long salesAmount;
    private long notSalesAmount;

    // 생성자
    public FruitStatResponse(long salesAmount, long notSalesAmount) {
        this.salesAmount = salesAmount;
        this.notSalesAmount = notSalesAmount;
    }

    // 게터 및 세터
    public long getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(long salesAmount) {
        this.salesAmount = salesAmount;
    }

    public long getNotSalesAmount() {
        return notSalesAmount;
    }

    public void setNotSalesAmount(long notSalesAmount) {
        this.notSalesAmount = notSalesAmount;
    }
}
