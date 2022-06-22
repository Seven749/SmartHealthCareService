package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.*;
import com.sevenrecy.smarthealthcareservice.entity.input.InPre;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.*;

@RestController
//@RequestMapping("/json")
@CrossOrigin(origins = "http://localhost:63342",maxAge = 3600)
public class TestUserController {

    @Autowired
    DoctorService doctorService;

    @RequestMapping("/test_get_doc")
    public Result testGetDoctor(@RequestParam("doc_name") String doc_name,
                                @RequestParam("dept_id") int dept_id) {
        return Result.ok().data("doctor", doctorService.selectDoctorByDeptAndDoc(dept_id, doc_name));
    }

    /**
     * !!!
     * @param inPreList
     * @return
     */
    @RequestMapping("/test")
    public Result test(@RequestBody List<InPre> inPreList) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" + this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        System.out.println(inPreList);
        return Result.ok();
    }

    @RequestMapping("/")
    public String getIndex() {
        return "Hello!";
    }

//    @RequestMapping(value = "/user_login",method= RequestMethod.POST)

    /**
     * 用户登录接口
     * @param tel 手机号
     * @param pwd 密码
     * @return
     */
    @RequestMapping("/user_login")
    public Result userLogin(@RequestParam("tel") String tel,
                            @RequestParam("pwd") String pwd) {
        if (!tel.equals("testTel")) {
            return Result.setResult(LOGIN_PHONE_ERROR);
        } else if (!pwd.equals("1234")) {
            return Result.setResult(LOGIN_PASSWORD_ERROR);
        }
        User user = new User();
        user.setUser_id(1);
        user.setName("张三");
        user.setPwd("1234");
        user.setIDCard("500***********1234");
        user.setTel("testTel");
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd");
        fmt2.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        user.setBirthday(fmt2.format(new Date()));
        user.setCreate_time(fmt1.format(new Date()));
        user.setSex("男");
        user.setCount(0);
        user.setBalance(0);
        return Result.ok().data("loginUser", user);
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
        User user = new User();
        user.setUser_id(23131);
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
        user.setCount(0);
        return Result.ok().data("registerUser", user);
    }


    @RequestMapping("/user_update")
    public Result userUpdate(@RequestParam("name") String name,
                               @RequestParam("pwd") String pwd,
                               @RequestParam("IDCard") String IDCard,
                               @RequestParam("tel") String tel) {
        User user = new User();
        user.setUser_id(23131);
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
        user.setCount(0);
        user.setBalance(0);
        return Result.ok().data("updateUser", user);
    }

    /**
     * 医护登录接口
     * @param id 工号
     * @param pwd 密码
     * @return
     */
    @RequestMapping("/doc_login")
    public Result docLogin(@RequestParam("doc_id") String id,
                          @RequestParam("pwd") String pwd) {
        if (!id.equals("testId")) {
            return Result.setResult(LOGIN_ACCOUNT_ERROR);
        } else if (!pwd.equals("1234")) {
            return Result.setResult(LOGIN_PASSWORD_ERROR);
        }
        Doctor doctor = new Doctor();
        doctor.setDoc_id("testId");
        doctor.setName("李四");
        doctor.setPwd("1234");
        doctor.setAvatar("null");
        doctor.setDept_id(3);
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        doctor.setCreate_time(fmt1.format(new Date()));
        doctor.setPrice(12.0);
        return Result.ok().data("doctor", doctor);
    }

    @RequestMapping("/nur_login")
    public Result nurLogin(@RequestParam("nur_id") String id,
                          @RequestParam("pwd") String pwd) {
        if (!id.equals("testId")) {
            return Result.setResult(LOGIN_ACCOUNT_ERROR);
        } else if (!pwd.equals("1234")) {
            return Result.setResult(LOGIN_PASSWORD_ERROR);
        }
        Nurse nurse = new Nurse();
        nurse.setNur_id("testId");
        nurse.setName("李四");
        nurse.setPwd("1234");
        nurse.setAvatar("null");
        nurse.setDept_id(3);
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        nurse.setCreate_time(fmt1.format(new Date()));
        return Result.ok().data("nurse", nurse);
    }

    @RequestMapping("/create_registration")
    public Result createRegistration(@RequestParam("user_id") int user_id,
                                      @RequestParam("doc_id") String doc_id,
                                      @RequestParam("date") String date) {
        Registration registration = new Registration();
        registration.setRecord_id("reg_3001");
        registration.setUser_id(20123131);
        registration.setUser_name("张三");
        registration.setDoc_id("testId");
        registration.setDoc_name("李四");
        registration.setNum(301);
        registration.setPrice(8.0);
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        registration.setTime(date);
        registration.setCreate_time(fmt1.format(new Date()));
        return Result.ok().data("registration", registration);
    }

    @RequestMapping("/get_registration_list")
    public Result getRegistrations(@RequestParam("user_id") int user_id) {
        List<Registration> list = new ArrayList<>();
        Registration registration = new Registration();
        registration.setRecord_id("reg_3001");
        registration.setUser_id(20123131);
        registration.setUser_name("张三");
        registration.setDoc_id("testId");
        registration.setDoc_name("李四");
        registration.setNum(301);
        registration.setPrice(8.0);
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        registration.setTime("2022-06-09 14:30");
        registration.setCreate_time(fmt1.format(new Date()));
        list.add(registration);
        list.add(registration);
        list.add(registration);
        list.add(registration);
        list.add(registration);
        return Result.ok().data("list", list);
    }

    @RequestMapping("/get_histories_list")
    public Result getHistoriesList(@RequestParam("user_id") int user_id) {
        List<Histories> historiesList = new ArrayList<>();
        Histories histories = new Histories();
        histories.setHistories_id("histories_0607");
        histories.setUser_id(user_id);
        histories.setUser_name("张三");
        histories.setDoc_id("testId");
        histories.setDoc_name("李四");
        histories.setDept_name("测试科室");
        histories.setAllergies("无");
        histories.setPresent("喉咙痛");
        histories.setPast("无");
        histories.setPhysical_exam("咽喉发炎");
        histories.setAssistant_exam("无");
        histories.setDiagnosis("咽喉发炎");
//        histories.setPrescription_id("pre_001");
//        histories.setCheck_item_id("check_001");
        histories.setAdvice("多喝热水");

        List<Prescription> prescriptionList = new ArrayList<>();
        Prescription prescription = new Prescription();
        prescription.setPrescription_id("pre_001");
        prescription.setHistories_id("histories_001");
        prescription.setDrug_id("drug_001");
        prescription.setDrug_name("阿莫西林");
        prescription.setDrug_count(5);
        prescription.setUsages("每日口服3次，一次2片");
        prescriptionList.add(prescription);
        prescriptionList.add(prescription);

        List<CheckItem> checkItemList = new ArrayList<>();
        CheckItem checkItem = new CheckItem();
        checkItem.setCheck_item_id("check_001");
        checkItem.setHistories_id("histories_001");
        checkItem.setItem_id("item_001");
        checkItem.setItem_name("测试检查项目");
        checkItem.setItem_count(1);
        checkItemList.add(checkItem);
        checkItemList.add(checkItem);

        histories.setPrescriptionList(prescriptionList);
        histories.setCheckItemList(checkItemList);


        historiesList.add(histories);
        historiesList.add(histories);
        historiesList.add(histories);
        historiesList.add(histories);
        historiesList.add(histories);
        return Result.ok().data("historiesList", historiesList);
    }

    @RequestMapping("/get_histories")
    public Result getHistories(@RequestParam("histories_id") String histories_id) {
        Histories histories = new Histories();
        histories.setHistories_id("histories_0607");
        histories.setUser_id(20123131);
        histories.setUser_name("张三");
        histories.setDoc_id("testId");
        histories.setDoc_name("李四");
        histories.setDept_name("测试科室");
        histories.setAllergies("无");
        histories.setPresent("喉咙痛");
        histories.setPast("无");
        histories.setPhysical_exam("咽喉发炎");
        histories.setAssistant_exam("无");
        histories.setDiagnosis("咽喉发炎");
//        histories.setPrescription_id("pre_001");
//        histories.setCheck_item_id("check_001");
        histories.setAdvice("多喝热水");

        List<Prescription> prescriptionList = new ArrayList<>();
        Prescription prescription = new Prescription();
        prescription.setPrescription_id("pre_001");
        prescription.setHistories_id("histories_001");
        prescription.setDrug_id("drug_001");
        prescription.setDrug_name("阿莫西林");
        prescription.setDrug_count(5);
        prescription.setUsages("每日口服3次，一次2片");
        prescriptionList.add(prescription);
        prescriptionList.add(prescription);

        List<CheckItem> checkItemList = new ArrayList<>();
        CheckItem checkItem = new CheckItem();
        checkItem.setCheck_item_id("check_001");
        checkItem.setHistories_id("histories_001");
        checkItem.setItem_id("item_001");
        checkItem.setItem_name("测试检查项目");
        checkItem.setItem_count(1);
        checkItemList.add(checkItem);
        checkItemList.add(checkItem);

        histories.setPrescriptionList(prescriptionList);
        histories.setCheckItemList(checkItemList);

        return Result.ok().data("histories", histories);
    }

    @RequestMapping("/get_report_list")
    public Result getReportList(@RequestParam("user_id") int user_id) {
        List<Report> reportList = new ArrayList<>();
        Report report = new Report();
        report.setCheck_item_id("check_001");
        report.setItem_id("item_001");
        report.setItem_name("测试项目");
        report.setReport_id("report_001");
        report.setUser_id(user_id);
        report.setUser_name("张三");
        report.setDoc_id("testId");
        report.setDoc_name("李四");
        report.setDept_name("测试科室");
        report.setSymptom("测试文本");
        report.setImg1("null");
        report.setImg2("null");
        report.setDiagnosis("测试文本");
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        report.setCreate_time(fmt1.format(new Date()));

        reportList.add(report);
        reportList.add(report);
        reportList.add(report);
        reportList.add(report);
        reportList.add(report);

        return Result.ok().data("reportList", reportList);
    }

    @RequestMapping("/get_report")
    public Result getReportList(@RequestParam("report_id") String report_id) {
        Report report = new Report();
        report.setReport_id("report_001");
        report.setCheck_item_id("check_001");
        report.setItem_id("item_001");
        report.setItem_name("测试项目");
        report.setUser_id(20123131);
        report.setUser_name("张三");
        report.setDoc_id("testId");
        report.setDoc_name("李四");
        report.setDept_name("测试科室");
        report.setSymptom("测试文本");
        report.setImg1("null");
        report.setImg2("null");
        report.setDiagnosis("测试文本");
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        report.setCreate_time(fmt1.format(new Date()));

        return Result.ok().data("report", report);
    }

    @RequestMapping("/get_bill")
    public Result getBill(@RequestParam("user_id") int user_id) {
        List<DrugBill> drugBillList = new ArrayList<>();
        DrugBill drugBill = new DrugBill();
        drugBill.setDrug_bill_id("drug_bill_001");
        drugBill.setPrescription_id("pre_001");
        drugBill.setUser_id(user_id);
        drugBill.setDrug_id("drug_001");
        drugBill.setDrug_name("阿莫西林");
        drugBill.setDrug_count(5);
        drugBill.setPrice(25);
        drugBill.setTotal(5*25);
        drugBill.setCount(1);
        drugBill.setIsPay("No");
        drugBillList.add(drugBill);
        drugBillList.add(drugBill);

        List<ItemBill> itemBillList = new ArrayList<>();
        ItemBill itemBill = new ItemBill();
        itemBill.setItem_bill_id("item_bill_001");
        itemBill.setCheck_item_id("check_item_001");
        itemBill.setUser_id(user_id);
        itemBill.setItem_id("item_001");
        itemBill.setItem_name("测试项目");
        itemBill.setItem_count(1);
        itemBill.setPrice(200);
        itemBill.setTotal(200*1);
        itemBill.setCount(1);
        itemBill.setIsPay("No");
        itemBillList.add(itemBill);
        itemBillList.add(itemBill);

        Map<String, Object> map = new HashMap<>();
        map.put("drugBillList", drugBillList);
        map.put("itemBillList", itemBillList);
        return Result.ok().data(map);
    }

}
