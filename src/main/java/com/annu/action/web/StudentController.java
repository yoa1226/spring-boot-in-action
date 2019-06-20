package com.annu.action.web;

import com.annu.action.service.StudentService;
import com.annu.action.vo.StudentEditVo;
import com.annu.action.vo.StudentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/students")
@Api(value = "学生操作接口value")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("{id}")
    public StudentVo findOne(@PathVariable Long id) {
        return studentService.findOne(id);
    }

    @PostMapping
    @ApiOperation("创建学生")
    public void add(@RequestBody @Valid StudentEditVo vo) {
    }

}
