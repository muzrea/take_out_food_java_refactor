package main.service;

import main.model.Dishes;
import main.model.Order;

import java.util.List;
import java.util.Map;

import static main.model.PromotionDishes.getPromotionDishes;

public class ContainPromotionJudgement {
    public static boolean isContainPromotion(Order myOrder) {
        boolean flag = false;
        List<Dishes> promotionDishes = getPromotionDishes();
        for (Map.Entry<Dishes, Double> orderElement : myOrder.getOrderInfo().entrySet()) {
            for (Dishes promotionDish : promotionDishes) {
                if (orderElement.getKey().getId().equals(promotionDish.getId())) {
                    flag = true;
                }
            }
        }
        return flag;
    }
}
