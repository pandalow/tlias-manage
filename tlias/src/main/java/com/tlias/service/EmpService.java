package com.tlias.service;

import com.tlias.pojo.Emp;
import com.tlias.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;


public interface EmpService {
    PageBean page(Integer page,Integer pageSize, String name, Short gender,
                  LocalDate begin, LocalDate end);
    void delete(List<Integer> ids);

    void save(Emp emp);
    Emp getById(Integer id);
    void updateById(Emp emp);

    Emp login(Emp emp);
}
