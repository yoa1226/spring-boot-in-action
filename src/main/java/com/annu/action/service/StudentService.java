package com.annu.action.service;

import com.annu.action.vo.StudentVo;

import javax.servlet.http.HttpServletResponse;

public interface StudentService {

    StudentVo findOne(Long id);

    StudentVo findByEqualToName(String name);

    void export(String name, HttpServletResponse response);

}
