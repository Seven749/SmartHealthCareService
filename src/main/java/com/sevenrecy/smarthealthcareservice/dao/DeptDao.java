package com.sevenrecy.smarthealthcareservice.dao;

import com.sevenrecy.smarthealthcareservice.entity.Dept;

import java.util.List;

public interface DeptDao {
    /**
     * 获取科室列表
     * @return
     */
    List<Dept> selectDeptList();

    /**
     * 获取对应id的科室信息
     * @param dept_name 科室id
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
