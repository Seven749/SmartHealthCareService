package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

@Data
public class Registration {
    private String record_id;
    private int user_id;
    private String user_name;
    private String doc_id;
    private String doc_name;
    private int num;
    private double price;
    private String time;
    private String create_time;
}
