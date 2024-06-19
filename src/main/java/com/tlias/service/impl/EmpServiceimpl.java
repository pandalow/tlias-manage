package com.tlias.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tlias.mapper.EmpMapper;
import com.tlias.pojo.Emp;
import com.tlias.pojo.PageBean;
import com.tlias.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceimpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    //    public PageBean page(Integer page, Integer pageSize) {
//
//        long count = empMapper.count();
//        List<Emp> emps = empMapper.page((page - 1) * pageSize, pageSize);
//        return new PageBean(count, emps);
//    }
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender,
                         LocalDate begin, LocalDate end) {

        PageHelper.startPage(page, pageSize);
        Page<Emp> p = (Page<Emp>) empMapper.page(name, gender, begin, end);

        return new PageBean(p.getTotal(), p.getResult());
    }
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }
    @Override
    public void updateById(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
    }

    @Override
    public Emp login(Emp emp){
        return empMapper.getUserNameAndPassword(emp);
    }
}
