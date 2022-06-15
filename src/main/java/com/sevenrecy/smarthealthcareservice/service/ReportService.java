package com.sevenrecy.smarthealthcareservice.service;

import com.sevenrecy.smarthealthcareservice.entity.Report;
import org.apache.ibatis.annotations.Param;

public interface ReportService {
    /**
     * 获取报告详情
     * @param report_id 报告id
     * @return
     */
    Report getReport(@Param("report_id") String report_id);

    /**
     * 获取报告列表
     * @param user_id 用户id
     * @return
     */
    Report getReportList(@Param("user_id") int user_id);
}
