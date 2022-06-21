package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.dao.BillDao;
import com.sevenrecy.smarthealthcareservice.entity.User;
import com.sevenrecy.smarthealthcareservice.entity.input.InPayBill;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.DATABASE_ERROR;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@CrossOrigin(origins = "http://localhost:63342",maxAge = 3600)
public class PayController {
    @Autowired
    UserService userService;
    @Autowired
    BillDao billDao;

    /**
     * 住院预交费
     * @param user_id
     * @param balance
     * @return
     */
    @RequestMapping("/save_balance")
    public Result saveBalance(@RequestParam("user_id") int user_id,
                              @RequestParam("balance") double balance) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        int i = userService.updateBalance(user_id, balance);
        System.out.println(i);
        if (i==1) {
            User user = userService.selectSysUserById(user_id);
            return Result.ok().message("预交费成功").data("user", user);
        }
        return Result.setResult(DATABASE_ERROR);
    }

    @RequestMapping("/pay_bill")
    public Result payBill(@RequestBody List<InPayBill> inPayBillList) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        Map<String, Object> map = new HashMap<>();
        int size = inPayBillList.size();
        for (int i = 0; i < size; i++) {
            InPayBill inPayBill = inPayBillList.get(i);
            int j = 0;
            if (inPayBill.getType().equals("drug")) {
                j = billDao.updateDrugBillPay(inPayBill.getBill_id(), inPayBill.getUser_id());
            } else {
                j = billDao.updateItemBillPay(inPayBill.getBill_id(), inPayBill.getUser_id());
            }
            if (j==1) {
                map.put(inPayBill.getBill_id(), "isPay:Yes");
            } else {
                map.put(inPayBill.getBill_id(), "isPay:No");
            }
        }
        return Result.ok().data(map);
    }
}
