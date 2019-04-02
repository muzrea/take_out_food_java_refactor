package main.model;

import java.util.ArrayList;
import java.util.List;

public class AllDishes {
    public static List<Dishes> getAllDishes() {
        Dishes item1 = new Dishes("ITEM0001", "黄焖鸡", 18.00);
        Dishes item2 = new Dishes("ITEM0013", "肉夹馍", 6.00);
        Dishes item3 = new Dishes("ITEM0022", "凉皮", 8.00);
        Dishes item4 = new Dishes("ITEM0030", "冰锋", 2.00);
        List<Dishes> allDishes = new ArrayList<Dishes>();
        allDishes.add(item1);
        allDishes.add(item2);
        allDishes.add(item3);
        allDishes.add(item4);
        return allDishes;
    }
}
