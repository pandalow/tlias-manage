package com.tlias.controller;

import com.tlias.anno.Log;
import com.tlias.pojo.Emp;
import com.tlias.pojo.PageBean;
import com.tlias.pojo.Result;
import com.tlias.service.impl.EmpServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    EmpServiceimpl empServiceimpl;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        log.info("分页查询{} {} {} {} {} {}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empServiceimpl.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("{}", ids);
        empServiceimpl.delete(ids);

        return Result.success();
    }
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        empServiceimpl.save(emp);
        log.info("{}", emp);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Emp emp = empServiceimpl.getById(id);
        return Result.success(emp);
    }
    @Log
    @PutMapping
    public Result updateById(@RequestBody Emp emp){

        empServiceimpl.updateById(emp);
        return Result.success();
    }

}
