package main.controller;

import main.model.Order;

public abstract class DiscountType {
    public abstract double getDiscountPrice(Order myOrder);
}
