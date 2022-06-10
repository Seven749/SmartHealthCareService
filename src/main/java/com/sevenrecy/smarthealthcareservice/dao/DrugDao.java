package com.sevenrecy.smarthealthcareservice.dao;

import com.sevenrecy.smarthealthcareservice.entity.Drug;

import java.util.List;

public interface DrugDao {

    /**
     * 创建新药品
     * @param drug 药品对象
     * @return
     */
    int insertDrug(Drug drug);

    /**
     * 通过id获取药品信息
     * @param drug_id
     * @return
     */
    Drug selectDrugById(String drug_id);

    /**
     * 获取所有药品的列表
     * @return
     */
    List<Drug> selectDrugList();
}
