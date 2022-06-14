package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.*;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import javax.xml.crypto.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
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
    @Autowired
    CheckItemService checkItemService;

    /**
     * 新增病历
     * @param user_id 用户id
     * @param doc_id 医生id
     * @param dept_id 科室id
     * @param allergies 过敏史
     * @param present 现状史
     * @param past 既往史
     * @param physical_exam 体格检查
     * @param assistant_exam 辅助检查
     * @param diagnosis 诊断
     * @param advice 建议
     * @param prescription_count 处方数量
     * @param check_item_count 检查单数量
     * @return
     */
    @RequestMapping("/create_histories")
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
                                  @RequestParam("prescription_count") int prescription_count,
                                  @RequestParam("check_item_count") int check_item_count) {
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
        histories.setCount(user.getCount());
        histories.setDoc_id(doctor.getDoc_id());
        histories.setDoc_name(doctor.getName());
        histories.setDept_id(dept.getDept_id());
        histories.setDept_name(dept.getName());
        histories.setAllergies(allergies);
        histories.setPresent(present);
        histories.setPast(past);
        histories.setPhysical_exam(physical_exam);
        histories.setAssistant_exam(assistant_exam);
        histories.setDiagnosis(diagnosis);
        histories.setAdvice(advice);
        histories.setPrescription_count(prescription_count);
        histories.setCheck_item_count(check_item_count);
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        histories.setCreate_time(fmt1.format(new Date()));
        String time = histories.getCreate_time();
        histories.setHistories_id("his"+time.substring(2,4)+time.substring(5,7)+time.substring(8,10)+time.substring(11,13)+user_id);
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

    /**
     * 获取某一次病历的详细信息
     * @param histories_id 病历id
     * @return
     */
    @RequestMapping("/get_histories")
    public Result getHistories(@RequestParam("histories_id") String histories_id) {
        Histories histories = historiesService.selectHistoriesById(histories_id);
        if (histories==null) {
            return Result.setResult(HISTORIES_NULL_ERROR);
        }
        int p_count = histories.getPrescription_count();
        int c_count = histories.getCheck_item_count();
        if (p_count!=0) {
            List<Prescription> p_list = prescriptionService.selectPrescriptionList(histories_id);
            if (p_list!=null&&p_list.size()==p_count) {
                histories.setPrescriptionList(p_list);
            } else {
                return Result.setResult(DATABASE_ERROR);
            }
        }
        if (c_count!=0) {
            List<CheckItem> c_list = checkItemService.selectCheckItemList();
            if (c_list!=null&&c_list.size()==c_count) {
                histories.setCheckItemList(c_list);
            } else {
                return Result.setResult(DATABASE_ERROR);
            }
        }
        return Result.ok().data("histories", histories);
    }

    @RequestMapping("/get_histories_list")
    public Result getHistoriesList(@RequestParam("user_id") int user_id) {
        List<Histories> historiesList = historiesService.selectHistoriesList(user_id);
        if (historiesList!=null&&historiesList.size()>0) {
            return Result.ok().data("historiesList", historiesList);
        }
        return Result.setResult(HISTORIES_NULL_ERROR);
    }
}
