package com.sevenrecy.smarthealthcareservice.entity.input;

import lombok.Data;

@Data
public class InPre {
    private String histories_id;
    private String drug_id;
    private int drug_count;
    private String usages;
}
