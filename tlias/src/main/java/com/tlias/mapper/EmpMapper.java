package com.tlias.mapper;

import com.tlias.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select count(*) from emp")
//    public long count();
//
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start, Integer pageSize);

    //    @Select("select * from emp")
    List<Emp> page(String name, Short gender,
                   LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    @Insert("insert into emp(username,name,gender,image,job,entrydate,dept_id,create_time,update_time) " +
            "values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void save(Emp emp);

    @Select("select * from emp where id =#{id}")
    Emp getById(Integer id);

    void updateById(Emp emp);

    /**
     * @param emp
     * @return
     */
    @Select("select * from emp where username = #{username} and password = #{password};")
    Emp getUserNameAndPassword(Emp emp);

    @Delete("delete from emp where deptId = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
