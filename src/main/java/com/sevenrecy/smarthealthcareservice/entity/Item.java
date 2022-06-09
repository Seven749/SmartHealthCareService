package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

@Data
public class Item {
    private String item_id;
    private String name;
    private double price;
    private int dept_id;
}
