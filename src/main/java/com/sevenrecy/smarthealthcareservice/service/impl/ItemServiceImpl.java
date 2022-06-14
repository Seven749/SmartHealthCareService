package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.ItemDao;
import com.sevenrecy.smarthealthcareservice.entity.Item;
import com.sevenrecy.smarthealthcareservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemDao itemDao;

    @Override
    public int insertItem(Item item) {
        return itemDao.insertItem(item);
    }

    @Override
    public Item selectItemById(String item_id) {
        return itemDao.selectItemById(item_id);
    }

    @Override
    public List<Item> selectItemList(int dept_id) {
        return itemDao.selectItemList(dept_id);
    }
}
