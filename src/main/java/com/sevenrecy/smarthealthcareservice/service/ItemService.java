package com.sevenrecy.smarthealthcareservice.service;

import com.sevenrecy.smarthealthcareservice.entity.Item;

import java.util.List;

public interface ItemService {

    /**
     * 新增检查项目
     * @param item 项目对象
     * @return
     */
    int insertItem(Item item);

    /**
     * 获取项目信息
     * @param item_id 项目id
     * @return
     */
    Item selectItemById(String item_id);

    /**
     * 获取所有项目的信息
     * @return
     */
    List<Item> selectItemList(int dept_id);
}
