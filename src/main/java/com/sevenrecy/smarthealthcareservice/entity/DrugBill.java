package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

@Data
public class DrugBill {
    private String drug_bill_id;
    private String prescription_id;
    private int user_id;
    private String drug_id;
    private String drug_name;
    private double price;
    private int drug_count;
    private double total;
    private int count;
    private boolean isPay;
}
