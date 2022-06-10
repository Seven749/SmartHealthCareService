package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.Drug;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.DrugService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.*;

@RestController
@RequestMapping("/api")
public class DrugController {
    @Autowired
    DrugService drugService;

    /**
     * 导入新药品
     * @param drug_id 药品id
     * @param name 药品名称
     * @param unit 药品计量单位
     * @param capacity 药品规格
     * @param price 药品价格
     * @return
     */
    @RequestMapping("/create_drug")
    public Result createDrug(@RequestParam("drug_id") String drug_id,
                             @RequestParam("name") String name,
                             @RequestParam("unit") String unit,
                             @RequestParam("capacity") String capacity,
                             @RequestParam("price") double price) {
        Drug drug = drugService.selectDrugById(drug_id);
        if (drug==null) {
            return Result.setResult(DRUG_EXIT_ERROR).data("drug", drug);
        }
        drug = new Drug();
        drug.setDrug_id(drug_id);
        drug.setName(name);
        drug.setUnit(unit);
        drug.setCapacity(capacity);
        drug.setPrice(price);
        int i = drugService.insertDrug(drug);
        if (i>0) {
            drug = drugService.selectDrugById(drug_id);
            if (drug!=null) {
                return Result.ok().data("drug", drug);
            }
            return Result.setResult(DATABASE_ERROR);
        }
        return Result.setResult(DATABASE_ERROR);
    }

    /**
     * 获取药品信息
     * @param drug_id 药品id
     * @return
     */
    @RequestMapping("/get_drug")
    public Result getDrug(@RequestParam("drug_id") String drug_id) {
        Drug drug = drugService.selectDrugById(drug_id);
        if (drug!=null) {
            return Result.ok().data("drug", drug);
        }
        return Result.setResult(DRUG_NULL_ERROR);
    }

    /**
     * 获取药品列表
     * @return
     */
    @RequestMapping("/get_drug_list")
    public Result getDrugList() {
        List<Drug> drugList = drugService.selectDrugList();
        if (drugList!=null&&drugList.size()>0) {
            return Result.ok().data("drug_list", drugList);
        }
        return Result.setResult(DATABASE_ERROR);
    }
}
