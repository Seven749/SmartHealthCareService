package com.sevenrecy.smarthealthcareservice.service;

import com.sevenrecy.smarthealthcareservice.entity.Doctor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    Doctor selectDoctorById(@Param("doc_id") String doc_id);

    /**
     * 通过科室id获取医生列表
     * @param dept_id 科室id
     * @return
     */
    List<Doctor> selectDoctorByDept(@Param("dept_id") int dept_id);

    /**
     * 通过科室名称，医生姓名查询医生信息
     * @param dept_id 科室id
     * @param doc_name 医生姓名
     * @return
     */
    Doctor selectDoctorByDeptAndDoc(@Param("dept_id") int dept_id, @Param("doc_name") String doc_name);
}
