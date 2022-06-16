package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.CheckItem;
import com.sevenrecy.smarthealthcareservice.entity.Histories;
import com.sevenrecy.smarthealthcareservice.entity.Item;
import com.sevenrecy.smarthealthcareservice.entity.ItemBill;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.BillService;
import com.sevenrecy.smarthealthcareservice.service.CheckItemService;
import com.sevenrecy.smarthealthcareservice.service.HistoriesService;
import com.sevenrecy.smarthealthcareservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
public class CheckItemController {
    @Autowired
    CheckItemService checkItemService;
    @Autowired
    HistoriesService historiesService;
    @Autowired
    ItemService itemService;
    @Autowired
    BillService billService;

    @RequestMapping("/create_check_item_list")
    public Result createCheckItemList(@RequestParam("histories_id") String histories_id,
                                      @RequestParam("item_id_list") List<String> item_idList,
                                      @RequestParam("item_count_list") List<Short> item_countList) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        int size = item_idList.size();
        List<CheckItem> checkItemList = new ArrayList<>();
        List<ItemBill> itemBillList = new ArrayList<>();
        for (int k = 0; k < size; k++) {
            Histories histories = historiesService.selectHistoriesById(histories_id);
            String item_id = item_idList.get(k);
            int item_count = item_countList.get(k);
            if (histories==null) {
                return Result.setResult(HISTORIES_NULL_ERROR);
            }
            Item item = itemService.selectItemById(item_id);
            if (item==null) {
                return Result.setResult(ITEM_NULL_ERROR);
            }
            CheckItem checkItem = checkItemService.selectCheckItem(histories_id, item_id);
            if (checkItem!=null) {
                checkItemList.add(checkItem);
                ItemBill itemBill = billService.selectItemBillByCheId(checkItem.getCheck_item_id());
                if (itemBill==null) {
                    itemBill = new ItemBill();
                    itemBill.setItem_bill_id("cbi"+checkItem.getCheck_item_id().substring(5));
                    itemBill.setCheck_item_id(checkItem.getCheck_item_id());
                    itemBill.setHistories_id(histories_id);
                    itemBill.setUser_id(histories.getUser_id());
                    itemBill.setUser_name(histories.getUser_name());
                    itemBill.setItem_id(item.getItem_id());
                    itemBill.setItem_name(item.getName());
                    itemBill.setPrice(item.getPrice());
                    itemBill.setItem_count(checkItem.getItem_count());
                    itemBill.setTotal(itemBill.getPrice()*itemBill.getItem_count());
                    itemBill.setCount(histories.getCount());
                    itemBill.setIsPay("No");
                    itemBill.setCreate_time(checkItem.getCreate_time());
                    int j = billService.createItemBill(itemBill);
                    if (j>0) {
                        itemBill = billService.selectItemBillByCheId(checkItem.getCheck_item_id());
                        if (itemBill!=null) {
                            itemBillList.add(itemBill);
                        } else {
                            itemBillList.add(null);
                        }
                    } else {
                        itemBillList.add(null);
                    }
                } else {
                    itemBillList.add(itemBill);
                }
            } else {
                checkItem = new CheckItem();
                checkItem.setHistories_id(histories.getHistories_id());
                checkItem.setItem_id(item.getItem_id());
                checkItem.setItem_name(item.getName());
                checkItem.setItem_count(item_count);
                SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                String date = fmt1.format(new Date());
                checkItem.setCreate_time(date);
                checkItem.setCheck_item_id("check"+histories_id.substring(3,10)+date.substring(14,16)+date.substring(17));
                int i = checkItemService.insertCheckItem(checkItem);
                if (i>0) {
                    checkItem = checkItemService.selectCheckItemById(checkItem.getCheck_item_id());
                    if (checkItem!=null) {
                        checkItemList.add(checkItem);
                        ItemBill itemBill = billService.selectItemBillByCheId(checkItem.getCheck_item_id());
                        if (itemBill==null) {
                            itemBill = new ItemBill();
                            itemBill.setItem_bill_id("cbi"+checkItem.getCheck_item_id().substring(5));
                            itemBill.setCheck_item_id(checkItem.getCheck_item_id());
                            itemBill.setHistories_id(histories_id);
                            itemBill.setUser_id(histories.getUser_id());
                            itemBill.setUser_name(histories.getUser_name());
                            itemBill.setItem_id(item.getItem_id());
                            itemBill.setItem_name(item.getName());
                            itemBill.setPrice(item.getPrice());
                            itemBill.setItem_count(checkItem.getItem_count());
                            itemBill.setTotal(itemBill.getPrice()*itemBill.getItem_count());
                            itemBill.setCount(histories.getCount());
                            itemBill.setIsPay("No");
                            itemBill.setCreate_time(checkItem.getCreate_time());
                            int j = billService.createItemBill(itemBill);
                            if (j>0) {
                                itemBill = billService.selectItemBillByCheId(checkItem.getCheck_item_id());
                                if (itemBill!=null) {
                                    itemBillList.add(itemBill);
                                } else {
                                    itemBillList.add(null);
                                }
                            } else {
                                itemBillList.add(null);
                            }
                        } else {
                            itemBillList.add(itemBill);
                        }
                    } else {
                        checkItemList.add(null);
                    }
                } else {
                    checkItemList.add(null);
                }
            }
        }
        return Result.ok().data("checkItemList", checkItemList).data("itemBillList", itemBillList);
    }

    /**
     * 新增检查记录 (已弃用)
     * @param histories_id 病历id
     * @param item_id 项目id
     * @param item_count 项目数目
     * @return
     */
    @RequestMapping("/create_check_item")
    public Result creatCheckItem(@RequestParam("histories_id") String histories_id,
                                 @RequestParam("item_id") String item_id,
                                 @RequestParam("item_count") int item_count) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        return Result.ok().message("已弃用");
