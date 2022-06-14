package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.BillDao;
import com.sevenrecy.smarthealthcareservice.entity.DrugBill;
import com.sevenrecy.smarthealthcareservice.entity.ItemBill;
import com.sevenrecy.smarthealthcareservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillDao billDao;

    @Override
    public List<ItemBill> selectItemBillListByCount(int user_id, int count) {
        return billDao.selectItemBillListByCount(user_id, count);
    }

    @Override
    public List<DrugBill> selectDrugBillListByCount(int user_id, int count) {
        return billDao.selectDrugBillListByCount(user_id, count);
    }

    @Override
    public List<ItemBill> selectItemBillList(int user_id) {
        return billDao.selectItemBillList(user_id);
    }

    @Override
    public List<DrugBill> selectDrugBillList(int user_id) {
        return billDao.selectDrugBillList(user_id);
    }

    @Override
    public int createItemBill(ItemBill itemBill) {
        return billDao.createItemBill(itemBill);
    }

    @Override
    public int createDrugBill(DrugBill drugBill) {
        return billDao.createDrugBill(drugBill);
    }

    @Override
    public ItemBill selectItemBillByCheId(String check_item_id) {
        return billDao.selectItemBillByCheId(check_item_id);
    }

    @Override
    public DrugBill selectDrugBillByPreId(String prescription_id) {
        return billDao.selectDrugBillByPreId(prescription_id);
    }
}
