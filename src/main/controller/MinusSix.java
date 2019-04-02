package main.controller;

import main.model.Order;

public class MinusSix extends DiscountType {
    @Override
    public double getDiscountPrice(Order myOrder) {
        return 6;
    }
}
