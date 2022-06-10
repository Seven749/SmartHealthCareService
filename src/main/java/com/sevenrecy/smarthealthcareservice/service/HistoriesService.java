package com.sevenrecy.smarthealthcareservice.service;

import com.sevenrecy.smarthealthcareservice.entity.Histories;

import java.util.List;

public interface HistoriesService {
    /**
     * 创建病历是
     * @param histories 病历对象
     * @return
     */
    int insertHistories(Histories histories);

    /**
     * 获取用户最近的历史病历
     * @param user_id
     * @return
     */
    List<Histories> selectHistoriesList(int user_id);

    /**
     * 获取用户最近一次历史病历
     * @param user_id
     * @return
     */
    Histories selectHistoriesByUserId(int user_id);

    /**
     * 查询是否存在病历号
     * @param histories_id
     * @return
     */
    Histories selectHistoriesById(String histories_id);
}
