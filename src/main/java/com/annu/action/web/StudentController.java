package com.annu.action.web;

import com.annu.action.service.StudentService;
import com.annu.action.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("{id}")
    public StudentVo findOne(@PathVariable Long id) {
        return studentService.findOne(id);
    }

}
