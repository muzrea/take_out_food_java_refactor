package main;

import main.model.Dishes;
import main.model.Order;
import main.service.BillPrint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import static main.tools.Tools.processInput;
import static main.tools.Tools.processOrder;

public class Main {
    public static void main(String[] args) {
        System.out.println("please input your order : ");
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(inputStreamReader);
        try {
            String inputText = br.readLine();
            Map<String, Double> inputMap = processInput(inputText);
            Order order = new Order(processOrder(inputMap));
            BillPrint myprint = new BillPrint();
            String text = myprint.printBill(order);
            System.out.println(text);
            inputStreamReader.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
