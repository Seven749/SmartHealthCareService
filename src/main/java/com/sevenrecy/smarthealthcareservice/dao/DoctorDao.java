package com.sevenrecy.smarthealthcareservice.dao;

import com.sevenrecy.smarthealthcareservice.entity.Doctor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.Doc;
import java.util.List;

public interface DoctorDao {
    /**
     * 医生登录时通过此查询是否正确
     * @param doc_id 医生id
     * @param pwd 密码
     * @return
     */
    Doctor selectDoctor(@Param("doc_id") String doc_id, @Param("pwd") String pwd);

    /**
     * 可以通过医生的id获取医生的基本信息
     * @param doc_id 医生id
     * @return
     */
    Doctor selectDoctorById(@RequestParam("doc_id") String doc_id);

    /**
     * 通过科室id获取医生列表
     * @param dept_id 科室id
     * @return
     */
    List<Doctor> selectDoctorByDept(@RequestParam("dept_id") int dept_id);
}
