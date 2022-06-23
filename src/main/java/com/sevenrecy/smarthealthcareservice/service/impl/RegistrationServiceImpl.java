package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.RegistrationDao;
import com.sevenrecy.smarthealthcareservice.entity.Registration;
import com.sevenrecy.smarthealthcareservice.entity.out.OutRegistration;
import com.sevenrecy.smarthealthcareservice.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    RegistrationDao registrationDao;

    @Override
    public List<Registration> selectRegistrationList(int user_id) {
        return registrationDao.selectRegistrationList(user_id);
    }

    @Override
    public Registration selectRegistration(String doc_id, String date, String time) {
        return registrationDao.selectRegistration(doc_id, date, time);
    }

    @Override
    public int insertRegistration(Registration registration) {
        return registrationDao.insertRegistration(registration);
    }

    @Override
    public int selectNumByTime(String doc_id, String date, String time) {
        return registrationDao.selectNumByTime(doc_id, date, time);
    }

    @Override
    public List<OutRegistration> selectRegistrationOfDoctor(String doc_id, String date) {
        return registrationDao.selectRegistrationOfDoctor(doc_id, date);
    }

    @Override
    public int updateSkipNum(String doc_id, String date, int num) {
        return registrationDao.updateSkipNum(doc_id, date, num);
    }

    @Override
    public int updateConfirmNum(String doc_id, String date, int num) {
        return registrationDao.updateConfirmNum(doc_id, date, num);
    }
}
