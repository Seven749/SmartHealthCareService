package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.PrescriptionDao;
import com.sevenrecy.smarthealthcareservice.entity.Prescription;
import com.sevenrecy.smarthealthcareservice.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    @Autowired
    PrescriptionDao prescriptionDao;

    @Override
    public int insertPrescription(Prescription prescription) {
        return prescriptionDao.insertPrescription(prescription);
    }

    @Override
    public Prescription selectPrescriptionById(String prescription_id) {
        return prescriptionDao.selectPrescriptionById(prescription_id);
    }

    @Override
    public List<Prescription> selectPrescriptionList(String histories_id) {
        return prescriptionDao.selectPrescriptionList(histories_id);
    }

    @Override
    public Prescription selectPrescription(String histories_id, String drug_id) {
        return prescriptionDao.selectPrescription(histories_id, drug_id);
    }
}
