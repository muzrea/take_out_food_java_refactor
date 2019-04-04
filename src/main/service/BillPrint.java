package main.service;

import main.model.Dishes;
import main.model.Order;

import java.util.List;
import java.util.Map;

public class BillPrint {
    public static String getBill(Order myOrder){
        String  text = "============= 订餐明细 =============\n";
        for (Map.Entry<Dishes, Double> ele : myOrder.getOrderInfo().entrySet()) {
            text += ele.getKey().getName() + " x " + ele.getValue() + " = " + ele.getKey().getPrice() * ele.getValue() + "元\n";
        }
        text += "-----------------------------------\n";
        return text;
    }
    public static String HalfPriceText(Order myOrder,String separateLine,String separateLineEnd){
        String halfText = "";
        HalfPrice half = new HalfPrice();
        double halfdiscount = half.getDiscountPrice(myOrder);
        List<Dishes> orderDiscountDishes = half.getOrderPromotionDishes();
        double orderSum = myOrder.getOriginalPrice() - halfdiscount;
        halfText += "使用优惠:\n指定菜品半价";
        for (Dishes d : orderDiscountDishes) {
            halfText += d.getName() + ",";
        }
        halfText += "省" + halfdiscount + "元\n" + separateLine +"总计: " + orderSum + "元\n" + separateLineEnd;
        return halfText;
    }
    public static String MinusSixText(Order myOrder,String separateLine,String separateLineEnd){
        double orderSum = myOrder.getOriginalPrice() - 6;
        return "使用优惠:\n满30减6元，省6元\n" + separateLine +
                "总计: " + orderSum + "元\n" + separateLineEnd;
    }

    public static String printBill(Order myOrder) {
        String goodsText = getBill(myOrder);
        HalfPrice half = new HalfPrice();
        String separateLine = "-----------------------------------\n";
        String separateLineEnd = "===================================";
        ContainPromotionJudgement promotionJudgement = new ContainPromotionJudgement();
        if (myOrder.getOriginalPrice() >= 30 && promotionJudgement.isContainPromotion(myOrder)) {
            double halfdiscount = half.getDiscountPrice(myOrder);
            if (halfdiscount > 6) {
                goodsText += HalfPriceText(myOrder,separateLine,separateLineEnd);
            } else {
                goodsText += MinusSixText(myOrder,separateLine,separateLineEnd);
            }
        } else if (myOrder.getOriginalPrice() >= 30) {
            goodsText += MinusSixText(myOrder,separateLine,separateLineEnd);
        } else if (promotionJudgement.isContainPromotion(myOrder)) {
            goodsText += HalfPriceText(myOrder,separateLine,separateLineEnd);
        } else {
            goodsText += "总计: " + myOrder.getOriginalPrice() + "元\n" + separateLineEnd;
        }
        return goodsText;
    }
}
