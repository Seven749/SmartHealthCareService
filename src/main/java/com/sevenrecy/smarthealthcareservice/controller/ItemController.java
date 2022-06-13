package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.Item;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum;
import com.sevenrecy.smarthealthcareservice.service.ItemService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
public class ItemController {
    @Autowired
    ItemService itemService;

    /**
     * 新增项目
     * @param item_id 项目id
     * @param name 项目名称
     * @param price 项目价格
     * @param dept_id 科室id
     * @return
     */
    @RequestMapping("/create_item")
    public Result createItem(@RequestParam("item_id") String item_id,
                             @RequestParam("name") String name,
                             @RequestParam("price") double price,
                             @RequestParam("dept_id") int dept_id) {
        Item item = itemService.selectItemById(item_id);
        if (item!=null) {
            return Result.setResult(ITEM_EXIT_ERROR).data("item", item);
        }
        item = new Item();
        item.setItem_id(item_id);
        item.setName(name);
        item.setPrice(price);
        item.setDept_id(dept_id);
        int i = itemService.insertItem(item);
        if (i>0) {
            item = itemService.selectItemById(item_id);
            if (item!=null) {
                return Result.ok().data("item", item);
            }
            return Result.setResult(DATABASE_ERROR);
        }
        return Result.setResult(DATABASE_ERROR);
    }

    /**
     * 获取项目信息
     * @param item_id 项目id
     * @return
     */
    @RequestMapping("get_item")
    public Result getItem(@RequestParam("item_id") String item_id) {
        Item item = itemService.selectItemById(item_id);
        if (item!=null) {
            return Result.ok().data("item", item);
        }
        return Result.setResult(ITEM_NULL_ERROR);
    }

    /**
     * 获取项目列表
     * @return
     */
    @RequestMapping("/get_item_list")
    public Result getItemList() {
        List<Item> itemList = itemService.selectItemList();
        if (itemList!=null&&itemList.size()>0) {
            return Result.ok().data("itemList", itemList);
        }
        return Result.setResult(DATABASE_ERROR);
    }
}
