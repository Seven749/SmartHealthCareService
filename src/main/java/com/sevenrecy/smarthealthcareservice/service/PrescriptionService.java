package com.sevenrecy.smarthealthcareservice.service;

import com.sevenrecy.smarthealthcareservice.entity.Prescription;

import java.util.List;

public interface PrescriptionService {

    /**
     * 创建处方单
     * @param prescription 处方
     * @return
     */
    int insertPrescription(Prescription prescription);

    /**
     * 获取对应的处方详情
     * @param prescription_id 处方id
     * @return
     */
    Prescription selectPrescription(String prescription_id);

    /**
     * 获取对应病历的处方列表
     * @param histories_id 病历id
     * @return
     */
    List<Prescription> selectPrescriptionList(String histories_id);
}
