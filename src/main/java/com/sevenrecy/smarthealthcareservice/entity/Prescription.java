package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

@Data
public class Prescription {
    private String prescription_id;
    private String histories_id;
    private String drug_id;
    private String drug_name;
    private int drug_count;
    private String usage;
}
