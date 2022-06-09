package com.sevenrecy.smarthealthcareservice.dao;

import com.sevenrecy.smarthealthcareservice.entity.Nurse;
import org.apache.ibatis.annotations.Param;

public interface NurseDao {

    /**
     * 护士登录时通过此查询是否正确
     * @param nur_id 护士id
     * @param pwd 密码
     * @return
     */
    Nurse selectNurse(@Param("nur_id") String nur_id, @Param("pwd") String pwd);

}
