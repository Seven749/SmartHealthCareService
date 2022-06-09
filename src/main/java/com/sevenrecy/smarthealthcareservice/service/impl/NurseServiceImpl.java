package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.NurseDao;
import com.sevenrecy.smarthealthcareservice.entity.Nurse;
import com.sevenrecy.smarthealthcareservice.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NurseServiceImpl implements NurseService {
    @Autowired
    NurseDao nurseDao;

    @Override
    public Nurse selectNurse(String nur_id, String pwd) {
        return nurseDao.selectNurse(nur_id, pwd);
    }
}
