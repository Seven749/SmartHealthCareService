package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.Nurse;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
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
