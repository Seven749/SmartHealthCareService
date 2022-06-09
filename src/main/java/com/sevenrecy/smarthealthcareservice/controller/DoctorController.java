package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.Doctor;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.DoctorService;
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
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    /**
     * 医护登录接口
     * @param doc_id 工号
     * @param pwd 密码
     * @return
     */
    @RequestMapping("/doc_login")
    public Result docLogin(@RequestParam("doc_id") String doc_id,
                           @RequestParam("pwd") String pwd) {
        Doctor doctor = doctorService.selectDoctor(doc_id, pwd);
        if (doctor!=null) {
            return Result.ok().data("login_doctor", doctor);
        }
        return Result.setResult(LOGIN_NULL_ERROR);
//        if (!doc_id.equals("testId")) {
//            return Result.setResult(LOGIN_ACCOUNT_ERROR);
//        } else if (!pwd.equals("1234")) {
//            return Result.setResult(LOGIN_PASSWORD_ERROR);
//        }
//        Doctor doctor = new Doctor();
//        doctor.setDoc_id("testId");
//        doctor.setName("李四");
//        doctor.setPwd("1234");
//        doctor.setAvatar("null");
//        doctor.setDept_id(3);
//        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        doctor.setCreate_time(fmt1.format(new Date()));
//        doctor.setPrice(12.0);
//        return Result.ok().data("doctor", doctor);
    }
}
