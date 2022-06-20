package com.sevenrecy.smarthealthcareservice.dao;

import com.sevenrecy.smarthealthcareservice.entity.DrugBill;
import com.sevenrecy.smarthealthcareservice.entity.ItemBill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillDao {
    /**
     * 获取最近某次就诊的项目账单列表
     * @param user_id 用户id
     * @param count 就诊次数
     * @return
     */
    List<ItemBill> selectItemBillListByCount(@Param("user_id") int user_id, @Param("count") int count);

    /**
     * 获取最近某次就诊的药品账单列表
     * @param user_id 用户id
     * @param count 就诊次数
     * @return
     */
    List<DrugBill> selectDrugBillListByCount(@Param("user_id") int user_id, @Param("count") int count);

    /**
     * 获取最近某次就诊的项目账单列表
     * @param user_id 用户id
     * @return
     */
    List<ItemBill> selectItemBillList(@Param("user_id") int user_id);

    /**
     * 获取最近某次就诊的药品账单列表
     * @param user_id 用户id
     * @return
     */
    List<DrugBill> selectDrugBillList(@Param("user_id") int user_id);
    /**
     * 创建项目账单
     * @param itemBill 项目账单对象
     * @return
     */
    int createItemBill(ItemBill itemBill);

    /**
     * 创建药品账单
     * @param drugBill 药品账单对象
     * @return
     */
    int createDrugBill(DrugBill drugBill);


    /**
     * 通过检查单id查询账单
     * @param check_item_id 检查单id
     * @return
     */
    ItemBill selectItemBillByCheId(@Param("check_item_id") String check_item_id);

    /**
     * 通过处方id查询账单
     * @param prescription_id 处方id
     * @return
     */
    DrugBill selectDrugBillByPreId(@Param("prescription_id") String prescription_id);

    /**
     * 支付药物账单
     * @param drug_bill_id 药物账单id
     * @param user_id 用户id
     * @return
     */
    int updateDrugBillPay(@Param("drug_bill_id") String drug_bill_id, @Param("user_id") int user_id);

    /**
     * 支付项目账单
     * @param item_bill_id 项目账单id
     * @param user_id 用户id
     * @return
     */
    int updateItemBillPay(@Param("item_bill_id") String item_bill_id, @Param("user_id") int user_id);
}
