package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

@Data
public class ItemBill {
    private String item_bill_id;
    private String check_item_id;
    private int user_id;
    private String item_id;
    private String item_name;
    private double price;
    private int item_count;
    private double total;
    private int count;
    private boolean isPay;
}
