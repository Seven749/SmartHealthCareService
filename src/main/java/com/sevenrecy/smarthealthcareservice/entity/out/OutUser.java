package com.sevenrecy.smarthealthcareservice.entity.out;

import lombok.Data;

@Data
public class OutUser {
    private int user_id;
    private String name;
    private String IDCard;
    private String sex;
    private String birthday;
    private String tel;
    private int count = 0;
    private double balance = 0.0;
    private int age;
}
