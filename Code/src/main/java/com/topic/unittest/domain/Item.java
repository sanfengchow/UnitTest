package com.topic.unittest.domain;

import lombok.Data;


@Data
public class Item {


    private int id;
    private String name;
    private int price;
    private int quantity;

    private int value;

    public Item() {

    }

    public Item(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
