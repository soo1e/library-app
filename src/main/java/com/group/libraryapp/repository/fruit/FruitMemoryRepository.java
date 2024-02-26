package com.group.libraryapp.repository.fruit;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class FruitMemoryRepository implements FruitRepository {

    @Override
    public void addFruit(String name, String warehousingDate, double price, String status) {
        // 메모리에 과일 추가하는 로직
    }

    @Override
    public void markFruitAsSold(long id) {
        // 메모리에서 과일 상태를 'SOLD'로 변경하는 로직
    }

    @Override
    public double getFruitStat(String name, String status) {
        // 메모리에서 특정 과일의 통계를 계산하는 로직
        return 0; // 예시로 0으로 반환
    }
}
