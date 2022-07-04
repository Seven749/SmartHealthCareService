package com.sevenrecy.smarthealthcareservice.service;

import com.sevenrecy.smarthealthcareservice.entity.HospitalCase;

import java.util.List;

public interface HospitalCaseService {
    /**
     * 通过user_id获取住院病案的列表
     * @param user_id
     * @return
     */
    List<HospitalCase> selectHospitalCaseList(int user_id);
}
