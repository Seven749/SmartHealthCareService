package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.HospitalCaseDao;
import com.sevenrecy.smarthealthcareservice.entity.HospitalCase;
import com.sevenrecy.smarthealthcareservice.service.HospitalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalCaseServiceImpl implements HospitalCaseService {
    @Autowired
    HospitalCaseDao hospitalCaseDao;

    @Override
    public List<HospitalCase> selectHospitalCaseList(int user_id) {
        return hospitalCaseDao.selectHospitalCaseList(user_id);
    }
}
