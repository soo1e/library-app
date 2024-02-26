package com.group.libraryapp.controller.HW;

import com.group.libraryapp.dto.HW.FruitRequest;
import com.group.libraryapp.dto.HW.FruitSoldRequest;
import com.group.libraryapp.dto.HW.FruitStatResponse;
import com.group.libraryapp.service.fruit.FruitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fruit")
public class FruitController {

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
}


