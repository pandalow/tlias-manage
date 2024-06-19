package com.tlias.service;

import com.tlias.pojo.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> list();
    public void deleteById(Integer id);
    public void add(Dept dept);
    public Dept getById(Integer id);
    public void edit(Dept dept);
}
