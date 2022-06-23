package com.sevenrecy.smarthealthcareservice.service;

import com.sevenrecy.smarthealthcareservice.entity.Registration;
import com.sevenrecy.smarthealthcareservice.entity.out.OutRegistration;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegistrationService {
    /**
     * 获取用户的历史挂号记录
     * @param user_id 用户id
     * @return
     */
    List<Registration> selectRegistrationList(int user_id);

    /**
     * 查询是否已被预约
     * @param doc_id 医生id
     * @param time 预约时间
     * @return
     */
    Registration selectRegistration(@Param("doc_id") String doc_id, @Param("date") String date, @Param("time") String time);

    /**
     * 挂号时插入一条记录
     * @param registration 挂号单
     * @return
     */
    int insertRegistration(Registration registration);

    /**
     * 获取医生某一天已预约到几号
     * @param doc_id 医生id
     * @param time 预约日期
     * @return
     */
    int selectNumByTime(@Param("doc_id") String doc_id, @Param("date") String date, @Param("time") String time);

    /**
     * 获取医生今日已经被预约的信息
     * @param doc_id
     * @param date
     * @return
     */
    List<OutRegistration> selectRegistrationOfDoctor(@Param("doc_id") String doc_id, @Param("date") String date);

    /**
     * 跳号
     * @param doc_id 医生id
     * @param date 日期
     * @param num 挂号号
     * @return
     */
    int updateSkipNum(@Param("doc_id") String doc_id, @Param("date") String date, @Param("num") int num);

    /**
     * 确认号
     * @param doc_id 医生id
     * @param date 日期
     * @param num 挂号号
     * @return
     */
    int updateConfirmNum(@Param("doc_id") String doc_id, @Param("date") String date, @Param("num") int num);
}
