package main.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.model.AllDishes.getAllDishes;

public class PromotionDishes {
    public static List<String> getPromotionItems() {
        String[] str = new String[]{"ITEM0001", "ITEM0022"};
        List<String> myPomotions = Arrays.asList(str);
        return myPomotions;
    }
    public static List<Dishes> getPromotionDishes(){
        List<String> myPomotions = getPromotionItems();
        List<Dishes> allDishes = getAllDishes();
        List<Dishes> promotionDishes =new ArrayList<>();
        for(String promotion: myPomotions){
            for(Dishes dish: allDishes){
                if(promotion.equals(dish.getId())){
                    promotionDishes.add(dish);
                }
            }
        }
        return promotionDishes;
    }
}
