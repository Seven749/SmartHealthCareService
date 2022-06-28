package com.sevenrecy.smarthealthcareservice.entity;

import lombok.Data;

import java.util.List;
@Data
public class Histories {
    private String histories_id;
    private int user_id;
    private String user_name;
    private int count;
    private String doc_id;
    private String doc_name;
    private int dept_id;
    private String dept_name;
    private String complaints;  //  主诉
//    private String allergies;   // 过敏史
    private String present; // 现状史
    private String past;    // 既往史
    private String physical_exam;   // 体格检查
    private String assistant_exam;  // 辅助检查
    private String diagnosis;   // 诊断
//    private String prescription_id; // 处方编号
//    private String check_item_id;   // 检查单编号
    private String advice;  // 建议
    private String create_time;
    private int prescription_count=0;
    private int check_item_count=0;

    private List<Prescription> prescriptionList = null;
    private List<CheckItem> checkItemList = null;
}
