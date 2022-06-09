package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

@Data
public class Drug {
    private String drug_id;
    private String name;
    private String unit;    // 计量单位
    private String capacity;    // 规格
    private double price;
}
