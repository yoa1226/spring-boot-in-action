package com.annu.action.service;

import com.annu.action.vo.StudentVo;

public interface StudentService {

    StudentVo findOne(Long id);

    StudentVo findByEqualToName(String name);

}
