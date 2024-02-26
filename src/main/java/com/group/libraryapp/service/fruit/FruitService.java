package com.group.libraryapp.service.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.domain.fruit.FruitRepository;
import com.group.libraryapp.dto.HW.FruitListResponse;
import com.group.libraryapp.dto.HW.FruitRequest;
import com.group.libraryapp.dto.HW.FruitSoldRequest;
import com.group.libraryapp.dto.HW.FruitStatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    @Autowired
    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Transactional
    public FruitRequest addFruit(FruitRequest fruitRequest) {
        Fruit fruit = new Fruit();
        fruit.setName(fruitRequest.getName());
        fruit.setWarehousingDate(fruitRequest.getWarehousingDate());
        fruit.setPrice(fruitRequest.getPrice());
        fruit.setStatus("NOT_SOLD");

        fruitRepository.save(fruit);

        return fruitRequest;
    }

    @Transactional
    public void markFruitAsSold(FruitSoldRequest request) {
        Fruit fruit = fruitRepository.findById(request.getId()).orElseThrow(IllegalArgumentException::new);
        fruit.setStatus("SOLD");
        fruitRepository.save(fruit);
    }

    @Transactional(readOnly = true)
    public FruitStatResponse getFruitStat(String name) {
        Double soldAmount = fruitRepository.getFruitStat(name, "SOLD");
        Double notSoldAmount = fruitRepository.getFruitStat(name, "NOT_SOLD");
        long sold = soldAmount != null ? soldAmount.longValue() : 0;
        long notSold = notSoldAmount != null ? notSoldAmount.longValue() : 0;

        return new FruitStatResponse(sold, notSold);
    }

    @Transactional(readOnly = true)
    public long countFruitsByName(String name) {
        return fruitRepository.countByName(name);
    }

    @Transactional(readOnly = true)
    public List<Fruit> listFruitsByPriceOption(String option, double price) {
        if (option.equalsIgnoreCase("GTE")) {
            return fruitRepository.findByPriceGreaterThanEqualAndStatus(price, "NOT_SOLD");
        } else if (option.equalsIgnoreCase("LTE")) {
            return fruitRepository.findByPriceLessThanEqualAndStatus(price, "NOT_SOLD");
        } else {
            throw new IllegalArgumentException("Invalid option value. Use 'GTE' or 'LTE'.");
        }
    }

    @Transactional(readOnly = true)
    public List<FruitListResponse> getFruitsWithPriceGreaterThanOrEqual(double price) {
        List<Fruit> fruits = fruitRepository.findByPriceGreaterThanEqualAndStatus(price, "NOT_SOLD");
        return fruits.stream()
                .map(this::mapToFruitListResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FruitListResponse> getFruitsWithPriceLessThanOrEqual(double price) {
        List<Fruit> fruits = fruitRepository.findByPriceLessThanEqualAndStatus(price, "NOT_SOLD");
        return fruits.stream()
                .map(this::mapToFruitListResponse)
                .collect(Collectors.toList());
    }

    private FruitListResponse mapToFruitListResponse(Fruit fruit) {
        FruitListResponse response = new FruitListResponse();
        response.setName(fruit.getName());
        response.setPrice(fruit.getPrice());
        response.setWarehousingDate(fruit.getWarehousingDate());
        return response;
    }
}
