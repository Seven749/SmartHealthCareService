package com.sevenrecy.smarthealthcareservice.service;

import com.sevenrecy.smarthealthcareservice.entity.Nurse;
import org.apache.ibatis.annotations.Param;

public interface NurseService {
    /**
     * 护士登录时通过此查询是否正确
     * @param nur_id 护士id
     * @param pwd 密码
     * @return
     */
    Nurse selectNurse(String nur_id, String pwd);

}
