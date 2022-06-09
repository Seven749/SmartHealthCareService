package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.dao.NurseDao;
import com.sevenrecy.smarthealthcareservice.entity.Nurse;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.*;

@RestController
@RequestMapping("/api")
public class NurseController {
    @Autowired
    NurseService nurseService;

    @RequestMapping("/nur_login")
    public Result nurLogin(@RequestParam("nur_id") String nur_id,
                           @RequestParam("pwd") String pwd) {
        Nurse nurse = nurseService.selectNurse(nur_id, pwd);
        if (nurse!=null) {
            return Result.ok().data("login_nurse", nurse);
        }
        return Result.setResult(LOGIN_NULL_ERROR);
    }

}
