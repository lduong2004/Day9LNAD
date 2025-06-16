package com.example.day7lnad.models;

public class CartItem {
    public int id;
    public String productId;
    public String name;
    public double price;
    public String image;
    public int quantity;

    public CartItem(int id, String productId, String name, double price, String image, int quantity) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }
}
