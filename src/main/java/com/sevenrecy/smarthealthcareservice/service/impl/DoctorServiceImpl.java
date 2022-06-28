package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.DoctorDao;
import com.sevenrecy.smarthealthcareservice.entity.Doctor;
import com.sevenrecy.smarthealthcareservice.entity.out.OutDoctor;
import com.sevenrecy.smarthealthcareservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorDao doctorDao;

    @Override
    public OutDoctor selectDoctor(String doc_id, String pwd) {
        return doctorDao.selectDoctor(doc_id, pwd);
    }

    @Override
    public OutDoctor selectDoctorById(String doc_id) {
        return doctorDao.selectDoctorById(doc_id);
    }

    @Override
    public List<OutDoctor> selectDoctorByDept(int dept_id) {
        return doctorDao.selectDoctorByDept(dept_id);
    }

    @Override
    public OutDoctor selectDoctorByDeptAndDoc(int dept_id, String doc_name) {
        return doctorDao.selectDoctorByDeptAndDoc(dept_id, doc_name);
    }
}