//        Histories histories = historiesService.selectHistoriesById(histories_id);
//        if (histories==null) {
//            return Result.setResult(HISTORIES_NULL_ERROR);
//        }
//        Item item = itemService.selectItemById(item_id);
//        if (item==null) {
//            return Result.setResult(ITEM_NULL_ERROR);
//        }
//        CheckItem checkItem = checkItemService.selectCheckItem(histories_id, item_id);
//        if (checkItem!=null) {
//            ItemBill itemBill = billService.selectItemBillByCheId(checkItem.getCheck_item_id());
//            if (itemBill==null) {
//                itemBill = new ItemBill();
//                itemBill.setItem_bill_id("cbi"+checkItem.getCheck_item_id().substring(5));
//                itemBill.setCheck_item_id(checkItem.getCheck_item_id());
//                itemBill.setHistories_id(histories_id);
//                itemBill.setUser_id(histories.getUser_id());
//                itemBill.setUser_name(histories.getUser_name());
//                itemBill.setItem_id(item.getItem_id());
//                itemBill.setItem_name(item.getName());
//                itemBill.setPrice(item.getPrice());
//                itemBill.setItem_count(checkItem.getItem_count());
//                itemBill.setTotal(itemBill.getPrice()*itemBill.getItem_count());
//                itemBill.setCount(histories.getCount());
//                itemBill.setIsPay("No");
//                itemBill.setCreate_time(checkItem.getCreate_time());
//                int j = billService.createItemBill(itemBill);
//                if (j>0) {
//                    itemBill = billService.selectItemBillByCheId(checkItem.getCheck_item_id());
//                    if (itemBill!=null) {
//                        return Result.setResult(CHECK_EXIST_ERROR).data("checkItem", checkItem).data("itemBill", itemBill);
//                    } else {
//                        return Result.setResult(DATABASE_ERROR).data("checkItem", checkItem);
//                    }
//                }
//                return Result.setResult(ITEM_BILL_CREATE_ERROR).data("checkItem", checkItem);
//            }
//            return Result.setResult(CANDB_EXIST_ERROR).data("checkItem", checkItem).data("itemBill", itemBill);
//        }
//        checkItem = new CheckItem();
//        checkItem.setHistories_id(histories.getHistories_id());
//        checkItem.setItem_id(item.getItem_id());
//        checkItem.setItem_name(item.getName());
//        checkItem.setItem_count(item_count);
//        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        String date = fmt1.format(new Date());
//        checkItem.setCreate_time(date);
//        checkItem.setCheck_item_id("check"+histories_id.substring(3,10)+date.substring(14,16)+date.substring(17));
//        int i = checkItemService.insertCheckItem(checkItem);
//        if (i>0) {
//            checkItem = checkItemService.selectCheckItemById(checkItem.getCheck_item_id());
//            if (checkItem!=null) {
//                ItemBill itemBill = billService.selectItemBillByCheId(checkItem.getCheck_item_id());
//                if (itemBill==null) {
//                    itemBill = new ItemBill();
//                    itemBill.setItem_bill_id("cbi"+checkItem.getCheck_item_id().substring(5));
//                    itemBill.setCheck_item_id(checkItem.getCheck_item_id());
//                    itemBill.setHistories_id(histories_id);
//                    itemBill.setUser_id(histories.getUser_id());
//                    itemBill.setUser_name(histories.getUser_name());
//                    itemBill.setItem_id(item.getItem_id());
//                    itemBill.setItem_name(item.getName());
//                    itemBill.setPrice(item.getPrice());
//                    itemBill.setItem_count(checkItem.getItem_count());
//                    itemBill.setTotal(itemBill.getPrice()*itemBill.getItem_count());
//                    itemBill.setCount(histories.getCount());
//                    itemBill.setIsPay("No");
//                    itemBill.setCreate_time(checkItem.getCreate_time());
//                    int j = billService.createItemBill(itemBill);
//                    if (j>0) {
//                        itemBill = billService.selectItemBillByCheId(checkItem.getCheck_item_id());
//                        if (itemBill!=null) {
//                            return Result.ok().data("checkItem", checkItem).data("itemBill", itemBill);
//                        } else {
//                            return Result.setResult(DATABASE_ERROR).data("checkItem", checkItem);
//                        }
//                    }
//                    return Result.setResult(ITEM_BILL_CREATE_ERROR).data("checkItem", checkItem);
//                }
//                return Result.setResult(CANDB_EXIST_ERROR).data("checkItem", checkItem).data("itemBill", itemBill);
//            }
//            return Result.setResult(DATABASE_ERROR);
//        }
//        return Result.setResult(DATABASE_ERROR);
    }

    /**
     * 获取检查项目信息
     * @param check_item_id 检查单id
     * @return
     */
    @RequestMapping("/get_check_item")
    public Result getCheckItem(@RequestParam("check_item_id") String check_item_id) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        CheckItem checkItem = checkItemService.selectCheckItemById(check_item_id);
        if (checkItem!=null) {
            return Result.ok().data("checkItem", checkItem);
        }
        return Result.setResult(CHECK_NULL_ERROR);
    }

    /**
     * 获取检查单列表
     * @return
     */
    @RequestMapping("/get_check_item_list")
    public Result getCheckItemList() {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        List<CheckItem> checkItemList = checkItemService.selectCheckItemList();
        if (checkItemList!=null&&checkItemList.size()>0) {
            return Result.ok().data("checkItemList", checkItemList);
        }
        return Result.setResult(CHECK_NULL_ERROR);
    }
}
