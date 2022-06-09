package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

@Data
public class Doctor {
    private String doc_id;
    private String name;
    private String pwd;
    private String avatar;
    private int dept_id;
    private String create_time;
    private double price;
}
