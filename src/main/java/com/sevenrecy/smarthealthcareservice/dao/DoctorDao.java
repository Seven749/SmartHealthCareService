package com.sevenrecy.smarthealthcareservice.dao;

import com.sevenrecy.smarthealthcareservice.entity.Doctor;
import org.apache.ibatis.annotations.Param;

public interface DoctorDao {
    /**
     * 医生登录时通过此查询是否正确
     * @param doc_id 医生id
     * @param pwd 密码
     * @return
     */
    Doctor selectDoctor(@Param("doc_id") String doc_id, @Param("pwd") String pwd);
}
