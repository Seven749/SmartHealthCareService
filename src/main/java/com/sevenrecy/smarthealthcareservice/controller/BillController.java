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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@CrossOrigin(origins = "http://localhost:63342",maxAge = 3600)
public class BillController {
    @Autowired
    BillService billService;

    @RequestMapping("/get_bill_list")
    public Result getBillList(@RequestParam("user_id") int user_id) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        List<ItemBill> itemBillList = billService.selectItemBillList(user_id);
        List<ItemBill> itemBillListYes = new ArrayList<>();
        List<ItemBill> itemBillListNo = new ArrayList<>();
        if (itemBillList==null||itemBillList.size()==0) {
            itemBillList = null;
        } else {
            for (ItemBill itemBill: itemBillList) {
                if (itemBill.getIsPay().equals("Yes")) {
                    itemBillListYes.add(itemBill);
                } else {
                    itemBillListNo.add(itemBill);
                }
            }
        }
        List<DrugBill> drugBillList = billService.selectDrugBillList(user_id);
        List<DrugBill> drugBillListYes = new ArrayList<>();
        List<DrugBill> drugBillListNo = new ArrayList<>();
        if (drugBillList==null||drugBillList.size()==0) {
            drugBillList = null;
        } else {
            for (DrugBill drugBill: drugBillList) {
                if (drugBill.getIsPay().equals("Yes")) {
                    drugBillListYes.add(drugBill);
                } else {
                    drugBillListNo.add(drugBill);
                }
            }
        }
        return Result.ok().data("itemBillListYes", itemBillListYes).data("itemBillListNo", itemBillListNo)
                .data("drugBillListYes", drugBillListYes).data("drugBillListNo", drugBillListNo);
    }

    @RequestMapping("/get_bill_list_by_count")
    public Result getBillListByCount(@RequestParam("user_id") int user_id,
                              @RequestParam("count") int count) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        List<ItemBill> itemBillList = billService.selectItemBillListByCount(user_id, count);
        List<ItemBill> itemBillListYes = new ArrayList<>();
        List<ItemBill> itemBillListNo = new ArrayList<>();
        if (itemBillList==null||itemBillList.size()==0) {
            itemBillList = null;
        } else {
            for (ItemBill itemBill: itemBillList) {
                if (itemBill.getIsPay().equals("Yes")) {
                    itemBillListYes.add(itemBill);
                } else {
                    itemBillListNo.add(itemBill);
                }
            }
        }
        List<DrugBill> drugBillList = billService.selectDrugBillListByCount(user_id, count);
        List<DrugBill> drugBillListYes = new ArrayList<>();
        List<DrugBill> drugBillListNo = new ArrayList<>();
        if (drugBillList==null||drugBillList.size()==0) {
            drugBillList = null;
        } else {
            for (DrugBill drugBill: drugBillList) {
                if (drugBill.getIsPay().equals("Yes")) {
                    drugBillListYes.add(drugBill);
                } else {
                    drugBillListNo.add(drugBill);
                }
            }
        }
        return Result.ok().data("itemBillListYes", itemBillListYes).data("itemBillListNo", itemBillListNo)
                .data("drugBillListYes", drugBillListYes).data("drugBillListNo", drugBillListNo);
    }

}
