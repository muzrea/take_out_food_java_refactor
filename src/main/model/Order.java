package main.model;

import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import main.model.Dishes;
public class Order {
    private Map<Dishes,Double> orderInfo = new HashMap<Dishes,Double>();
    public Order(Map<Dishes,Double> orderInfo){
        this.orderInfo = orderInfo;
    }
    public void setOrderInfo(Map<Dishes,Double> orderInfo){
        this.orderInfo = orderInfo;
    }
    public Map<Dishes,Double> getOrderInfo(){
        return this.orderInfo;
    }
    public double getOriginalPrice(){
        double originalPrice = 0.00;
        for(Map.Entry<Dishes,Double> entry : this.orderInfo.entrySet()){
            originalPrice += entry.getKey().getPrice()*entry.getValue();
        }
        return originalPrice;
    }
}
