package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.HistoriesDao;
import com.sevenrecy.smarthealthcareservice.entity.Histories;
import com.sevenrecy.smarthealthcareservice.service.HistoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriesServiceImpl implements HistoriesService {
    @Autowired
    HistoriesDao historiesDao;

    @Override
    public int insertHistories(Histories histories) {
        return historiesDao.insertHistories(histories);
    }

    @Override
    public List<Histories> selectHistoriesList(int user_id) {
        return historiesDao.selectHistoriesList(user_id);
    }

    @Override
    public Histories selectHistoriesByUserId(int user_id) {
        return historiesDao.selectHistoriesByUserId(user_id);
    }

    @Override
    public Histories selectHistoriesById(String histories_id) {
        return historiesDao.selectHistoriesById(histories_id);
    }
}
