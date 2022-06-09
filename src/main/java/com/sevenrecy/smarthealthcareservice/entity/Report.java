package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

@Data
public class Report {
    private String report_id;
    private String check_item_id;
    private String item_id;
    private String item_name;
    private int user_id;
    private String user_name;
    private String doc_id;
    private String doc_name;
    private int dept_id;
    private String dept_name;
    private String symptom;
    private String img1;
    private String img2;
    private String diagnosis;
    private String create_time;
}
