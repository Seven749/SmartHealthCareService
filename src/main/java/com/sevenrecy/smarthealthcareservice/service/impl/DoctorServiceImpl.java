package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.DoctorDao;
import com.sevenrecy.smarthealthcareservice.entity.Doctor;
import com.sevenrecy.smarthealthcareservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorDao doctorDao;

    @Override
    public Doctor selectDoctor(String doc_id, String pwd) {
        return doctorDao.selectDoctor(doc_id, pwd);
    }
}
