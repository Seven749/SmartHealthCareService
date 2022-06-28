package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.User;
import com.sevenrecy.smarthealthcareservice.entity.out.OutUser;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.*;
@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@CrossOrigin(origins = "http://localhost:63342",maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 用户登录接口
     * @param tel 手机号
     * @param pwd 密码
     * @return
     */
    @RequestMapping("/user_login")
    public Result userLogin(@RequestParam("tel") String tel,
                            @RequestParam("pwd") String pwd) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        User user = userService.selectSysUserByTel(tel);
        if (user!=null) {
            if (user.getPwd().equals(pwd)) {
                return Result.ok().data("loginUser", getOutUser(setAge(user)));
            } else {
                return Result.setResult(LOGIN_PASSWORD_ERROR);
            }
        }
        return Result.setResult(LOGIN_PHONE_NULL_ERROR);
    }

    /**
     * 用户注册接口
     * @param name 姓名
     * @param pwd 密码
     * @param IDCard 身份证
     * @param tel 手机号
     * @return
     */
    @RequestMapping("/user_register")
    public Result userRegister(@RequestParam("name") String name,
                               @RequestParam("pwd") String pwd,
                               @RequestParam("IDCard") String IDCard,
                               @RequestParam("tel") String tel) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        User user = new User();
        user.setName(name);
        user.setPwd(pwd);
        user.setIDCard(IDCard);
        user.setTel(tel);
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

        User checkedUser = userService.selectSysUserByIDCard(IDCard);
        if (checkedUser==null) {
            System.out.println("身份证未使用");
            // 身份证未使用
            checkedUser = userService.selectSysUserByTel(tel);

            if (checkedUser==null) {
                // 手机号未使用
            } else {
                // 手机号已使用
                return Result.setResult(REGISTER_MOBILE_ERROR);
            }
        } else {
            int user_id = checkedUser.getUser_id();
            String checkTel = checkedUser.getTel();
            if (checkTel==null || checkTel.isEmpty()) {
                // 未注册,但已有信息，直接完善信息
                System.out.println(user_id + " "+ user.getTel()+ " " + user.getPwd());
                int i = userService.updateSysUserPwd(user_id, user.getTel(), user.getPwd());
                if (i>0) {
                    user = userService.selectSysUserByTel(tel);
                    return Result.ok().data("user", getOutUser(setAge(user)));
                }
                return Result.setResult(DATABASE_ERROR);
            } else {
                // 已注册
                return Result.setResult(REGISTER_IDCARD_ERROR);
            }
        }
        // 可以注册
        int i = userService.insertSysUser(user);
        if (i>0) {
            user = userService.selectSysUserByTel(tel);
            return Result.ok().data("registerUser", getOutUser(setAge(user)));
        }
        return Result.setResult(DATABASE_ERROR);
    }

    @RequestMapping("/user_find_pwd")
    public Result userFindPwd(@RequestParam("IDCard") String IDCard,
                              @RequestParam("tel") String tel,
                              @RequestParam("pwd") String pwd) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        User user = userService.selectSysUserByIDCardAndTel(IDCard, tel);
        if (user!=null) {
            int user_id = user.getUser_id();
            int i = userService.updateSysUserPwd(user_id, tel, pwd);
            if (i>0) {
                user = userService.selectSysUserByTel(tel);
                return Result.ok().data("user", getOutUser(setAge(user)));
            }
            return Result.setResult(DATABASE_ERROR);
        }
        return Result.setResult(USER_NULL_ERROR);
    }

    @RequestMapping("/get_user_by_id")
    public Result getUserById(@RequestParam("user_id") int user_id) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        User user = userService.selectSysUserById(user_id);
        if (user!=null) {
            return Result.ok().data("user", setAge(user));
        }
        return Result.setResult(USER_NULL_ERROR);
    }

    @RequestMapping("/get_user")
    public Result getUser(@RequestParam("user_IDCard") String IDcard) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        User user = userService.selectSysUserByIDCard(IDcard);
        if (user!=null) {
            return Result.ok().data("user", getOutUser(setAge(user)));
        }
        return Result.setResult(USER_NULL_ERROR);
    }

    /**
     * 实时计算年龄
     * @param user 用户对象
     * @return
     */
    private User setAge(User user) {
        int age;
        String birth = user.getBirthday();
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String date = fmt1.format(new Date());
        age = Integer.parseInt(date.substring(0,4)) - Integer.parseInt(birth.substring(0,4));
        if (Integer.parseInt((date.substring(5,7))) < Integer.parseInt(birth.substring(5,7)) ||
                Integer.parseInt((date.substring(8,10))) < Integer.parseInt(birth.substring(8,10))) {
            age -= 1;
        }
        user.setAge(age);
        return user;
    }


    private OutUser getOutUser(User user) {
        OutUser outUser = new OutUser();
        outUser.setName(user.getName());
        outUser.setUser_id(user.getUser_id());
        outUser.setIDCard(user.getIDCard());
        outUser.setBirthday(user.getBirthday());
        outUser.setSex(user.getSex());
        outUser.setAge(user.getAge());
        outUser.setTel(user.getTel());
        outUser.setBalance(user.getBalance());
        outUser.setCount(user.getCount());
        return outUser;
    }
}
