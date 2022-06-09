package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

@Data
public class CheckItem {
    private String Check_item_id;
    private String histories_id;
    private String item_id;
    private String item_name;
    private int item_count;
}
