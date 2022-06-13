package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.Doctor;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
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
    }

    /**
     * 查询医生
     * @param doc_id 医生id
     * @return
     */
    @RequestMapping("/get_doc")
    public Result getDoctor(@RequestParam("doc_id") String doc_id) {
        Doctor doctor = doctorService.selectDoctorById(doc_id);
        if (doctor!=null) {
            return Result.ok().data("doctor", doctor);
        }
        return Result.setResult(LOGIN_NULL_ERROR);
    }

    /**
     * 获取科室医生列表
     * @param dept_id 科室
     * @return
     */
    @RequestMapping("get_doc_dept_list")
    public Result getDoctorDeptList(@RequestParam("dept_id") int dept_id) {
        List<Doctor> doctorList = doctorService.selectDoctorByDept(dept_id);
        if (doctorList!=null&&doctorList.size()>0) {
            return Result.ok().data("doctorList", doctorList);
        }
        return Result.setResult(DOCTOR_NULL_ERROR);
    }
}
