package com.tlias.mapper;

import com.tlias.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {
    @Insert("insert into detplog(create_time,descritpion) values (creatTime,description)")
    void insertLog(DeptLog deptLog);
}
