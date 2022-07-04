package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

@Data
public class HospitalCase {
    private String case_id;
    private int user_id;
    private String user_name;
    private int user_age;
    private String doc_id;
    private String doc_name;
    private int dept_id;
    private String dept_name;
    private String create_time;
    private String end_time;
}
