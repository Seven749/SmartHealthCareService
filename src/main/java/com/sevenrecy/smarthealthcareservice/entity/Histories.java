package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

import java.util.List;
@Data
public class Histories {
    private String histories_id;
    private int user_id;
    private String user_name;
    private String doc_id;
    private String doc_name;
    private String dept_name;
    private String allergies;   // 过敏史
    private String present; // 现状史
    private String past;    // 既往史
    private String physical_exam;   // 体格检查
    private String assistant_exam;  // 辅助检查
    private String diagnosis;   // 诊断
//    private String prescription_id; // 处方编号
//    private String check_item_id;   // 检查单编号
    private String advice;  // 建议

    private List<Prescription> prescriptionList;
    private List<CheckItem> checkItemList;
}
