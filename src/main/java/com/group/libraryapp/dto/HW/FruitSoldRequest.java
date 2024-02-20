package com.group.libraryapp.dto.HW;

public class FruitSoldRequest {
    private long id;

    // 기본 생성자를 명시적으로 추가
    public FruitSoldRequest() {
    }

    // id 필드에 대한 세터 메서드
    public void setId(long id) {
        this.id = id;
    }

    // id 필드에 대한 게터 메서드
    public long getId() {
        return id;
    }
}
