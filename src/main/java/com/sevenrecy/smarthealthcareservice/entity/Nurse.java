package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

@Data
public class Nurse {
    private String nur_id;
    private String name;
    private String pwd;
    private String avatar;
    private int dept_id;
    private String create_time;
}
