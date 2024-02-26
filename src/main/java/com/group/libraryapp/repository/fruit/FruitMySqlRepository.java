package com.group.libraryapp.repository.fruit;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FruitMySqlRepository implements FruitRepository {

    private final JdbcTemplate jdbcTemplate;

    public FruitMySqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addFruit(String name, String warehousingDate, double price, String status) {
        String sql = "INSERT INTO fruits (name, warehousingDate, price, status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, name, warehousingDate, price, status);
    }

    @Override
    public void markFruitAsSold(long id) {
        String sql = "UPDATE fruits SET status = 'SOLD' WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public double getFruitStat(String name, String status) {
        String sql = "SELECT SUM(price) as totalAmount FROM fruits WHERE name = ? AND status = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, name, status);
    }
}
