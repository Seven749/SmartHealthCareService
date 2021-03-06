package com.sevenrecy.smarthealthcareservice.dao;

import com.sevenrecy.smarthealthcareservice.entity.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemDao {

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
    List<Item> selectItemList(@Param("dept_id") int dept_id);
}
