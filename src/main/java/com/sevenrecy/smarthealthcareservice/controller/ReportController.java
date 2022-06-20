package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.Report;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum;
import com.sevenrecy.smarthealthcareservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.REPORT_NULL_ERROR;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@CrossOrigin(origins = "http://localhost:63342",maxAge = 3600)
public class ReportController {
    @Autowired
    ReportService reportService;

    /**
     * 获取报告单
     * @param report_id 报告id
     * @return
     */
    @RequestMapping("/get_report")
    public Result getReport(@RequestParam("report_id") String report_id) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        Report report = reportService.getReport(report_id);
        if (report!=null) {
            return Result.ok().data("report", report);
        }
        return Result.setResult(REPORT_NULL_ERROR);
    }

    @RequestMapping("/get_report_list")
    public Result getReportList(@RequestParam("user_id") int user_id) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        List<Report> reportList = reportService.getReportList(user_id);
        if (reportList!=null&&reportList.size()>0) {
            return Result.ok().data("reportList", reportList);
        }
        return Result.setResult(REPORT_NULL_ERROR);
    }
}
