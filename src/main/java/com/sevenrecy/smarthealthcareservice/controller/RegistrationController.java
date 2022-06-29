package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.Dept;
import com.sevenrecy.smarthealthcareservice.entity.Doctor;
import com.sevenrecy.smarthealthcareservice.entity.Registration;
import com.sevenrecy.smarthealthcareservice.entity.User;
import com.sevenrecy.smarthealthcareservice.entity.out.OutDoctor;
import com.sevenrecy.smarthealthcareservice.entity.out.OutRegistration;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.DeptService;
import com.sevenrecy.smarthealthcareservice.service.DoctorService;
import com.sevenrecy.smarthealthcareservice.service.RegistrationService;
import com.sevenrecy.smarthealthcareservice.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.*;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@CrossOrigin(origins = "http://localhost:63342",maxAge = 3600)
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;
    @Autowired
    UserService userService;
    @Autowired
    DeptService deptService;
    @Autowired
    DoctorService doctorService;

    /**
     * 挂号时通过此接口进行挂号，并返回挂号单信息
     * @param user_name 用户姓名
     * @param IDCard 用户身份证
     * @param dept_name 挂号科室id
     * @param doc_name 挂号医生姓名
     * @param time 预约时间
     * @return
     */
    @RequestMapping("/create_registration")
    public Result createRegistration(@RequestParam("user_name") String user_name,
                                     @RequestParam("IDCard") String IDCard,
                                     @RequestParam("dept_name") String dept_name,
                                     @RequestParam("doc_name") String doc_name,
                                     @RequestParam("date") String date,
                                     @RequestParam("time") String time) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        User user = userService.selectSysUserByIDCard(IDCard);
        if (user==null) {
            // 无用户，创建用户
            user = new User();
            user.setName(user_name);
            user.setIDCard(IDCard);
            String birth = ""+IDCard.substring(6,10)+"-"+IDCard.substring(10,12)+"-"+IDCard.substring(12,14);
            SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            user.setBirthday(birth);
            user.setCreate_time(fmt1.format(new Date()));
            String sexNum = IDCard.substring(16,17);
            if (Integer.parseInt(sexNum)%2 == 0) {
                user.setSex("女");
            } else {
                user.setSex("男");
            }
            int i = userService.insertSysUser(user);
            if (i<=0) {
                return Result.setResult(DATABASE_ERROR);
            }
            user = userService.selectSysUserByIDCard(IDCard);
        }
        // 用户存在
        Dept dept = deptService.selectDept(dept_name);
        if (dept==null) {
            // 科室不存在
            return Result.setResult(DEPT_NULL_ERROR);
        }
        OutDoctor doctor = doctorService.selectDoctorByDeptAndDoc(dept.getDept_id(),doc_name);
        if (doctor==null) {
            // 医生不存在
            return Result.setResult(DOCTOR_NULL_ERROR);
        }
        String doc_id = doctor.getDoc_id();
        Registration registration = registrationService.selectRegistration(doc_id, date, time);
        if (registration!=null) {
            // 时间已占用
            return Result.setResult(REGISTRATION_TIME_ERROR);
        }
        int h = Integer.parseInt(time.substring(0,2));
        int m = Integer.parseInt(time.substring(3,5));
        int num;
        if (h<13) {
            num = 1 + (h-8)*6 + (m/10);
        } else {
            num = 25 + (h-14)*6 + (m/10);
        }
        // 挂号
        registration = new Registration();
        registration.setUser_id(user.getUser_id());
        registration.setUser_name(user.getName());
        registration.setUser_IDCard(user.getIDCard());
        registration.setCount(user.getCount()+1);
        registration.setDoc_id(doctor.getDoc_id());
        registration.setDoc_name(doctor.getName());
        registration.setDept_id(dept.getDept_id());
        registration.setDept_name(dept.getName());
        registration.setNum(num);
        registration.setDate(date);
        registration.setTime(time);
        registration.setRecord_id(doc_id+date.substring(0,4)+date.substring(5,7)+date.substring(8,10)+num);
        registration.setPrice(doctor.getPrice());
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        registration.setCreate_time(fmt1.format(new Date()));
        int i = registrationService.insertRegistration(registration);
        if (i>0) {
            registration = registrationService.selectRegistration(doc_id, date, time);
            int k = userService.updateCount(user.getUser_id());
            if (k==1) return Result.ok().data("registration", registration);
        }
        return Result.setResult(DATABASE_ERROR);
    }

    /**
     * 获取用户的预约挂号记录
     * @param user_id 用户id
     * @return
     */
    @RequestMapping("/get_registration_list")
    public Result getRegistrations(@RequestParam("user_id") int user_id) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        List<Registration> registrationList = registrationService.selectRegistrationList(user_id);
        if (registrationList==null||registrationList.size()==0) {
            return Result.setResult(REGISTRATION_NULL_ERROR);
        }
        // 有预约记录
        return Result.ok().data("registrationList", registrationList);
    }

    /**
     * 获取医生再某一时间的号
     * @param doc_id 医生id
     * @param date 日期
     * @param time 时间
     * @return
     */
    @RequestMapping("/get_num")
    public Result getNum(@RequestParam("doc_id") String doc_id,
                         @RequestParam("date") String date,
                         @RequestParam("time") String time) {
        int num = registrationService.selectNumByTime(doc_id, date, time);
        if (num>0) {
            return Result.ok().data("num", num);
        }
        return Result.setResult(REGISTRATION_NULL_ERROR);
    }

    @RequestMapping("/get_registration_of_doctor")
    public Result getRegistrationOfDoctor(@RequestParam("doc_id") String doc_id) {
//        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
//        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        String date = fmt1.format(new Date());
        String date = "2022-06-09";
        List<OutRegistration> outRegistrationList = registrationService.selectRegistrationOfDoctor(doc_id, date);
        if (outRegistrationList!=null&&outRegistrationList.size()>0) {
            return Result.ok().data("RegistrationList", outRegistrationList);
        }
        return Result.setResult(REGISTRATION_NULL_ERROR);
    }

    @RequestMapping("/registration_skip_num")
    public Result skipNum(@RequestParam("doc_id") String doc_id,
                          @RequestParam("num") int num) {
        //        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
//        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        String date = fmt1.format(new Date());
        String date = "2022-06-09";
        int i = registrationService.updateSkipNum(doc_id, date, num);
        if (i==1) {
            List<OutRegistration> outRegistrationList = registrationService.selectRegistrationOfDoctor(doc_id, date);
            if (outRegistrationList!=null&&outRegistrationList.size()>0) {
                return Result.ok().message(num+"号：跳号成功").data("RegistrationList", outRegistrationList);
            }
        }
        return Result.setResult(DATABASE_ERROR);
    }

    @RequestMapping("/registration_confirm_num")
    public Result confirmNum(@RequestParam("doc_id") String doc_id,
                          @RequestParam("num") int num) {
        //        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
//        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        String date = fmt1.format(new Date());
        String date = "2022-06-09";
        int i = registrationService.updateConfirmNum(doc_id, date, num);
        if (i==1) {
            return Result.ok().message(num+"号：确认成功");
        }
        return Result.setResult(DATABASE_ERROR);
    }
}
