package com.sevenrecy.smarthealthcareservice.dao;

import com.sevenrecy.smarthealthcareservice.entity.HospitalCase;

import java.util.List;

public interface HospitalCaseDao {

    /**
     * 通过user_id获取住院病案的列表
     * @param user_id
     * @return
     */
    List<HospitalCase> selectHospitalCaseList(int user_id);
}
