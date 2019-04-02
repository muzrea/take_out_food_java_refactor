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
    
}
