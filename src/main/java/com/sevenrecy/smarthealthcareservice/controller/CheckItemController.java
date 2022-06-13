package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.CheckItem;
import com.sevenrecy.smarthealthcareservice.entity.Histories;
import com.sevenrecy.smarthealthcareservice.entity.Item;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.CheckItemService;
import com.sevenrecy.smarthealthcareservice.service.HistoriesService;
import com.sevenrecy.smarthealthcareservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
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

    /**
     * 新增检查记录
     * @param histories_id 病历id
     * @param item_id 项目id
     * @param item_count 项目数目
     * @return
     */
    @RequestMapping("/create_check_item")
    public Result creatCheckItem(@RequestParam("histories_id") String histories_id,
                                 @RequestParam("item_id") String item_id,
                                 @RequestParam("item_count") int item_count) {
        CheckItem checkItem = checkItemService.selectCheckItem(histories_id, item_id);
        if (checkItem!=null) {
            return Result.setResult(CHECK_EXIT_ERROR);
        }
        Histories histories = historiesService.selectHistoriesById(histories_id);
        if (histories==null) {
            return Result.setResult(HISTORIES_NULL_ERROR);
        }
        Item item = itemService.selectItemById(item_id);
        if (item==null) {
            return Result.setResult(ITEM_NULL_ERROR);
        }
        checkItem = new CheckItem();
        checkItem.setHistories_id(histories.getHistories_id());
        checkItem.setItem_id(item.getItem_id());
        checkItem.setItem_name(item.getName());
        checkItem.setItem_count(item_count);
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String date = fmt1.format(new Date());
        checkItem.setCheck_item_id(date);
        checkItem.setCheck_item_id("check"+histories_id.substring(3,10)+date.substring(15,17)+date.substring(18,20));
        int i = checkItemService.insertCheckItem(checkItem);
        if (i>0) {
            checkItem = checkItemService.selectCheckItemById(checkItem.getCheck_item_id());
            if (checkItem!=null) {
                return Result.ok().data("checkItem", checkItem);
            }
            return Result.setResult(DATABASE_ERROR);
        }
        return Result.setResult(DATABASE_ERROR);
    }

    /**
     * 获取检查项目信息
     * @param check_item_id 检查单id
     * @return
     */
    @RequestMapping("/get_check_item")
    public Result getCheckItem(@RequestParam("check_item_id") String check_item_id) {
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
        List<CheckItem> checkItemList = checkItemService.selectCheckItemList();
        if (checkItemList!=null&&checkItemList.size()>0) {
            return Result.ok().data("checkItemList", checkItemList);
        }
        return Result.setResult(CHECK_NULL_ERROR);
    }
}
