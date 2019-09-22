package com.annu.action.web;

import com.annu.action.service.StudentService;
import com.annu.action.vo.ResponseVo;
import com.annu.action.vo.StudentEditVo;
import com.annu.action.vo.StudentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("students")
@Api(value = "学生操作接口")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("{id}")
    @ApiOperation("根据id查询学生")
    public ResponseVo<StudentVo> findOne(@PathVariable Long id) {
        return ResponseVo.success(studentService.findOne(id));
    }

    @PostMapping
    @ApiOperation("创建学生")
    public void add(@RequestBody @Valid StudentEditVo vo) {
    }

    @GetMapping("search")
    @ApiOperation("根据name查询学生")
    public ResponseVo<StudentVo> search(@NotBlank @RequestParam String name) {
        return ResponseVo.success(studentService.findByEqualToName(name));
    }

    @GetMapping(value = "export",produces = "text/csv")
    @ApiOperation("CSV导出学生数据信息")
    public void export(@Validated @NotEmpty @RequestParam("name") String name, HttpServletResponse response) {
        studentService.export(name, response);
    }
}
