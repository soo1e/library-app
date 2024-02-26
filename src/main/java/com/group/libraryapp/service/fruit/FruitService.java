package com.group.libraryapp.service.fruit;

import com.group.libraryapp.dto.HW.FruitRequest;
import com.group.libraryapp.dto.HW.FruitSoldRequest;
import com.group.libraryapp.dto.HW.FruitStatResponse;
import com.group.libraryapp.repository.fruit.FruitRepository;
import org.springframework.stereotype.Service;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public FruitRequest addFruit(FruitRequest fruitRequest) {
        fruitRequest.setStatus("NOT_SOLD");
        fruitRepository.addFruit(fruitRequest.getName(), fruitRequest.getWarehousingDateAsString(),
                fruitRequest.getPrice(), fruitRequest.getStatus());
        return fruitRequest;
    }

    public void markFruitAsSold(FruitSoldRequest request) {
        fruitRepository.markFruitAsSold(request.getId());
    }

    public FruitStatResponse getFruitStat(String name) {
        long soldAmount = Math.round(fruitRepository.getFruitStat(name, "SOLD"));
        long notSoldAmount = Math.round(fruitRepository.getFruitStat(name, "NOT_SOLD"));
        return new FruitStatResponse(soldAmount, notSoldAmount);
    }
}
