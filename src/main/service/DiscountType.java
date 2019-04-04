package main.service;

import main.model.Order;

public abstract class DiscountType {
    public abstract double getDiscountPrice(Order myOrder);
}
