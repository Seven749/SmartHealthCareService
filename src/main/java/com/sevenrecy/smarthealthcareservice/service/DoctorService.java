package com.sevenrecy.smarthealthcareservice.service;

import com.sevenrecy.smarthealthcareservice.entity.Doctor;
import org.apache.ibatis.annotations.Param;

public interface DoctorService {
    /**
     * 医生登录时通过此查询是否正确
     * @param doc_id 医生id
     * @param pwd 密码
     * @return
     */
    Doctor selectDoctor(String doc_id, String pwd);
}
