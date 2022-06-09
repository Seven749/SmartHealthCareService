package com.sevenrecy.smarthealthcareservice.service;

import com.sevenrecy.smarthealthcareservice.entity.Doctor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface DoctorService {
    /**
     * 医生登录时通过此查询是否正确
     * @param doc_id 医生id
     * @param pwd 密码
     * @return
     */
    Doctor selectDoctor(String doc_id, String pwd);

    /**
     * 可以通过医生的id获取医生的基本信息
     * @param doc_id
     * @return
     */
    Doctor selectDoctorById(@RequestParam("doc_id") String doc_id);
}
