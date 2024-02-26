package com.group.libraryapp.controller.HW;

import com.group.libraryapp.dto.HW.FruitListResponse;
import com.group.libraryapp.dto.HW.FruitRequest;
import com.group.libraryapp.dto.HW.FruitSoldRequest;
import com.group.libraryapp.dto.HW.FruitStatResponse;
import com.group.libraryapp.service.fruit.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/fruit")
public class FruitController {

    @Autowired
    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping
    public FruitRequest addFruit(@RequestBody FruitRequest fruitRequest) {
        return fruitService.addFruit(fruitRequest);
    }

    @PutMapping
    public ResponseEntity<Void> markFruitAsSold(@RequestBody FruitSoldRequest request) {
        fruitService.markFruitAsSold(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/stat")
    public FruitStatResponse getFruitStat(@RequestParam("name") String name) {
        return fruitService.getFruitStat(name);
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> countFruitsByName(@RequestParam("name") String name) {
        long count = fruitService.countFruitsByName(name);
        Map<String, Long> responseBody = new HashMap<>();
        responseBody.put("count", count);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FruitListResponse>> getFruitsByPriceOption(
            @RequestParam("option") String option,
            @RequestParam("price") double price) {
        List<FruitListResponse> fruits;
        if ("GTE".equals(option)) {
            fruits = fruitService.getFruitsWithPriceGreaterThanOrEqual(price);
        } else if ("LTE".equals(option)) {
            fruits = fruitService.getFruitsWithPriceLessThanOrEqual(price);
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(fruits);
    }
}


