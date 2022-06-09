package com.sevenrecy.smarthealthcareservice.dao;

import com.sevenrecy.smarthealthcareservice.entity.Registration;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegistrationDao {
    /**
     * 获取用户的历史挂号记录
     * @param user_id 用户id
     * @return
     */
    List<Registration> selectRegistrationList(int user_id);

    /**
     * 查询时间是否已有挂号
     * @param doc_id 医生id
     * @param date 日期
     * @param time 时间
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
    int selectNumByTime(@Param("doc_id") String doc_id, @Param("time") String time);
}
