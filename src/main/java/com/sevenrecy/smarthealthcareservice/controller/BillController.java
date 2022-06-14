package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.DrugBill;
import com.sevenrecy.smarthealthcareservice.entity.ItemBill;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
public class BillController {
    @Autowired
    BillService billService;

    @RequestMapping("/get_bill_list")
    public Result getBillList(@RequestParam("user_id") int user_id) {
        List<ItemBill> itemBillList = billService.selectItemBillList(user_id);
        if (itemBillList==null||itemBillList.size()==0) {
            itemBillList = null;
        }
        List<DrugBill> drugBillList = billService.selectDrugBillList(user_id);
        if (drugBillList==null||drugBillList.size()==0) {
            drugBillList = null;
        }
        return Result.ok().data("itemBillList", itemBillList).data("drugBillList", drugBillList);
    }

    @RequestMapping("/get_bill_list_by_count")
    public Result getBillListByCount(@RequestParam("user_id") int user_id,
                              @RequestParam("count") int count) {
        List<ItemBill> itemBillList = billService.selectItemBillListByCount(user_id, count);
        if (itemBillList==null||itemBillList.size()==0) {
            itemBillList = null;
        }
        List<DrugBill> drugBillList = billService.selectDrugBillListByCount(user_id, count);
        if (drugBillList==null||drugBillList.size()==0) {
            drugBillList = null;
        }
        return Result.ok().data("itemBillList", itemBillList).data("drugBillList", drugBillList);
    }

}
