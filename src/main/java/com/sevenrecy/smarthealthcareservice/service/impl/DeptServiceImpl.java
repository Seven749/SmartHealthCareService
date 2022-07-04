package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.DeptDao;
import com.sevenrecy.smarthealthcareservice.entity.Dept;
import com.sevenrecy.smarthealthcareservice.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptDao deptDao;

    @Override
    public List<Dept> selectDeptList(int type) {
        return deptDao.selectDeptList(type);
    }

    @Override
    public Dept selectDept(String dept_name) {
        return deptDao.selectDept(dept_name);
    }

    @Override
    public Dept selectDeptById(int dept_id) {
        return deptDao.selectDeptById(dept_id);
    }
}
