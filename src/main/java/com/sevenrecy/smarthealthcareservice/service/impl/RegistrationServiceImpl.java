package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.RegistrationDao;
import com.sevenrecy.smarthealthcareservice.entity.Registration;
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
}
