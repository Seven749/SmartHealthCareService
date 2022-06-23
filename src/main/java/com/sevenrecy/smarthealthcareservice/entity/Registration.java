package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

@Data
public class Registration {
    private String record_id;
    private int user_id;
    private String user_name;
    private String user_IDCard;
    private String doc_id;
    private String doc_name;
    private int dept_id;
    private String dept_name;
    private int num;
    private double price;
    private String date;
    private String time;
    private String create_time;
    private String confirm;
    private String skip;
}
