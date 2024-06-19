package com.tlias.controller;

import com.tlias.anno.Log;
import com.tlias.pojo.Dept;
import com.tlias.pojo.Result;
import com.tlias.service.impl.DeptServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    //    private static Logger log = LoggerFactory.getLogger(DeptController.class);
    //    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @Autowired
    private DeptServiceimpl deptService;

    @GetMapping
    public Result list() {
        List<Dept> deptList = deptService.list();
        log.info("查询全部部门数据");
        return Result.success(deptList);
    }
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        deptService.deleteById(id);
        log.info("根据id删除{}", id);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("new Dept{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("search Dept{}",id);
        Dept byId = deptService.getById(id);
        return Result.success(byId);
    }
    @Log
    @PutMapping
    public Result updateDept(@RequestBody Dept dept){
        log.info("Dept{},{}",dept.getId(),dept.getName());
        deptService.edit(dept);
        return Result.success();
    }
}
