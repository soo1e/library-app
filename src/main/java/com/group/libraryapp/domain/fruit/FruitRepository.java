package com.group.libraryapp.domain.fruit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
    @Query("SELECT SUM(f.price) FROM Fruit f WHERE f.name = :name AND f.status = :status")
    Double getFruitStat(@Param("name") String name, @Param("status") String status);

    @Query("SELECT COUNT(f) FROM Fruit f WHERE f.name = :name")
    long countByName(@Param("name") String name);

    List<Fruit> findByPriceGreaterThanEqualAndStatus(double price, String status);

    List<Fruit> findByPriceLessThanEqualAndStatus(double price, String status);

    Fruit findByName(String name);
}


