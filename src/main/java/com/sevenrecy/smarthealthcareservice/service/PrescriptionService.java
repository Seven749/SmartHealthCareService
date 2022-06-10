package com.sevenrecy.smarthealthcareservice.service;

import com.sevenrecy.smarthealthcareservice.entity.Prescription;
import org.apache.ibatis.annotations.Param;

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
    Prescription selectPrescriptionById(String prescription_id);

    /**
     * 获取对应病历的处方列表
     * @param histories_id 病历id
     * @return
     */
    List<Prescription> selectPrescriptionList(String histories_id);

    /**
     * 查询是否已有处方
     * @param histories_id 病历id
     * @param drug_id 药品id
     * @return
     */
    Prescription selectPrescription(@Param("histories_id") String histories_id, @Param("drug_id") String drug_id);
}
