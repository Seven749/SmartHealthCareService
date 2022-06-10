package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.CheckItemDao;
import com.sevenrecy.smarthealthcareservice.entity.CheckItem;
import com.sevenrecy.smarthealthcareservice.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    CheckItemDao checkItemDao;

    @Override
    public int insertCheckItem(CheckItem checkItem) {
        return checkItemDao.insertCheckItem(checkItem);
    }

    @Override
    public CheckItem selectCheckItemById(String check_item_id) {
        return checkItemDao.selectCheckItemById(check_item_id);
    }

    @Override
    public List<CheckItem> selectCheckItemList() {
        return checkItemDao.selectCheckItemList();
    }

    @Override
    public CheckItem selectCheckItem(String histories_id, String item_id) {
        return checkItemDao.selectCheckItem(histories_id, item_id);
    }
}
