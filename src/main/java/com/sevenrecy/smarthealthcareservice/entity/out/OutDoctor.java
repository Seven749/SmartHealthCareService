package com.sevenrecy.smarthealthcareservice.entity.out;

import lombok.Data;

@Data
public class OutDoctor {
    private String doc_id;
    private String name;
    private String avatar;
    private int dept_id;
    private String dept_name;
    private double price;
}
