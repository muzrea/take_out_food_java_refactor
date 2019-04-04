package main.tools;

import main.service.ContainPromotionJudgement;
import main.service.HalfPrice;
import main.model.Dishes;
import main.model.Order;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.model.AllDishes.getAllDishes;

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
