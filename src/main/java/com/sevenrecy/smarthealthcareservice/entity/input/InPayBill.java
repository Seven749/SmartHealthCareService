package com.sevenrecy.smarthealthcareservice.entity.input;

import lombok.Data;

@Data
public class InPayBill {
    private int user_id;
    private String bill_id;
    private String type;
    private double total;
}
