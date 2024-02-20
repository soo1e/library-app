package com.group.libraryapp.controller.HW;

import com.group.libraryapp.dto.HW.FruitRequest;
import com.group.libraryapp.dto.HW.FruitSoldRequest;
import com.group.libraryapp.dto.HW.FruitStatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HW4Controller {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/api/v1/fruit")
    public FruitRequest addFruit(@RequestBody FruitRequest fruitRequest) {
        // 기본 상태를 'NOT_SOLD'로 설정
        fruitRequest.setStatus("NOT_SOLD");

        String sql = "INSERT INTO fruits (name, warehousingDate, price, status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, fruitRequest.getName(), fruitRequest.getWarehousingDate(),
                fruitRequest.getPrice(), fruitRequest.getStatus());

        return fruitRequest; // 저장된 과일 정보 반환
    }

    @PutMapping("/api/v1/fruit")
    public ResponseEntity<Void> markFruitAsSold(@RequestBody FruitSoldRequest request) {
        String sql = "UPDATE fruits SET status = 'SOLD' WHERE id = ?";
        int updatedRows = jdbcTemplate.update(sql, request.getId());

        if (updatedRows == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitStatResponse getFruitStat(@RequestParam("name") String name) {
        String sql = "SELECT status, SUM(price) as totalAmount FROM fruits WHERE name = ? GROUP BY status";

        AtomicLong salesAmount = new AtomicLong(0);
        AtomicLong notSalesAmount = new AtomicLong(0);

        jdbcTemplate.query(sql, new Object[]{name}, (rs, rowNum) -> {
            String status = rs.getString("status");
            long totalAmount = rs.getLong("totalAmount");

            if ("SOLD".equals(status)) {
                salesAmount.addAndGet(totalAmount);
            } else if ("NOT_SOLD".equals(status)) {
                notSalesAmount.addAndGet(totalAmount);
            }
            return null;
        });

        return new FruitStatResponse(salesAmount.get(), notSalesAmount.get());
    }

}
