package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

@Data
public class User {
    private int user_id;
    private String name;
    private String pwd;
    private String IDCard;
    private String sex;
    private String birthday;
    private String tel;
    private int count = 0;
    private String create_time;
    private double balance = 0.0;
    private int age;
}
