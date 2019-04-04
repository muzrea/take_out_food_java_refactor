package main.controller;

import main.model.Dishes;
import main.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static main.model.PromotionDishes.getPromotionDishes;

public class HalfPrice extends DiscountType {
    private List<Dishes> orderPromotionDishes;

    public List<Dishes> getOrderPromotionDishes() {
        return this.orderPromotionDishes;
    }

    @Override
    public double getDiscountPrice(Order myOrder) {
        double discountPrice = 0.00;
        List<Dishes> promotionDishes = getPromotionDishes();
        List<Dishes> orderPromotionDishes = new ArrayList<Dishes>();
        for (Map.Entry<Dishes, Double> orderElement : myOrder.getOrderInfo().entrySet()) {
            for (Dishes promotionDish : promotionDishes) {
                if (orderElement.getKey().getId().equals(promotionDish.getId())) {
                    discountPrice += (promotionDish.getPrice() / 2) * orderElement.getValue();
                    orderPromotionDishes.add(promotionDish);
                }
            }
        }
        this.orderPromotionDishes = orderPromotionDishes;
        return discountPrice;
    }
}
