package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.ReportDao;
import com.sevenrecy.smarthealthcareservice.entity.Report;
import com.sevenrecy.smarthealthcareservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportDao reportDao;

    @Override
    public Report getReport(String report_id) {
        return reportDao.getReport(report_id);
    }

    @Override
    public List<Report> getReportList(int user_id) {
        return reportDao.getReportList(user_id);
    }
}
