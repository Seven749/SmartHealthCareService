package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.Dept;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.DATABASE_ERROR;
import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.DEPT_NULL_ERROR;

@RestController
@RequestMapping("/api")
public class DeptController {
    @Autowired
    DeptService deptService;

    @RequestMapping("/get_dept_list")
    public Result getDeptList() {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        List<Dept> deptList = deptService.selectDeptList();
        if (deptList!=null&&deptList.size()>0) {
            return Result.ok().data("deptList", deptList);
        }
        return Result.setResult(DATABASE_ERROR);
    }

    @RequestMapping("/get_dept")
    public Result getDept(@RequestParam("dept_id") int dept_id) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        Dept dept = deptService.selectDept(dept_id);
        if (dept!=null) {
            return Result.ok().data("dept", dept);
        }
        return Result.setResult(DEPT_NULL_ERROR);
    }
}
