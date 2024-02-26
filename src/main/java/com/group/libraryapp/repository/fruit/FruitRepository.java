package com.group.libraryapp.repository.fruit;

public interface FruitRepository {

    void addFruit(String name, String warehousingDate, double price, String status);

    void markFruitAsSold(long id);

    double getFruitStat(String name, String status);
}