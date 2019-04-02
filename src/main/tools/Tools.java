package main.tools;

import main.controller.HalfPrice;
import main.model.Dishes;
import main.model.Order;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static main.model.AllDishes.getAllDishes;
import static main.model.PromotionDishes.getPromotionDishes;

public class Tools {
    public static Map<String, Double> processInput(String input) {
        Map<String, Double> inputMap = new HashMap<>();
        String[] inputArray = input.split("[|,|]");
        List<String> inputList = Arrays.asList(inputArray);
        for (String i : inputList) {
            String[] inputElement = i.split("\"| x |\"");
            inputMap.put(inputElement[1], Double.valueOf(inputElement[2].trim()));
        }
        return inputMap;
    }

    public static Map<Dishes, Double> processOrder(Map<String, Double> inputMap) {
        List<Dishes> allDishes = getAllDishes();
        Map<Dishes, Double> orderInfo = new HashMap<>();
        for (Map.Entry<String, Double> entry : inputMap.entrySet()) {
            for (Dishes dish : allDishes) {
                if (entry.getKey().equals(dish.getId())) {
                    orderInfo.put(dish, entry.getValue());
                }
            }
        }
        return orderInfo;
    }

    public static double getOrderSum(Order myOrder, double discountPrice) {
        return myOrder.getOrignalPrice() - discountPrice;
    }

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

    public static String printBill(Order myOrder) {
        String goodsText = "============= 订餐明细 =============\n";
        for (Map.Entry<Dishes, Double> ele : myOrder.getOrderInfo().entrySet()) {
            goodsText += ele.getKey().getName() + " x " + ele.getValue() + " = " + ele.getKey().getPrice() * ele.getValue() + "元\n";
        }
        goodsText += "-----------------------------------\n";
        HalfPrice half = new HalfPrice();
        if (myOrder.getOrignalPrice() >= 30 && isContainPromotion(myOrder)) {
            double halfdiscount = half.getDiscountPrice(myOrder);
            System.out.println("折扣价"+halfdiscount);
            if (halfdiscount > 6) {
                List<Dishes> orderDiscountDishes = half.getDiscountDishes(myOrder);
                double orderSum = myOrder.getOrignalPrice() - halfdiscount;
                goodsText += "使用优惠:\n指定菜品半价";
                for (Dishes d : orderDiscountDishes) {
                    goodsText += d.getName() + ",";
                }
                goodsText += "省" + halfdiscount + "元\n" + "-----------------------------------\n" +
                        "总计: " + orderSum + "元\n" + "===================================";
            } else {
                double orderSum = myOrder.getOrignalPrice() - 6;
                goodsText += "使用优惠:\n满30减6元，省6元\n" + "-----------------------------------\n" +
                        "总计: " + orderSum + "元\n" + "===================================";
            }
        } else if (myOrder.getOrignalPrice() >= 30) {
            double orderSum = myOrder.getOrignalPrice() - 6;
            goodsText += "使用优惠:\n满30减6元，省6元\n" + "-----------------------------------" +
                    "总计: " + orderSum + "元\n" + "===================================";
        } else if (isContainPromotion(myOrder)) {
            double halfdiscount = half.getDiscountPrice(myOrder);
            List<Dishes> orderDiscountDishes = half.getDiscountDishes(myOrder);
            double orderSum = myOrder.getOrignalPrice() - halfdiscount;
            goodsText += "使用优惠:\n指定菜品半价";
            for (Dishes d : orderDiscountDishes) {
                goodsText += d.getName() + ",";
            }
            goodsText += "省" + halfdiscount + "元\n" + "-----------------------------------\n" +
                    "总计: " + orderSum + "元\n" + "===================================";
        } else {
            goodsText += "总计: " + myOrder.getOrignalPrice() + "元\n" + "===================================";
        }
        return goodsText;
    }
}
