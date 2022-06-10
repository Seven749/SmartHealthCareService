package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.DrugDao;
import com.sevenrecy.smarthealthcareservice.entity.Drug;
import com.sevenrecy.smarthealthcareservice.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    DrugDao drugDao;

    @Override
    public int insertDrug(Drug drug) {
        return drugDao.insertDrug(drug);
    }

    @Override
    public Drug selectDrugById(String drug_id) {
        return drugDao.selectDrugById(drug_id);
    }

    @Override
    public List<Drug> selectDrugList() {
        return drugDao.selectDrugList();
    }
}
