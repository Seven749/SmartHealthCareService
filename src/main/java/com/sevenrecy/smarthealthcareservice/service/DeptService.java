package com.sevenrecy.smarthealthcareservice.service;

import com.sevenrecy.smarthealthcareservice.entity.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 获取科室列表
     * @return
     */
    List<Dept> selectDeptList(int type);

    /**
     * 获取对应名称的科室信息
     * @param dept_name 科室name
     * @return
     */
    Dept selectDept(String dept_name);

    /**
     * 获取对应id的科室信息
     * @param dept_id 科室id
     * @return
     */
    Dept selectDeptById(int dept_id);
}
