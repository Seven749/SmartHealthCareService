package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.Dept;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.DATABASE_ERROR;
import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.DEPT_NULL_ERROR;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@CrossOrigin(origins = "http://localhost:63342",maxAge = 3600)
public class DeptController {
    @Autowired
    DeptService deptService;

    @RequestMapping("/get_dept_list_reg")
    public Result getDeptListReg() {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        List<Dept> deptList = deptService.selectDeptList(0);
        if (deptList!=null&&deptList.size()>0) {
            return Result.ok().data("deptList", deptList);
        }
        return Result.setResult(DATABASE_ERROR);
    }

    @RequestMapping("/get_dept_list_item")
    public Result getDeptListItem() {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        List<Dept> deptList = deptService.selectDeptList(1);
        if (deptList!=null&&deptList.size()>0) {
            return Result.ok().data("deptList", deptList);
        }
        return Result.setResult(DATABASE_ERROR);
    }

    @RequestMapping("/get_dept")
    public Result getDept(@RequestParam("dept_name") String dept_name) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        Dept dept = deptService.selectDept(dept_name);
        if (dept!=null) {
            return Result.ok().data("dept", dept);
        }
        return Result.setResult(DEPT_NULL_ERROR);
    }

    @RequestMapping("/get_dept_by_id")
    public Result getDept(@RequestParam("dept_id") int dept_id) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        Dept dept = deptService.selectDeptById(dept_id);
        if (dept!=null) {
            return Result.ok().data("dept", dept);
        }
        return Result.setResult(DEPT_NULL_ERROR);
    }
}
