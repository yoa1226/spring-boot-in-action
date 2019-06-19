package com.annu.action.web;

import com.annu.action.service.StudentService;
import com.annu.action.vo.StudentEditVo;
import com.annu.action.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("{id}")
    public StudentVo findOne(@PathVariable Long id) {
        return studentService.findOne(id);
    }

    @PostMapping
    public void add(@RequestBody @Valid StudentEditVo vo) {
    }

}
