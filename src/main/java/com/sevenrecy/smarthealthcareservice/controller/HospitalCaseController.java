package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.dao.HospitalCaseDao;
import com.sevenrecy.smarthealthcareservice.entity.HospitalCase;
import com.sevenrecy.smarthealthcareservice.json.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.HOSPITAL_CASE_NULL_ERROR;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@CrossOrigin(origins = "http://localhost:63342",maxAge = 3600)
public class HospitalCaseController {
    @Autowired
    HospitalCaseDao hospitalCaseDao;

    @RequestMapping("/get_hospital_case_list")
    public Result getHospitalCaseList(@RequestParam("user_id") int user_id) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        List<HospitalCase> hospitalCaseList = hospitalCaseDao.selectHospitalCaseList(user_id);
        if (hospitalCaseList!=null&&hospitalCaseList.size()>0) {
            return Result.ok().data("hospitalCaseList", hospitalCaseList);
        }
        return Result.setResult(HOSPITAL_CASE_NULL_ERROR);
    }
}
