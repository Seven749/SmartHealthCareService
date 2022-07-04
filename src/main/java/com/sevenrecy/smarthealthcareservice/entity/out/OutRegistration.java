package com.sevenrecy.smarthealthcareservice.entity.out;

import lombok.Data;

@Data
public class OutRegistration {
    private int user_id;
    private String user_name;
    private String user_IDCard;
    private String date;
    private String time;
    private int num;
    private String confirm;
    private String skip;
    private int count;
}
