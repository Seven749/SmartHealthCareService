package com.sevenrecy.smarthealthcareservice.dao;

import com.sevenrecy.smarthealthcareservice.entity.Doctor;
import com.sevenrecy.smarthealthcareservice.entity.out.OutDoctor;
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
    OutDoctor selectDoctor(@Param("doc_id") String doc_id, @Param("pwd") String pwd);

    /**
     * 可以通过医生的id获取医生的基本信息
     * @param doc_id 医生id
     * @return
     */
    OutDoctor selectDoctorById(@Param("doc_id") String doc_id);

    /**
     * 通过科室id获取医生列表
     * @param dept_id 科室id
     * @return
     */
    List<OutDoctor> selectDoctorByDept(@Param("dept_id") int dept_id);

    /**
     * 通过科室名称，医生姓名查询医生信息
     * @param dept_id 科室id
     * @param doc_name 医生姓名
     * @return
     */
    OutDoctor selectDoctorByDeptAndDoc(@Param("dept_id") int dept_id, @Param("doc_name") String doc_name);
}
