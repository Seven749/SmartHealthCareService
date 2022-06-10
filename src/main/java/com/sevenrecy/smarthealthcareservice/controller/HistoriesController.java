package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.*;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import javax.xml.crypto.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.*;

@RestController
@RequestMapping("/api")
public class HistoriesController {
    @Autowired
    HistoriesService historiesService;
    @Autowired
    UserService userService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    DeptService deptService;
    @Autowired
    PrescriptionService prescriptionService;

    @RequestMapping("create_histories")
    public Result createHistories(@RequestParam("user_id") int user_id,
                                  @RequestParam("doc_id") String doc_id,
                                  @RequestParam("dept_id") int dept_id,
                                  @RequestParam("allergies") String allergies,
                                  @RequestParam("present") String present,
                                  @RequestParam("past") String past,
                                  @RequestParam("physical_exam") String physical_exam,
                                  @RequestParam("assistant_exam") String assistant_exam,
                                  @RequestParam("diagnosis") String diagnosis,
                                  @RequestParam("advice") String advice,
                                  @RequestParam("create_time") String create_time) {
        // 编辑病历
            // 查询用户
        User user = userService.selectSysUserById(user_id);
        if (user==null) {
            // 用户不存在
            return Result.setResult(USER_NULL_ERROR);
        }
        Doctor doctor = doctorService.selectDoctorById(doc_id);
        if (doctor==null) {
            // 医生不存在
            return Result.setResult(DOCTOR_NULL_ERROR);
        }
        Dept dept = deptService.selectDept(dept_id);
        if (dept==null) {
            // 科室不存在
            return Result.setResult(DEPT_NULL_ERROR);
        }
        Histories histories = new Histories();
        histories.setUser_id(user.getUser_id());
        histories.setUser_name(user.getName());
        histories.setDoc_id(doctor.getDoc_id());
        histories.setDept_name(dept.getName());
        histories.setDept_id(dept.getDept_id());
        histories.setDept_name(dept.getName());
        histories.setAllergies(allergies);
        histories.setPresent(present);
        histories.setPast(past);
        histories.setPhysical_exam(physical_exam);
        histories.setAssistant_exam(assistant_exam);
        histories.setAdvice(advice);
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = new Date();
        try {
            date = fmt1.parse(create_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        histories.setCreate_time(fmt1.format(date));
        String time = histories.getCreate_time();
        histories.setHistories_id(user_id+time.substring(2,4)+time.substring(5,7)+time.substring(8,10));
        // 先新建病历
        int i = historiesService.insertHistories(histories);
        if (i<=0) {
            return Result.setResult(DATABASE_ERROR);
        }
        // 成功
        histories = historiesService.selectHistoriesById(histories.getHistories_id());
        if (histories==null) {
            return Result.setResult(DATABASE_ERROR);
        }
        return Result.ok().data("histories", histories);
    }
}
