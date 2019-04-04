package main.service;

import main.model.Dishes;
import main.model.Order;

import java.util.List;
import java.util.Map;

public class BillPrint {
    public static String printBill(Order myOrder) {
        String goodsText = "============= 订餐明细 =============\n";
        for (Map.Entry<Dishes, Double> ele : myOrder.getOrderInfo().entrySet()) {
            goodsText += ele.getKey().getName() + " x " + ele.getValue() + " = " + ele.getKey().getPrice() * ele.getValue() + "元\n";
        }
        goodsText += "-----------------------------------\n";
        HalfPrice half = new HalfPrice();
        ContainPromotionJudgement promotionJudgement = new ContainPromotionJudgement();
        if (myOrder.getOriginalPrice() >= 30 && promotionJudgement.isContainPromotion(myOrder)) {
            double halfdiscount = half.getDiscountPrice(myOrder);
            if (halfdiscount > 6) {
                List<Dishes> orderDiscountDishes = half.getOrderPromotionDishes();
                double orderSum = myOrder.getOriginalPrice() - halfdiscount;
                goodsText += "使用优惠:\n指定菜品半价";
                for (Dishes d : orderDiscountDishes) {
                    goodsText += d.getName() + ",";
                }
                goodsText += "省" + halfdiscount + "元\n" + "-----------------------------------\n" +
                        "总计: " + orderSum + "元\n" + "===================================";
            } else {
                double orderSum = myOrder.getOriginalPrice() - 6;
                goodsText += "使用优惠:\n满30减6元，省6元\n" + "-----------------------------------\n" +
                        "总计: " + orderSum + "元\n" + "===================================";
            }
        } else if (myOrder.getOriginalPrice() >= 30) {
            double orderSum = myOrder.getOriginalPrice() - 6;
            goodsText += "使用优惠:\n满30减6元，省6元\n" + "-----------------------------------" +
                    "总计: " + orderSum + "元\n" + "===================================";
        } else if (promotionJudgement.isContainPromotion(myOrder)) {
            double halfdiscount = half.getDiscountPrice(myOrder);
            List<Dishes> orderDiscountDishes = half.getOrderPromotionDishes();
            double orderSum = myOrder.getOriginalPrice() - halfdiscount;
            goodsText += "使用优惠:\n指定菜品半价";
            for (Dishes d : orderDiscountDishes) {
                goodsText += d.getName() + ",";
            }
            goodsText += "省" + halfdiscount + "元\n" + "-----------------------------------\n" +
                    "总计: " + orderSum + "元\n" + "===================================";
        } else {
            goodsText += "总计: " + myOrder.getOriginalPrice() + "元\n" + "===================================";
        }
        return goodsText;
    }
}
