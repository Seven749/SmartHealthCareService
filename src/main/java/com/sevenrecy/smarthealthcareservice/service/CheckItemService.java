package com.sevenrecy.smarthealthcareservice.service;

import com.sevenrecy.smarthealthcareservice.entity.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckItemService {

    /**
     * 新增检查单
     * @param checkItem 检查项目对象
     * @return
     */
    int insertCheckItem(CheckItem checkItem);

    /**
     * 获取检查单信息
     * @param check_item_id
     * @return
     */
    CheckItem selectCheckItemById(String check_item_id);

    /**
     * 获取检查单列表
     * @return
     */
    List<CheckItem> selectCheckItemList(String histories_id);

    /**
     * 获取检查单列表
     * @return
     */
    List<CheckItem> selectCheckItemListByUserId(int user_id);

    /**
     * 查询是否已有检查单
     * @param histories_id 病历id
     * @param item_id 项目id
     * @return
     */
    CheckItem selectCheckItem(@Param("histories_id") String histories_id, @Param("item_id") String item_id);

}
